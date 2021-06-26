package me.superbiebel.punishapi.offenseprocessing;

import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.services.Service;

import java.util.concurrent.ConcurrentHashMap;

public class OffenseManager extends ServiceRegistry<OffenseManager.offenseProcessorType> {
    public OffenseManager() {
        super(new ConcurrentHashMap<>());
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
    protected void onServiceRegistryEmptyingBegin(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingEnd(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRemovedEnd(offenseProcessorType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRemovedMiddle(offenseProcessorType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRemovedBegin(offenseProcessorType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //implement if needed
    }
    
    @Override
    protected void onServiceAddedEnd(offenseProcessorType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //implement if needed
    }
    
    @Override
    protected void onServiceAddedMiddle(offenseProcessorType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //implement if needed
    }
    
    @Override
    protected void onServiceAddedBegin(offenseProcessorType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //implement if needed
    }
    
    public enum offenseProcessorType {
        TEST, COMMAND
    }
}
