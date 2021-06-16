package me.superbiebel.punishapi;


import lombok.Getter;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.data.Datamanager;
import org.apache.logging.log4j.LogManager;

//multiple instances of this class can be created!
public class PunishCore extends System {
    
    @Getter
    private DataAPI dataAPI;
    @Getter
    private Datamanager datamanager;
    
    protected void onStartup(boolean forcedInit) {
        LogManager.getLogger().debug("Starting up PunishAPI");
        datamanager = new Datamanager();
        datamanager.startup();
        dataAPI = new DataAPI(this);
    }
    protected void onShutdown() {
        LogManager.getLogger().debug("Shutting down PunishAPI");
        datamanager.shutdown();
    }
    protected void onKill() {
        LogManager.getLogger().debug("Killing PunishAPI");
        datamanager.kill();
    }
}
