package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.services.Service;
import org.jetbrains.annotations.Nullable;

public class DataAPI {
    private final PunishCore core;
    
    public DataAPI(PunishCore core) {
        this.core = core;
    }
    public void addService(Datamanager.DataServiceType dataServiceType, DataService service) throws StartupException, ServiceAlreadyRegisteredException {
        core.getDatamanager().addService(dataServiceType, service);
    }
    @Nullable
    public Service<Datamanager.DataServiceType> removeService(Datamanager.DataServiceType dataServiceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        return core.getDatamanager().removeService(dataServiceType,kill);
    }
    public Service<Datamanager.DataServiceType> getService(Datamanager.DataServiceType dataServiceType) throws ServiceNotFoundException {
        return core.getDatamanager().getService(dataServiceType);
    }
    
    
}
