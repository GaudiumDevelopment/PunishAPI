package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.TestDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


class DataManagerOperationTests {

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void addServiceTest() throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException {
        TestDataServiceImpl service = new TestDataServiceImpl();
        PunishAPI api = new PunishAPI();
        PunishCore punishCore = api.getUnsafeCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        assertSame(service, punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST));
        TestDataServiceImpl serviceFromApi = (TestDataServiceImpl) punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST);
        assertSame(SystemStatus.READY, serviceFromApi.status());
    }
    @ParameterizedTest
    @ValueSource(booleans = {false,true})
    @Execution(ExecutionMode.CONCURRENT)
    void removeServiceTest(boolean kill) throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException, ShutDownException {
        TestDataServiceImpl service = new TestDataServiceImpl();
        PunishAPI api = new PunishAPI();
        PunishCore punishCore = api.getUnsafeCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        TestDataServiceImpl removedService = (TestDataServiceImpl) punishCore.getDatamanager().removeService(Datamanager.DataServiceType.TEST, kill);
        assertEquals(removedService.getStatus().get(), kill ? SystemStatus.KILLED : SystemStatus.DOWN);
    }
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void getService() throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException {
        TestDataServiceImpl service = new TestDataServiceImpl();
        PunishAPI api = new PunishAPI();
        PunishCore punishCore = api.getUnsafeCore();
        punishCore.startup();
        punishCore.getDatamanager().addService(Datamanager.DataServiceType.TEST, service);
        punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST);
        assertSame(service, punishCore.getDatamanager().getService(Datamanager.DataServiceType.TEST));
    }
}