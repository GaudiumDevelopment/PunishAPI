package me.superbiebel.punishapi.offenseprocessing.services.premade.jsoffenseprocessor;

import java.io.File;
import java.io.IOException;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;
import me.superbiebel.punishapi.offenseprocessing.services.AbstractOffenseProcessor;
import me.superbiebel.punishapi.offenseprocessing.services.premade.jsoffenseprocessor.scriptobjects.OffenseHistoryRecordScriptObject;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.jetbrains.annotations.NotNull;

public class JSOffenseProcessor extends AbstractOffenseProcessor {
    
    private final PunishCore core;
    
    public JSOffenseProcessor(PunishCore core) {
        this.core = core;
    }
    
    @Override
    protected OffenseHistoryRecord process(OffenseProcessingRequest offenseProcessingRequest, File scriptFile) throws OffenseProcessingException {
        try {
            return ScriptObjectConverter.convertOffenseScriptProcessingResultToOffenseHistoryRecord(processScript(offenseProcessingRequest, scriptFile), offenseProcessingRequest, true);
        } catch (Exception e) {
            throw new OffenseProcessingException(e);
        }
    }
    
    @Override
    public void serviceStartup(boolean force) {
        //to be implemented if needed
    }
    
    @NotNull
    public OffenseHistoryRecordScriptObject processScript(OffenseProcessingRequest offenseProcessingRequest, File scriptFile) throws IOException {
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            Source source = Source.newBuilder("js", scriptFile).build();
            Value jsBindings = context.getBindings("js");
            jsBindings.putMember("request", offenseProcessingRequest);
            jsBindings.putMember("datamanager", core.getDatamanager()); //TODO: change this to a new facade with adapted objects and a converter otherwise it can't be accessed from the script.
            context.eval(source);
            Value verdictValue = jsBindings.getMember("verdict");
            return verdictValue.as(OffenseHistoryRecordScriptObject.class);
        }
        
    }
    
    @Override
    public void serviceShutdown() {
        //to be implemented if needed
    }
    
    @Override
    public void serviceKill() {
        //to be implemented if needed
    }
    
    @Override
    public String serviceType() {
        return "JSGRAALVM";
    }
    
    
    @Override
    public boolean isScriptBased() {
        return true; //yes becuz it uses js scripts.
    }
}
