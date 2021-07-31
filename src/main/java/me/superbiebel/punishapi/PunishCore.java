package me.superbiebel.punishapi;


import lombok.Getter;
import lombok.Setter;
import me.superbiebel.punishapi.abstractions.System;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.OffenseManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

public class PunishCore extends System {
    
    @Getter
    private Datamanager datamanager;
    @Getter
    private OffenseManager offenseManager;
    @Getter
    @Setter
    private DataAPI dataAPI;
    
    @Override
    protected void onStartup(boolean forcedInit) throws StartupException {
        datamanager = new Datamanager();
        datamanager.startup();
        offenseManager = new OffenseManager(this);
        offenseManager.startup();
    }
    @Override
    protected void onShutdown() throws ShutDownException {
        datamanager.shutdown();
        offenseManager.shutdown();
    }
    @Override
    protected void onKill() throws ShutDownException {
        datamanager.kill();
        offenseManager.kill();
    }

    public void setLogLevel(Level logLevel) {
        final LoggerContext context = (LoggerContext) LogManager.getContext(false);
        final Configuration config = context.getConfiguration();
        config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(logLevel);
        context.updateLoggers(config);
    }
}
