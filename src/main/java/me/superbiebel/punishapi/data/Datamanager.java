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
    private static final int MAXSERVICECOUNT = 1;
    
    public Datamanager() {
        super(new ConcurrentHashMap<>());
    }
    
    //locked so threadsafe
    @Override
    public void onServiceRegistryStartup(boolean force) {
        //to be implemented if needed
    }
    //locked so threadsafe
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
    //locked so threadsafe
    @Override
    public void onServiceRegistryKill() {
        serviceRegistryMap.keys().asIterator().forEachRemaining(dataServiceType -> {
            try {
                this.removeService(dataServiceType, true);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
            }
        });
    }
    //thread safe becuz of specialised datatype
    public Service getDataService(Datamanager.DataServiceType dataServiceType) {
        Service service = serviceRegistryMap.get(dataServiceType);
        if (service == null) {
            throw new IllegalArgumentException("Servicetype not found");
        }
        return service;
    }
    public int serviceCount() {
        return serviceRegistryMap.size();
    }
    
    @Override
    protected void onServiceAddedBegin(DataServiceType serviceType, Service service) throws StartupException, ServiceAlreadyRegisteredException {
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
    
    public enum DataServiceType {
        TEST
    }
}
