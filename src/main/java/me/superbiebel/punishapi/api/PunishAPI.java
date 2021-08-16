package me.superbiebel.punishapi.api;

import lombok.Getter;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.System;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.jetbrains.annotations.ApiStatus;


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
    @ApiStatus.Internal
    public PunishCore getUnsafeCore() {
        return core;
    }

}
