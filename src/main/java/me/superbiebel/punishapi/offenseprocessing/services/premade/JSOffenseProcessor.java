package me.superbiebel.punishapi.offenseprocessing.services.premade;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.services.AbstractOffenseProcessor;
import org.jetbrains.annotations.NotNull;

public class JSOffenseProcessor extends AbstractOffenseProcessor {
    @Override
    protected OffenseHistoryRecord process(OffenseProcessingRequest offenseProcessingRequest) {
        return null;
    }
    
    @Override
    public void serviceStartup(boolean force) throws StartupException {
        //to be implemented if needed
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
}
