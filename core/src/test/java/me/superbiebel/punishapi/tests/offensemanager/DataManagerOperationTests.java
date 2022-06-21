package me.superbiebel.punishapi.tests.offensemanager;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.tests.testobjects.TestDataServiceImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class DataManagerOperationTests {
    
    
    @Test
    void addServiceTest() throws Exception {
        TestDataServiceImpl service = new TestDataServiceImpl();
        PunishCore punishCore = new PunishCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        assertSame(service, punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST));
        TestDataServiceImpl serviceFromApi = (TestDataServiceImpl) punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST);
        assertSame(SystemStatus.READY, serviceFromApi.status());
    }
}