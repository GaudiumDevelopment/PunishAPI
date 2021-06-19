package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;

/**
 * This is the base class of the whole API. This is the only class where there may be interacted with.
 */

public class PunishAPI {
    
    private final PunishCore core;
    
    public PunishAPI() {
        core = new PunishCore();
    }
    
    public void startup() throws StartupException {
        core.startup();
    }
    public void shutdown() throws ShutDownException {
        core.shutdown();
    }
    public void kill() throws ShutDownException {
        core.kill();
    }
    
    public SystemStatus status() {
        return core.status();
    }
    
    
}
