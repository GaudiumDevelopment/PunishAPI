package me.superbiebel.punishapi.tests.punishapitests;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PunishAPIStartupTests {
    
    //test if the boolean is
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanStartupTest() throws StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        assertSame(SystemStatus.READY, api.status());
    }
    
    // test if the api is started up twice, it should throw an exception.
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleStartupTest(int times) throws StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        for (int i = 1; i<=times;i++) {
            Throwable thrown = assertThrows(StartupException.class, api::startup);
            assertEquals(IllegalStateException.class, thrown.getCause().getClass());
        }
    }
    
    
}
