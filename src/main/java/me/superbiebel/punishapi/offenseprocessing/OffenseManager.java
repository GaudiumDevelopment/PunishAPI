package me.superbiebel.punishapi.offenseprocessing;

import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.exceptions.*;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.services.Service;

import java.util.concurrent.ConcurrentHashMap;

public class OffenseManager extends ServiceRegistry<String> {
    public OffenseManager() {
        super(new ConcurrentHashMap<>());
    }
    
    
    public void submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException {
        throw new UnsupportedOperationException("Coming soon!");
    }
    
    
    
    
    @Override
    public void onServiceRegistryStartup(boolean force) throws StartupException {
        //implement if needed
    }
    
    @Override
    public void onServiceRegistryShutdown() throws ShutDownException {
        //implement if needed
    }
    
    @Override
    public void onServiceRegistryKill() throws ShutDownException {
        //implement if needed
    }
    
    @Override
    protected void onServiceAddedBegin(String serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //implement if needed
    }
    
    @Override
    protected void onServiceAddedMiddle(String serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //implement if needed
    }
    
    @Override
    protected void onServiceAddedEnd(String serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRemovedBegin(String serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRemovedMiddle(String serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRemovedEnd(String serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingBegin(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingEnd(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
}
