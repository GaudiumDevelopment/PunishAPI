package me.superbiebel.punishapi;


import lombok.Getter;
import me.superbiebel.punishapi.abstractions.System;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.OffenseManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

//multiple instances of this class can be created!
public class PunishCore extends System {
    
    @Getter
    private Datamanager datamanager;
    @Getter
    private OffenseManager offenseManager;
    
    @Override
    protected void onStartup(boolean forcedInit) throws StartupException {
        setLogLevel(Level.ALL);
        LogManager.getLogger().debug("Starting up PunishAPI");
        datamanager = new Datamanager();
        datamanager.startup();
        offenseManager = new OffenseManager(this);
        offenseManager.startup();
    }
    @Override
    protected void onShutdown() throws ShutDownException {
        LogManager.getLogger().debug("Shutting down PunishAPI");
        datamanager.shutdown();
        offenseManager.shutdown();
    }
    @Override
    protected void onKill() throws ShutDownException {
        LogManager.getLogger().debug("Killing PunishAPI");
        datamanager.kill();
        offenseManager.kill();
    }
    public void setLogLevel(Level logLevel) {
        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();
        config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(logLevel);
        context.updateLoggers(config);
    }
}
