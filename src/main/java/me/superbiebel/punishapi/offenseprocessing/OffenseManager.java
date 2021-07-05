package me.superbiebel.punishapi.offenseprocessing;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.System;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingResult;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingTemplate;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;

public class OffenseManager extends System {
    
    PunishCore core;
    Context context = Context.create();
    
    public OffenseManager(PunishCore core) {
        this.core = core;
    }
    public OffenseHistoryRecord submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws ServiceNotFoundException, IOException {
        Datamanager datamanager = core.getDatamanager();
        datamanager.lockUser(offenseProcessingRequest.getCriminalUUID());
        OffenseProcessingTemplate offenseProcessingTemplate = datamanager.retrieveOffenseProcessingTemplate(offenseProcessingRequest.getProcessingTemplateUUID());
        Source source = Source.newBuilder("js",offenseProcessingTemplate.getScriptFile()).build();
        Value jsBindings = context.getBindings("js");
        jsBindings.putMember("offenseProcessingRequest",offenseProcessingRequest);
        jsBindings.putMember("datamanager", core.getDatamanager()); //gives limited access to do data operations.
        context.eval(source);
        Value verdictValue = jsBindings.getMember("verdict");
        OffenseProcessingResult verdict = verdictValue.as(OffenseProcessingResult.class);
        OffenseHistoryRecord offenseHistoryRecord =  OffenseHistoryRecord.builder().attributes(verdict.attributes)
                                               .linkedPunishments(verdict.linkedPunishments)
                                               .criminalUUID(offenseProcessingRequest.getCriminalUUID())
                                               .moderatorUUID(offenseProcessingRequest.getModeratorUUID()).timeregistered(java.lang.System.currentTimeMillis()).build();
        core.getDatamanager().storeOffense(offenseHistoryRecord);
        
        return offenseHistoryRecord;
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
