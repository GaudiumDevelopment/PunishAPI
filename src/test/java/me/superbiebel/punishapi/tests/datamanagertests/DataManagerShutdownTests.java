package me.superbiebel.punishapi.tests.datamanagertests;

import me.superbiebel.punishapi.Datamanager;
import me.superbiebel.punishapi.api.PunishAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DataManagerShutdownTests {
    
    @Test
    public void readyBooleanShutdownTestTest() {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.shutdown();
        Assertions.assertFalse(datamanager.isReady());
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void multipleShutdownTest(int times) {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.shutdown();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, datamanager::shutdown);
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void multipleKilledShutdownTest(int times) {
        Datamanager datamanager = new Datamanager();
        datamanager.startup();
        datamanager.kill();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, datamanager::kill);
        }
    }
}
