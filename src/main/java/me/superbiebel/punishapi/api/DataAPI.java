package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.services.Service;

public class DataAPI {
    private final PunishCore core;
    
    public DataAPI(PunishCore core) {
        this.core = core;
    }
    public void addService(Datamanager.DataServiceType dataServiceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        core.getDatamanager().addService(dataServiceType, service);
    }
    //thread safe
    public void removeService(Datamanager.DataServiceType dataServiceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        core.getDatamanager().removeService(dataServiceType,kill);
    }
}
