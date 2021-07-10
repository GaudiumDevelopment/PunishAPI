package me.superbiebel.punishapi.tests.dataapi;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataAPIOperationTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void addServiceTest() throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException {
    
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getCore();
        
        api.startup();
        
        TestServiceImpl testService = new TestServiceImpl();
        
        api.getDataAPI().addService(Datamanager.DataServiceType.TEST,testService);
        assertSame(testService,core.getDatamanager().getService(Datamanager.DataServiceType.TEST));
        assertSame(SystemStatus.READY,core.getDatamanager().getService(Datamanager.DataServiceType.TEST).serviceStatus());
    }
    @ParameterizedTest
    @ValueSource(ints = {2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void addMultipleServiceTest(int times) throws StartupException, ServiceNotFoundException, ServiceAlreadyRegisteredException {
    
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getCore();
        api.startup();
        
        TestServiceImpl testService = new TestServiceImpl();
        api.getDataAPI().addService(Datamanager.DataServiceType.TEST,testService);
        
        for (int i = 1; i<=times;i++) {
            assertThrows(ServiceAlreadyRegisteredException.class, ()->api.getDataAPI().addService(Datamanager.DataServiceType.TEST,testService));
        }
        assertSame(testService,core.getDatamanager().getService(Datamanager.DataServiceType.TEST));
        assertSame(SystemStatus.READY,core.getDatamanager().getService(Datamanager.DataServiceType.TEST).serviceStatus());
    }
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void getServiceTest() throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException {
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getCore();
        api.startup();
    
        TestServiceImpl testService = new TestServiceImpl();
        api.getDataAPI().addService(Datamanager.DataServiceType.TEST,testService);
        assertSame(testService,api.getDataAPI().getService(Datamanager.DataServiceType.TEST));
        assertSame(testService,core.getDatamanager().getService(Datamanager.DataServiceType.TEST));
    }
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @Execution(ExecutionMode.CONCURRENT)
    void removeServiceTest(boolean kill) throws StartupException, ServiceAlreadyRegisteredException, ServiceNotFoundException, ShutDownException {
        PunishAPI api = new PunishAPI();
        api.startup();
    
        TestServiceImpl testService = new TestServiceImpl();
        api.getDataAPI().addService(Datamanager.DataServiceType.TEST,testService);
        
        assertSame(kill ? SystemStatus.KILLED : SystemStatus.DOWN, api.getDataAPI().removeService(Datamanager.DataServiceType.TEST,kill).serviceStatus());
    }
    @ParameterizedTest
    @ValueSource(ints = {2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void removeMultipleServiceTest(int times) throws StartupException, ServiceNotFoundException, ServiceAlreadyRegisteredException, ShutDownException {
    
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getCore();
        api.startup();
        
        TestServiceImpl testService = new TestServiceImpl();
        api.getDataAPI().addService(Datamanager.DataServiceType.TEST,testService);
        api.getDataAPI().removeService(Datamanager.DataServiceType.TEST,false);
        
        for (int i = 1; i<=times;i++) {
            assertThrows(ServiceNotFoundException.class, ()->api.getDataAPI().removeService(Datamanager.DataServiceType.TEST,false));
        }
    }
}
