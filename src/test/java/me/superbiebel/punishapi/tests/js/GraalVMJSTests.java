package me.superbiebel.punishapi.tests.js;

import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.dataobjects.scriptobjects.OffenseScriptProcessingResult;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
    
    @Test //offenseProcessingTestFile1.js
    void offenseProcessingJSProcessingTest() throws IOException, InterruptedException, StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        OffenseProcessingRequest offenseProcessingRequest = OffenseProcessingRequest.builder()
                                                                    .processingTemplateUUID(UUID.randomUUID())
                                                                    .criminalUUID(UUID.randomUUID())
                                                                    .moderatorUUID(UUID.randomUUID())
                                                                    .attributes(new HashMap<>())
                                                                    .build();
        File scriptFile = new File(getClass().getClassLoader().getResource("offenseProcessingTestFiles/offenseProcessingTestFile1.js").getFile());
        OffenseProcessingTemplate offenseProcessingTemplate = OffenseProcessingTemplate.builder().scriptFile(scriptFile).offenseProcessingTemplateUUID(UUID.randomUUID()).build();
        OffenseScriptProcessingResult result = api.getCore().getOffenseManager().getDefaultOffenseProcessor().processScript(offenseProcessingRequest, offenseProcessingTemplate.getScriptFile());
        assertEquals("testvalueresult", result.attributes.get("testkeyresult"));
        assertEquals("testvaluepunishment",result.punishments.get(0).attributes.get("testkeypunishment"));
        assertEquals(1000,result.punishments.get(0).startTime);
        assertEquals(2000,result.punishments.get(0).duration);
        assertTrue(result.punishments.get(0).activated);
        assertEquals("MINECRAFT",result.punishments.get(0).scopes.get(0));
        assertEquals(123160, result.punishments.get(0).punishmentReductionList.get(0).priority);
        assertEquals(65464, result.punishments.get(0).punishmentReductionList.get(0).amountSubtracted);
        assertEquals("testvaluepunishmentreductionattributes", result.punishments.get(0).punishmentReductionList.get(0).attributes.get("testkeypunishmentreductionattributes"));
    }
    @Test //importVariablesJSTest.js
    void offenseProcessingJSImportVariablesTest() throws IOException, StartupException {
        PunishAPI api = new PunishAPI();
        api.startup();
        OffenseProcessingRequest offenseProcessingRequest = OffenseProcessingRequest.builder()
                                                                    .processingTemplateUUID(UUID.randomUUID())
                                                                    .criminalUUID(UUID.randomUUID())
                                                                    .moderatorUUID(UUID.randomUUID())
                                                                    .attributes(new HashMap<>())
                                                                    .build();
        File scriptFile = new File(getClass().getClassLoader().getResource("offenseProcessingTestFiles/importVariablesJSTest.js").getFile());
        OffenseProcessingTemplate offenseProcessingTemplate = OffenseProcessingTemplate.builder().scriptFile(scriptFile).offenseProcessingTemplateUUID(UUID.randomUUID()).build();
        assertDoesNotThrow(()->api.getCore().getOffenseManager().getDefaultOffenseProcessor().processScript(offenseProcessingRequest, offenseProcessingTemplate.getScriptFile()));
        
    }
    
}
