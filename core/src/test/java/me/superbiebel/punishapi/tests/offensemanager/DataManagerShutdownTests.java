package me.superbiebel.punishapi.tests.offensemanager;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.OffenseManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DataManagerShutdownTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanShutdownTestTest() throws ShutDownException, StartupException {
        PunishCore core = new PunishCore();
        OffenseManager offenseManager = new OffenseManager(core);
        offenseManager.startup();
        offenseManager.shutdown();
        assertSame(SystemStatus.DOWN, offenseManager.status());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleShutdownTest(int times) throws ShutDownException, StartupException {
        PunishCore core = new PunishCore();
        OffenseManager offenseManager = new OffenseManager(core);
        offenseManager.startup();
        offenseManager.shutdown();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(ShutDownException.class, offenseManager::shutdown);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void killTest() throws StartupException, ShutDownException {
        PunishCore core = new PunishCore();
        OffenseManager offenseManager = new OffenseManager(core);
        offenseManager.startup();
        offenseManager.kill();
        assertSame(SystemStatus.KILLED,offenseManager.status());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleKilledShutdownTest(int times) throws StartupException, ShutDownException {
        PunishCore core = new PunishCore();
        OffenseManager offenseManager = new OffenseManager(core);
        offenseManager.startup();
        offenseManager.kill();
        for (int i = 1; i<=times;i++) {
            assertThrows(ShutDownException.class, offenseManager::kill);
            assertSame(SystemStatus.KILLED,offenseManager.status());
        }
    }
}
