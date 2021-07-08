package me.superbiebel.punishapi.tests.js;

import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.OffenseManager;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseScriptProcessingResult;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GraalVMJSTests {
    
    @Test
    void basicJSTest() {
        Context polyglot = Context.create();
        Value array = polyglot.eval("js", "1+1");
        int result = array.asInt();
        assertSame(2,result);
    }
    
    @Disabled("js file must be rewritten")
    @Test //offenseProcessingTestFile1.js
    void offenseProcessingTest() throws IOException, InterruptedException, StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        OffenseManager offenseManager = Objects.requireNonNull(api.getCore().getOffenseManager());
        OffenseProcessingRequest offenseProcessingRequest = OffenseProcessingRequest.builder()
                                                                    .processingTemplateUUID(UUID.randomUUID())
                                                                    .criminalUUID(UUID.randomUUID())
                                                                    .moderatorUUID(UUID.randomUUID())
                                                                    .attributes(new HashMap<>())
                                                                    .build();
        File scriptFile = new File(getClass().getClassLoader().getResource("offenseProcessingTestFiles/offenseProcessingTestFile1.js").getFile());
        OffenseProcessingTemplate offenseProcessingTemplate = OffenseProcessingTemplate.builder().scriptFile(scriptFile).offenseProcessingTemplateUUID(UUID.randomUUID()).build();
        OffenseScriptProcessingResult result = offenseManager.processScript(offenseProcessingRequest, offenseProcessingTemplate);
        assertEquals("testvalue", result.attributes.get("testkey"));
        assertEquals("punishmenttestvalue",result.linkedScriptPunishmentObjects.get(0).attributes.get("punishmenttestkey"));
        assertEquals(1000,result.linkedScriptPunishmentObjects.get(0).startTime);
        assertEquals(3000,result.linkedScriptPunishmentObjects.get(0).duration);
        assertTrue(result.linkedScriptPunishmentObjects.get(0).activated);
        assertEquals("testscope",result.linkedScriptPunishmentObjects.get(0).scopes.get(0));
    }
    
}
