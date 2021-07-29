package me.superbiebel.punishapi.tests.premadeoffenseprocessors.graalvmjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.OffenseHistoryRecordScriptObject;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.PunishmentReductionScriptObject;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.PunishmentScriptObject;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ScriptObjectConverterTests {


    @Test
    void offenseManagerConverterTest() throws StartupException {
        OffenseHistoryRecord historyRecord;
        OffenseHistoryRecordScriptObject scriptProcessingResult = new OffenseHistoryRecordScriptObject();
        scriptProcessingResult.punishments = new ArrayList<>();



        PunishmentScriptObject punishmentScriptObject = new PunishmentScriptObject();
        punishmentScriptObject.attributes = new HashMap<>();
        punishmentScriptObject.attributes.put("testkey","testvalue");
        punishmentScriptObject.activated = true;
        punishmentScriptObject.decrementsDuration = false;
        punishmentScriptObject.startTime = 209098088L;
        punishmentScriptObject.duration = 54467864546L;
        punishmentScriptObject.scopes = new ArrayList<>();
        punishmentScriptObject.scopes.add("DISCORD");
        punishmentScriptObject.scopes.add("MINECRAFT");
        punishmentScriptObject.punishmentReductions = new ArrayList<>();
        scriptProcessingResult.punishments.add(punishmentScriptObject);


        PunishmentReductionScriptObject punishmentReductionScriptObject = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject.priority = 98909;
        punishmentReductionScriptObject.amountSubtracted = 94976;
        punishmentReductionScriptObject.attributes = new HashMap<>();
        punishmentReductionScriptObject.attributes.put("testkeyATTRIBUTESOMETHING","testvalueATTRIBUTESOMETHING");

        PunishmentReductionScriptObject punishmentReductionScriptObject2 = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject2.priority = 6546;
        punishmentReductionScriptObject2.amountSubtracted = 5766221;
        punishmentReductionScriptObject2.attributes = new HashMap<>();
        punishmentReductionScriptObject2.attributes.put("testkeyATTRIBUTESOMETHING","testvalueATTRIBUTESOMETHING");

        punishmentScriptObject.punishmentReductions.add(punishmentReductionScriptObject);
        punishmentScriptObject.punishmentReductions.add(punishmentReductionScriptObject2);


        PunishmentScriptObject punishmentScriptObject2 = new PunishmentScriptObject();
        punishmentScriptObject2.attributes = new HashMap<>();
        punishmentScriptObject2.attributes.put("testkeySOMETHING","testvalueALSOSOMETHING");
        punishmentScriptObject2.activated = true;
        punishmentScriptObject2.decrementsDuration = false;
        punishmentScriptObject2.startTime = 56463216546L;
        punishmentScriptObject2.duration = 564562146515L;
        punishmentScriptObject2.scopes = new ArrayList<>();
        punishmentScriptObject2.scopes.add("SOMETHINGRANDOM");
        punishmentScriptObject2.scopes.add("STEAM");
        punishmentScriptObject2.punishmentReductions = new ArrayList<>();

        scriptProcessingResult.punishments.add(punishmentScriptObject);


        PunishmentReductionScriptObject punishmentReductionScriptObject3 = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject3.priority = 68461525;
        punishmentReductionScriptObject3.amountSubtracted = 45565;
        punishmentReductionScriptObject3.attributes = new HashMap<>();
        punishmentReductionScriptObject3.attributes.put("testkeyATTRIBUTESOMETHING3","testvalueATTRIBUTESOMETHING3");

        punishmentScriptObject2.punishmentReductions.add(punishmentReductionScriptObject3);

        PunishmentReductionScriptObject punishmentReductionScriptObject4 = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject4.priority = 564654;
        punishmentReductionScriptObject4.amountSubtracted = 23587;
        punishmentReductionScriptObject4.attributes = new HashMap<>();
        punishmentReductionScriptObject4.attributes.put("testkeyATTRIBUTESOMETHING4","testvalueATTRIBUTESOMETHING4");

        punishmentScriptObject2.punishmentReductions.add(punishmentReductionScriptObject4);

        UUID criminalUUID = UUID.randomUUID();
        UUID moderatorUUID = UUID.randomUUID();


        OffenseProcessingRequest offenseProcessingRequest = OffenseProcessingRequest.builder().attributes(new HashMap<>())
                .criminalUUID(criminalUUID)
                .moderatorUUID(moderatorUUID)
                .processingTemplateUUID(UUID.randomUUID())
                .build();

        PunishAPI api = new PunishAPI();
        api.startup();

        historyRecord = me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.ScriptObjectConverter.convertOffenseScriptProcessingResultToOffenseHistoryRecord(scriptProcessingResult, offenseProcessingRequest, false);

        assertEquals(historyRecord.getLinkedPunishments().get(0).getAttributes(), scriptProcessingResult.punishments.get(0).attributes);
        assertEquals(historyRecord.getLinkedPunishments().get(0).isActivated(),scriptProcessingResult.punishments.get(0).activated);
        assertEquals(historyRecord.getLinkedPunishments().get(0).isDecrementsDuration(),scriptProcessingResult.punishments.get(0).decrementsDuration);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getStartTime(),scriptProcessingResult.punishments.get(0).startTime);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getDuration(),scriptProcessingResult.punishments.get(0).duration);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getScopes().get(0),scriptProcessingResult.punishments.get(0).scopes.get(0));
        assertEquals(historyRecord.getLinkedPunishments().get(0).getScopes().get(0),scriptProcessingResult.punishments.get(0).scopes.get(0));
        assertEquals(historyRecord.getLinkedPunishments().get(0).getScopes().get(1),scriptProcessingResult.punishments.get(0).scopes.get(1));
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(0).getPriority(),scriptProcessingResult.punishments.get(0).punishmentReductions.get(0).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(0).getAmountSubtracted(),scriptProcessingResult.punishments.get(0).punishmentReductions.get(0).amountSubtracted);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(1).getPriority(),scriptProcessingResult.punishments.get(0).punishmentReductions.get(1).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(0).getPunishmentReductions().get(1).getAmountSubtracted(),scriptProcessingResult.punishments.get(0).punishmentReductions.get(1).amountSubtracted);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getAttributes(), scriptProcessingResult.punishments.get(1).attributes);
        assertEquals(historyRecord.getLinkedPunishments().get(1).isActivated(),scriptProcessingResult.punishments.get(1).activated);
        assertEquals(historyRecord.getLinkedPunishments().get(1).isDecrementsDuration(),scriptProcessingResult.punishments.get(1).decrementsDuration);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getStartTime(),scriptProcessingResult.punishments.get(1).startTime);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getDuration(),scriptProcessingResult.punishments.get(1).duration);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getScopes().get(0),scriptProcessingResult.punishments.get(1).scopes.get(0));
        assertEquals(historyRecord.getLinkedPunishments().get(1).getScopes().get(1),scriptProcessingResult.punishments.get(1).scopes.get(1));
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(0).getPriority(),scriptProcessingResult.punishments.get(1).punishmentReductions.get(0).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(0).getAmountSubtracted(),scriptProcessingResult.punishments.get(1).punishmentReductions.get(0).amountSubtracted);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(1).getPriority(),scriptProcessingResult.punishments.get(1).punishmentReductions.get(1).priority);
        assertEquals(historyRecord.getLinkedPunishments().get(1).getPunishmentReductions().get(1).getAmountSubtracted(),scriptProcessingResult.punishments.get(1).punishmentReductions.get(1).amountSubtracted);

    }
}