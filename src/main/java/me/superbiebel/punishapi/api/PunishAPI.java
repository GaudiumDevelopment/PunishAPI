package me.superbiebel.punishapi.api;


import lombok.Getter;
import me.superbiebel.punishapi.System;
import me.superbiebel.punishapi.data.Datamanager;
import org.apache.logging.log4j.LogManager;

//multiple instances of this class can be created!
public class PunishAPI extends System {
    
    @Getter
    private DataAPI dataAPI;
    @Getter
    private Datamanager datamanager;
    
    public void onStartup(boolean forcedInit) {
        LogManager.getLogger().debug("Starting up PunishAPI");
        datamanager = new Datamanager();
        datamanager.startup();
        dataAPI = new DataAPI(this);
    }
    public void onShutdown() {
    }
    public void onKill() {
    }
}
