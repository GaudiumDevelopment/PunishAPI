package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.services.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataManagerOperationTests {
    
    @Test
    public void addServiceTest() throws Exception {
        PunishAPI punishAPI = new PunishAPI();
        punishAPI.startup();
        Service service = new Service() {
            @Override
            public void startup(boolean force) throws Exception {
        
            }
    
            @Override
            public void shutdown() throws Exception {
        
            }
    
            @Override
            public void kill() throws Exception {
        
            }
        };
        punishAPI.getDatamanager().addService(DataAPI.ServiceType.TEST, service);
        Assertions.assertSame(service, punishAPI.getDatamanager().getService(DataAPI.ServiceType.TEST));
        
    }
}
