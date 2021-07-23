package me.superbiebel.punishapi.offenseprocessing.services.premade;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.Punishment;
import me.superbiebel.punishapi.dataobjects.PunishmentReduction;
import me.superbiebel.punishapi.dataobjects.scriptobjects.OffenseScriptProcessingResult;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.services.AbstractOffenseProcessor;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JSOffenseProcessor extends AbstractOffenseProcessor {
    
    private final PunishCore core;
    
    public JSOffenseProcessor(PunishCore core) {
        this.core = core;
    }
    
    @Override
    protected OffenseHistoryRecord process(OffenseProcessingRequest offenseProcessingRequest, File scriptFile) throws OffenseProcessingException {
        try {
            return convertToOffenseHistoryRecord(processScript(offenseProcessingRequest, scriptFile), offenseProcessingRequest);
        } catch (Exception e) {
            throw new OffenseProcessingException(e);
        }
    }
    
    @Override
    public void serviceStartup(boolean force) throws StartupException {
        //to be implemented if needed
    }
    
    @NotNull
    public OffenseScriptProcessingResult processScript(OffenseProcessingRequest offenseProcessingRequest, File scriptFile) throws IOException {
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            Source source = Source.newBuilder("js", scriptFile).build();
            Value jsBindings = context.getBindings("js");
            jsBindings.putMember("request", offenseProcessingRequest);
            jsBindings.putMember("datamanager", core.getDatamanager()); //TODO: change this to a new facade with adapted objects and a converter otherwise it can't be accessed from the script.
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
    public void serviceShutdown() throws ShutDownException {
        //to be implemented if needed
    }
    
    @Override
    public void serviceKill() {
        //to be implemented if needed
    }
    
    @Override
    public @NotNull SystemStatus serviceStatus() {
        return null; //TODO: set it to a non-null value.
    }
    
    @Override
    public String serviceType() {
        return null; //TODO: set it to a non-null value.
    }
    
    
    @Override
    public boolean isScriptBased() {
        return true; //yes becuz it uses js scripts.
    }
}
