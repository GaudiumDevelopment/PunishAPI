package me.superbiebel.punishapi;


import lombok.Getter;
import lombok.Setter;
import me.superbiebel.punishapi.abstractions.AbstractService;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.OffenseManager;

public class PunishCore extends AbstractService {

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
}
