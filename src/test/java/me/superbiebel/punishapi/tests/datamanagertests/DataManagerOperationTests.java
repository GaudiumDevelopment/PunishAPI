package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.tests.testObjects.TestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataManagerOperationTests {
    
    
    @Test
    void addServiceTest() throws Exception {
        TestServiceImpl service = new TestServiceImpl();
        PunishCore punishCore = new PunishCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(DataAPI.ServiceType.TEST, service);
        Assertions.assertSame(service, punishCore.getDatamanager().getService(DataAPI.ServiceType.TEST));
        TestServiceImpl serviceFromApi = (TestServiceImpl) punishCore.getDatamanager().getService(DataAPI.ServiceType.TEST);
    }
}