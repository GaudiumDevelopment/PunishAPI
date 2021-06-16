package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.SystemStatus;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.exceptions.ShutDownException;

import me.superbiebel.punishapi.exceptions.StartupException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataManagerShutdownTests {
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void readyBooleanShutdownTestTest() throws ShutDownException, StartupException {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.shutdown();
        assertSame(SystemStatus.DOWN, datamanager.status());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleShutdownTest(int times) throws ShutDownException, StartupException {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.shutdown();
        for (int i = 1; i<=times;i++) {
            assertThrows(IllegalStateException.class, datamanager::shutdown);
        }
    }
    
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void killTest() throws StartupException {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.kill();
        assertSame(SystemStatus.KILLED,datamanager.status());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @Execution(ExecutionMode.CONCURRENT)
    void multipleKilledShutdownTest(int times) throws StartupException {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.kill();
        for (int i = 1; i<=times;i++) {
            assertThrows(IllegalStateException.class, datamanager::kill);
            assertSame(SystemStatus.KILLED,datamanager.status());
        }
    }
}
