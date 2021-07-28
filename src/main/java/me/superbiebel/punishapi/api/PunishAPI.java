package me.superbiebel.punishapi.api;

import lombok.Getter;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.apache.logging.log4j.LogManager;


/**
 * This is the base class of the whole API. This is the only class where there may be interacted with.
 */

public class PunishAPI {
    
    private final PunishCore core; //do NOT interact with this!
    @Getter
    private final DataAPI dataAPI;
    
    public PunishAPI() {
        core = new PunishCore();
        dataAPI = new DataAPI(core);
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
    public PunishCore getCore() {
        LogManager.getLogger().warn("A reference of Punishcore has just been acquired. THIS IS ONLY FOR AUTOMATED TESTING PURPOSES AND IT MAY NOT BE EDITED IN ANY WAY NOR ANY OF ITS METHODS MAY BE USED!");
        return core;
    }
    
}
