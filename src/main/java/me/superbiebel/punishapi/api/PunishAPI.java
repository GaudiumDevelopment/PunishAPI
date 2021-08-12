package me.superbiebel.punishapi.api;

import lombok.Getter;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.System;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.apache.logging.log4j.LogManager;


/**
 * This is the base class of the whole API. This is the only class where there may be interacted with.
 */

public class PunishAPI extends System {

    private final PunishCore core; //do NOT interact with this!
    @Getter
    private DataAPI dataAPI;
    @Getter
    private OffenseAPI offenseAPI;

    public PunishAPI() {
        core = new PunishCore();

    }

    public void onStartup(boolean force) throws StartupException {
        core.startup(force);
        dataAPI = new DataAPI(core);
        offenseAPI = new OffenseAPI(core);
    }

    public void onShutdown() throws ShutDownException {
        core.shutdown();
    }

    public void onKill() throws ShutDownException {
        core.kill();
    }

    public PunishCore getCore() {
        LogManager.getLogger().warn("A reference of Punishcore has just been acquired. THIS IS ONLY FOR AUTOMATED TESTING PURPOSES AND IT MAY NOT BE EDITED IN ANY WAY NOR ANY OF ITS METHODS MAY BE USED!");
        return core;
    }

}
