package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class DataManagerOperationTests {
    
    
    @Test
    void addServiceTest() throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException {
        TestServiceImpl service = new TestServiceImpl();
        PunishCore punishCore = new PunishCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        assertSame(service, punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST));
        TestServiceImpl serviceFromApi = (TestServiceImpl) punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST);
        assertSame(SystemStatus.READY, serviceFromApi.status());
    }
    @ParameterizedTest
    @ValueSource(booleans = {false,true})
    void removeServiceTest(boolean kill) throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException, ShutDownException {
        TestServiceImpl service = new TestServiceImpl();
        PunishCore punishCore = new PunishCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        TestServiceImpl removedService = (TestServiceImpl) punishCore.getDatamanager().removeService(Datamanager.DataServiceType.TEST, kill);
        assertEquals(removedService.getStatus().get(), kill ? SystemStatus.KILLED : SystemStatus.DOWN);
    }
    @Test
    void getService() throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException {
        TestServiceImpl service = new TestServiceImpl();
        PunishCore punishCore = new PunishCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST);
        assertSame(service, punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST));
    }
}