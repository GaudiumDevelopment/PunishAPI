package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.data.services.Service;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;

/**
 * This is the base class of the whole API. This is the only class where there may be interacted with.
 */

public class PunishAPI {
    
    private PunishCore core;
    private SystemStatus status = SystemStatus.DOWN;
    
    public void startup() throws StartupException {
        core = new PunishCore();
        core.startup();
    }
    public void shutdown() throws ShutDownException {
        core.shutdown();
    }
    public void kill() throws ShutDownException {
        core.kill();
    }
    public void addService(PunishAPI.ServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        core.getDatamanager().addService(serviceType, service);
    }
    //thread safe
    public void removeService(ServiceType serviceType,boolean kill) throws ShutDownException, ServiceNotFoundException {
        core.getDatamanager().removeService(serviceType,kill);
    }
    public SystemStatus status() {
        return status;
    }
    
    public enum ServiceType {
        TEST
    }
}
