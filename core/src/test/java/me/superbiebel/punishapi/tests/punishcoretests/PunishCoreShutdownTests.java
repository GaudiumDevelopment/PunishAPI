package me.superbiebel.punishapi.tests.punishcoretests;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PunishCoreShutdownTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void statusShutdownTest() throws ShutDownException, StartupException {
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getUnsafeCore();
        core.startup();
        core.shutdown();
        assertSame(SystemStatus.DOWN, core.status());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleShutdownTest(int times) throws ShutDownException, StartupException {
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getUnsafeCore();
        core.startup();
        core.shutdown();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(ShutDownException.class, core::shutdown);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleKilledShutdownTest(int times) throws StartupException, ShutDownException {
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getUnsafeCore();
        core.startup();
        core.kill();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(ShutDownException.class, core::kill);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void statusKilledTest() throws ShutDownException, StartupException {
        PunishAPI api = new PunishAPI();
        PunishCore core = api.getUnsafeCore();
        core.startup();
        core.kill();
        assertSame(SystemStatus.KILLED, core.status());
    }
}
