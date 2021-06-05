package me.superbiebel.punishapi.tests.punishapitests;

import me.superbiebel.punishapi.api.PunishAPI;
import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PunishAPIStartupTests {
    
    //test if the boolean is
    @Test
    public void readyBooleanSartupTest() {
        PunishAPI api = new PunishAPI();
        api.startup();
        Assertions.assertTrue(api.isReady());
    }
    
    // test if the api is started up twice, it should throw an exception.
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void multipleStartupTest(int times) {
        PunishAPI api = new PunishAPI();
        api.startup();
        for (int i = 1; i<=times;i++) {
            Assertions.assertThrows(IllegalStateException.class, api::startup);
        }
    }
    
    
}
