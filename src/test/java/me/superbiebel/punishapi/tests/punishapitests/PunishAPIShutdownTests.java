package me.superbiebel.punishapi.tests.punishapitests;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PunishAPIShutdownTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanShutdownTestTest() throws ShutDownException, StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        api.shutdown();
        Assertions.assertSame(SystemStatus.DOWN, api.status());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleShutdownTest(int times) throws ShutDownException, StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        api.shutdown();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(ShutDownException.class, api::shutdown);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleKilledShutdownTest(int times) throws StartupException, ShutDownException {
        PunishAPI api = new PunishAPI();
        api.startup();
        api.kill();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(ShutDownException.class, api::kill);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
}
