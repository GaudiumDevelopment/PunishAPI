package me.superbiebel.punishapi.tests.punishcoretests;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PunishCoreStartupTests {
    
    //test if the boolean is
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanStartupTest() throws StartupException {
        PunishCore core = new PunishCore();
        core.startup();
        assertSame(SystemStatus.READY, core.status());
    }
    
    // test if the api is started up twice, it should throw an exception.
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleStartupTest(int times) throws StartupException {
        PunishCore core = new PunishCore();
        core.startup();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(StartupException.class, core::startup);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
    
    
}
