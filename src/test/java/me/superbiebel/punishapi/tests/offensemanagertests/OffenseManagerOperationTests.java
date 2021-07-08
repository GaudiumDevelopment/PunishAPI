package me.superbiebel.punishapi.tests.offensemanagertests;

import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OffenseManagerOperationTests {
    
    OffenseHistoryRecord historyRecord;
    
    @Test
    void offenseManagerConverterTest() throws StartupException {
        OffenseScriptProcessingResult scriptProcessingResult = new OffenseScriptProcessingResult();
        scriptProcessingResult.linkedScriptPunishmentObjects = new ArrayList<>();
    
        
        //create new ScriptPunishmentObject
        ScriptPunishmentObject scriptPunishmentObject = new ScriptPunishmentObject();
        scriptPunishmentObject.attributes = new HashMap<>();
        scriptPunishmentObject.attributes.put("testkey","testvalue"); //assertEquals(historyRecord.getLinkedPunishments().get(0).getAttributes(), scriptProcessingResult.linkedScriptPunishmentObjects.get(0).attributes);
        scriptPunishmentObject.activated = true; //assertSame(historyRecord.getLinkedPunishments().get(0).isActivated(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).activated);
        scriptPunishmentObject.decrementsDuration = false; //assertSame(historyRecord.getLinkedPunishments().get(0).isDecrementsDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).decrementsDuration);
        scriptPunishmentObject.startTime = 209098088L; //assertSame(historyRecord.getLinkedPunishments().get(0).getStartTime(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).startTime);
        scriptPunishmentObject.duration = 54467864546L;//assertSame(historyRecord.getLinkedPunishments().get(0).getDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).duration);
        scriptPunishmentObject.scopes = new ArrayList<>();//assertSame(historyRecord.getLinkedPunishments().get(0).getScopes()[0],scriptProcessingResult.linkedScriptPunishmentObjects.get(0).scopes.get(0));
        scriptPunishmentObject.scopes.add("DISCORD");//assertSame(historyRecord.getLinkedPunishments().get(0).getScopes()[0],scriptProcessingResult.linkedScriptPunishmentObjects.get(0).scopes.get(0));
        scriptPunishmentObject.scopes.add("MINECRAFT");//assertSame(historyRecord.getLinkedPunishments().get(0).getScopes()[1],scriptProcessingResult.linkedScriptPunishmentObjects.get(0).scopes.get(1));
        scriptPunishmentObject.punishmentReductionScriptObjects = new ArrayList<>();
        scriptProcessingResult.linkedScriptPunishmentObjects.add(scriptPunishmentObject);
        
        //create new punishmentReductionScriptObject
        PunishmentReductionScriptObject punishmentReductionScriptObject = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject.priority = 98909; //assertSame(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions()[0].getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(0).priority);
        punishmentReductionScriptObject.amountSubtracted = 94976;//assertSame(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions()[0].getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(0).amountSubtracted);
    
        PunishmentReductionScriptObject punishmentReductionScriptObject2 = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject2.priority = 6546; //assertSame(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions()[1].getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(1).priority);
        punishmentReductionScriptObject2.amountSubtracted = 5766221;//assertSame(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions()[0].getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(1).amountSubtracted);
        
        scriptPunishmentObject.punishmentReductionScriptObjects.add(punishmentReductionScriptObject);
        scriptPunishmentObject.punishmentReductionScriptObjects.add(punishmentReductionScriptObject2);
        
        //create new ScriptPunishmentObject
        ScriptPunishmentObject scriptPunishmentObject2 = new ScriptPunishmentObject();
        scriptPunishmentObject2.attributes = new HashMap<>();
        scriptPunishmentObject2.attributes.put("testkeySOMETHING","testvalueALSOSOMETHING");//assertEquals(historyRecord.getLinkedPunishments().get(1).getAttributes(), scriptProcessingResult.linkedScriptPunishmentObjects.get(1).attributes);
        scriptPunishmentObject2.activated = true;//assertSame(historyRecord.getLinkedPunishments().get(1).isActivated(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).activated);
        scriptPunishmentObject2.decrementsDuration = false;//assertSame(historyRecord.getLinkedPunishments().get(1).isDecrementsDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).decrementsDuration);
        scriptPunishmentObject2.startTime = 56463216546L;//assertSame(historyRecord.getLinkedPunishments().get(1).getStartTime(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).startTime);
        scriptPunishmentObject2.duration = 564562146515L;//assertSame(historyRecord.getLinkedPunishments().get(1).getDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).duration);
        scriptPunishmentObject2.scopes = new ArrayList<>();
        scriptPunishmentObject2.scopes.add("SOMETHINGRANDOM");//assertSame(historyRecord.getLinkedPunishments().get(1).getScopes()[0],scriptProcessingResult.linkedScriptPunishmentObjects.get(1).scopes.get(0));
        scriptPunishmentObject2.scopes.add("STEAM");//assertSame(historyRecord.getLinkedPunishments().get(1).getScopes()[1],scriptProcessingResult.linkedScriptPunishmentObjects.get(1).scopes.get(1));
        scriptPunishmentObject2.punishmentReductionScriptObjects = new ArrayList<>();
    
        scriptProcessingResult.linkedScriptPunishmentObjects.add(scriptPunishmentObject);
        
        //create new punishmentReductionScriptObject
        PunishmentReductionScriptObject punishmentReductionScriptObject3 = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject3.priority = 68461525;//assertSame(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions()[0].getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(0).priority);
        punishmentReductionScriptObject3.amountSubtracted = 45565;//assertSame(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions()[0].getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(0).amountSubtracted);
        
        scriptPunishmentObject2.punishmentReductionScriptObjects.add(punishmentReductionScriptObject3);
        
        PunishmentReductionScriptObject punishmentReductionScriptObject4 = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject4.priority = 564654;//assertSame(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions()[0].getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(1).priority);
        punishmentReductionScriptObject4.amountSubtracted = 23587;//assertSame(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions()[0].getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(1).amountSubtracted);
    
        scriptPunishmentObject2.punishmentReductionScriptObjects.add(punishmentReductionScriptObject4);
        
        UUID criminalUUID = UUID.randomUUID();
        UUID moderatorUUID = UUID.randomUUID();
        
        
        OffenseProcessingRequest offenseProcessingRequest = OffenseProcessingRequest.builder().attributes(new HashMap<>())
                                                                    .criminalUUID(criminalUUID)
                                                                    .moderatorUUID(moderatorUUID)
                                                                    .processingTemplateUUID(UUID.randomUUID())
                                                                    .build();
        
        PunishAPI api = new PunishAPI();
        api.startup();
        
        historyRecord = api.getCore().getOffenseManager().convertToOffenseHistoryRecord(scriptProcessingResult,offenseProcessingRequest);
    
        assertEquals(historyRecord.getLinkedPunishments().get(0).getAttributes(), scriptProcessingResult.linkedScriptPunishmentObjects.get(0).attributes);
        assertEquals(historyRecord.getLinkedPunishments().get(0).isActivated(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).activated);
        assertEquals(historyRecord.getLinkedPunishments().get(0).isDecrementsDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).decrementsDuration);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getStartTime(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).startTime);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).duration);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getScopes().get(0),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).scopes.get(0));
        assertEquals(historyRecord.getLinkedPunishments().get(0).getScopes().get(0),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).scopes.get(0));
        assertEquals(historyRecord.getLinkedPunishments().get(0).getScopes().get(1),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).scopes.get(1));
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(0).getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(0).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(0).getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(0).amountSubtracted);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(1).getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(1).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(1).getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(0).punishmentReductionScriptObjects.get(1).amountSubtracted);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getAttributes(), scriptProcessingResult.linkedScriptPunishmentObjects.get(1).attributes);
        assertEquals(historyRecord.getLinkedPunishments().get(1).isActivated(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).activated);
        assertEquals(historyRecord.getLinkedPunishments().get(1).isDecrementsDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).decrementsDuration);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getStartTime(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).startTime);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getDuration(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).duration);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getScopes().get(0),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).scopes.get(0));
        assertEquals(historyRecord.getLinkedPunishments().get(1).getScopes().get(1),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).scopes.get(1));
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(0).getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(0).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(0).getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(0).amountSubtracted);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(1).getPriority(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(1).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(1).getAmountSubtracted(),scriptProcessingResult.linkedScriptPunishmentObjects.get(1).punishmentReductionScriptObjects.get(1).amountSubtracted);
        
    }
}
