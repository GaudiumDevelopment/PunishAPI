package me.superbiebel.punishapi.offenseprocessing;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.System;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.*;
import me.superbiebel.punishapi.dataobjects.scriptobjects.OffenseScriptProcessingResult;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OffenseManager extends System {
    
    PunishCore core;
    
    
    public OffenseManager(PunishCore core) {
        this.core = core;
    }
    @NotNull
    public OffenseHistoryRecord submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws ServiceNotFoundException, IOException {
        Datamanager datamanager = core.getDatamanager();
        try {
            
            datamanager.lockUser(offenseProcessingRequest.getCriminalUUID());
            OffenseScriptProcessingResult verdict = processScript(offenseProcessingRequest
                    , datamanager.retrieveOffenseProcessingTemplate(offenseProcessingRequest.getProcessingTemplateUUID()));
    
            OffenseHistoryRecord offenseHistoryRecord = convertToOffenseHistoryRecord(verdict, offenseProcessingRequest);
            core.getDatamanager().storeOffense(offenseHistoryRecord);
            return offenseHistoryRecord;
        } finally {
            datamanager.unlockUser(offenseProcessingRequest.getCriminalUUID());
        }
        
    }
    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseRecord) throws ServiceNotFoundException {
        core.getDatamanager().storeOffense(offenseRecord);
    }
    @NotNull
    public OffenseScriptProcessingResult processScript(OffenseProcessingRequest offenseProcessingRequest, OffenseProcessingTemplate template) throws IOException {
        
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            Source source = Source.newBuilder("js", template.getScriptFile()).build();
            Value jsBindings = context.getBindings("js");
            jsBindings.putMember("request", offenseProcessingRequest);
            jsBindings.putMember("datamanager", core.getDatamanager());
            context.eval(source);
            Value verdictValue = jsBindings.getMember("verdict");
            return verdictValue.as(OffenseScriptProcessingResult.class);
        }
    }
    public OffenseHistoryRecord convertToOffenseHistoryRecord(OffenseScriptProcessingResult result, OffenseProcessingRequest offenseProcessingRequest) {
        List<Punishment> punishmentList = new ArrayList<>();
        
        UUID offenseUUID = UUID.randomUUID();
        
        result.punishments.forEach(punishmentScriptObject->{
            Punishment.PunishmentBuilder punishmentBuilder = Punishment.builder()
                                            .punishmentUUID(UUID.randomUUID())
                                            .offenseUUID(offenseUUID)
                                            .attributes(punishmentScriptObject.attributes)
                                            .startTime(punishmentScriptObject.startTime)
                                            .originalDuration(punishmentScriptObject.duration)
                                            .duration(punishmentScriptObject.duration)
                                            .decrementsDuration(punishmentScriptObject.decrementsDuration)
                                            .activated(punishmentScriptObject.activated)
                                            .scopes(punishmentScriptObject.scopes);
            List<PunishmentReduction> punishmentReductionList = new ArrayList<>();
            punishmentScriptObject.punishmentReductionList.forEach(punishmentReductionScriptObject->{
                PunishmentReduction reduction = PunishmentReduction.builder()
                                                        .punishmentReductionUUID(UUID.randomUUID())
                                                        .amountSubtracted(punishmentReductionScriptObject.amountSubtracted)
                                                        .priority(punishmentReductionScriptObject.priority)
                                                        .build();
                punishmentReductionList.add(reduction);
            });
            punishmentBuilder.punishmentReductions(punishmentReductionList);
            punishmentList.add(punishmentBuilder.build());
        });
        
        
        return OffenseHistoryRecord.builder().attributes(result.attributes)
                       .linkedPunishments(punishmentList)
                       .criminalUUID(offenseProcessingRequest.getCriminalUUID())
                       .moderatorUUID(offenseProcessingRequest.getModeratorUUID()).timeregistered(java.lang.System.currentTimeMillis()).build();
    }
    
    
    
    @Override
    protected void onStartup(boolean force) throws StartupException {
        //implement if needed
    }
    
    @Override
    protected void onShutdown() throws ShutDownException {
        //implement if needed
    }
    
    @Override
    protected void onKill() throws ShutDownException {
        //implement if needed
    }
}
