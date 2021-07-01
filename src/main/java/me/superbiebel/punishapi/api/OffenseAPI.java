package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.OffenseManager;
import me.superbiebel.punishapi.services.Service;

public class OffenseAPI {
    private final PunishCore core;
    
    public OffenseAPI(PunishCore core) {
        this.core = core;
    }
    
    public void addService(OffenseManager.OffenseProcessorType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        core.getOffenseManager().addService(serviceType, service);
    }
    public void removeService(OffenseManager.OffenseProcessorType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        core.getOffenseManager().removeService(serviceType,kill);
    }
    public void createOffense(int offenseId) {
        throw new UnsupportedOperationException("Coming soon!");
    }
    public void deleteOffense(int offenseID) {
        throw new UnsupportedOperationException("Coming soon!");
    }
}
