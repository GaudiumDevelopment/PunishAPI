package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.tests.testobjects.TestServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;


class DataManagerOperationTests {
    
    
    @Test
    void addServiceTest() throws Exception {
        TestServiceImpl service = new TestServiceImpl();
        PunishCore punishCore = new PunishCore();
        punishCore.startup();
        punishCore.getDatamanager().addDataService(PunishAPI.DataServiceType.TEST, service);
        assertSame(service, punishCore.getDatamanager().getDataService(PunishAPI.DataServiceType.TEST));
        TestServiceImpl serviceFromApi = (TestServiceImpl) punishCore.getDatamanager().getDataService(PunishAPI.DataServiceType.TEST);
        assertSame(SystemStatus.READY, serviceFromApi.status());
    }
}