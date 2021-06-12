package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.tests.testObjects.TestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataManagerOperationTests {
    
    
    @Test
    void addServiceTest() throws Exception {
        TestServiceImpl service = new TestServiceImpl();
        PunishAPI punishAPI = new PunishAPI();
        punishAPI.startup();
        punishAPI.getDatamanager().addService(DataAPI.ServiceType.TEST, service);
        Assertions.assertSame(service, punishAPI.getDatamanager().getService(DataAPI.ServiceType.TEST));
        TestServiceImpl serviceFromApi = (TestServiceImpl) punishAPI.getDatamanager().getService(DataAPI.ServiceType.TEST);
        
    }
}