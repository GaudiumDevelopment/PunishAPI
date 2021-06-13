package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.data.Datamanager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DataManagerShutdownTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanShutdownTestTest() {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.shutdown();
        Assertions.assertSame(SystemStatus.DOWN, datamanager.status());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleShutdownTest(int times) {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.shutdown();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, datamanager::shutdown);
        }
    }
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void killTest() {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.kill();
        Assertions.assertSame(SystemStatus.KILLED,datamanager.status());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleKilledShutdownTest(int times) {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.kill();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, datamanager::kill);
            Assertions.assertSame(SystemStatus.KILLED,datamanager.status());
        }
    }
}
