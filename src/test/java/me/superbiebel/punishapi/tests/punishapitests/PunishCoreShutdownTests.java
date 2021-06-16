package me.superbiebel.punishapi.tests.punishapitests;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.PunishCore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PunishCoreShutdownTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanShutdownTestTest() {
        PunishCore api = new PunishCore();
        api.startup();
        api.shutdown();
        Assertions.assertNotSame(SystemStatus.READY, api.status());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleShutdownTest(int times) {
        PunishCore api = new PunishCore();
        api.startup();
        api.shutdown();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, api::shutdown);
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleKilledShutdownTest(int times) {
        PunishCore api = new PunishCore();
        api.startup();
        api.kill();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, api::kill);
        }
    }
}
