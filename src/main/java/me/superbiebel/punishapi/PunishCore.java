package me.superbiebel.punishapi;


import lombok.Getter;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.apache.logging.log4j.LogManager;

//multiple instances of this class can be created!
public class PunishCore extends System {
    
    @Getter
    private Datamanager datamanager;
    @Override
    protected void onStartup(boolean forcedInit) throws StartupException {
        LogManager.getLogger().debug("Starting up PunishAPI");
        datamanager = new Datamanager();
        datamanager.startup();
    }
    @Override
    protected void onShutdown() throws ShutDownException {
        LogManager.getLogger().debug("Shutting down PunishAPI");
        datamanager.shutdown();
    }
    @Override
    protected void onKill() throws ShutDownException {
        LogManager.getLogger().debug("Killing PunishAPI");
        datamanager.kill();
    }
}
