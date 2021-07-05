package me.superbiebel.punishapi.data;


import lombok.Getter;
import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.services.Service;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * FOR INTERNAL USE ONLY!!!!
 */
public class Datamanager extends ServiceRegistry<Datamanager.DataServiceType> {
    
    @Getter
    private static final int MAXSERVICECOUNT = 2;
    
    public Datamanager() {
        super(new ConcurrentHashMap<>());
    }
    
    public enum DataServiceType {
        TEST,OFFENSE_PROCESSING_TEMPLATE_STORAGE,OFFENSE_RECORD_STORAGE,USER_LOCKING
    }
    
    
    
    @Override
    public void onServiceRegistryStartup(boolean force) {
        //to be implemented if needed
    }
    @Override
    public void onServiceRegistryShutdown() {
        serviceRegistryMap.keys().asIterator().forEachRemaining(dataServiceType -> {
            try {
                this.removeService(dataServiceType, false);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
            }
        });
    }
    @Override
    public void onServiceRegistryKill() {
        //to be implemented if needed
    }
    public int serviceCount() {
        return serviceRegistryMap.size();
    }
    
    @Override
    protected void onServiceAddedBegin(DataServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceAddedMiddle(DataServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceAddedEnd(DataServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRemovedBegin(DataServiceType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRemovedMiddle(DataServiceType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRemovedEnd(DataServiceType serviceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingBegin(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRegistryEmptyingEnd(boolean kill) throws ShutDownException, ServiceNotFoundException {
        //to be implemented if needed
    }
}
