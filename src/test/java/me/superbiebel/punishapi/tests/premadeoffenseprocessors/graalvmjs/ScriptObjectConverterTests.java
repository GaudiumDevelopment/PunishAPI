package me.superbiebel.punishapi.tests.premadeoffenseprocessors.graalvmjs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.ScriptObjectConverter;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.OffenseHistoryRecordScriptObject;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.PunishmentReductionScriptObject;
import me.superbiebel.punishapi.offenseprocessing.premade.jsoffenseprocessor.scriptobjects.PunishmentScriptObject;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ScriptObjectConverterTests {


    @TestFactory
    @Execution(ExecutionMode.CONCURRENT)
    Collection<DynamicTest> testOffenseHistoryRecordScriptObjectToOffenseHistoryRecordConverter() {

        int amountOffenseHistoryRecordScriptObjects = generateRandomInt(1,10);

        List<DynamicTest> testList = new ArrayList<>();

        for (int i = 0;i < amountOffenseHistoryRecordScriptObjects;i++) {
            testList.add(DynamicTest.dynamicTest("OffenseHistoryRecordScriptObjectToOffenseHistoryRecordTest-" + i,()-> {
                int amountPunishmentScriptObjects = generateRandomInt(1, 10);
                PunishmentScriptObject[] punishmentScriptObjects = new PunishmentScriptObject[amountPunishmentScriptObjects];
                for (int j = 0; j < amountPunishmentScriptObjects; j++) {
                    int attributecount = generateRandomInt(1, 10);
                    punishmentScriptObjects[j] = generatePunishmentScriptObject(generateStringArray(attributecount),
                            generateStringArray(attributecount),
                            generateRandomLong(1, Long.MAX_VALUE - 1),
                            generateRandomLong(1, Long.MAX_VALUE - 1), generateRandomBoolean(),
                            generateStringArray(generateRandomInt(1, 10)),
                                generatePunishmentReductionScriptObjectArray(generateRandomInt(1, 10)),
                            generateRandomBoolean());
                }

                int keycount = generateRandomInt(1, 10);

                OffenseHistoryRecordScriptObject offenseHistoryRecordScriptObject = generateOffenseHistoryRecordScriptObject(generateStringArray(keycount),
                        generateStringArray(keycount),
                        punishmentScriptObjects);

                OffenseHistoryRecord historyRecord = ScriptObjectConverter.convertOffenseScriptProcessingResultToOffenseHistoryRecord( offenseHistoryRecordScriptObject, OffenseProcessingRequest.builder()
                        .attributes(new HashMap<>())
                        .criminalUUID(UUID.randomUUID())
                        .moderatorUUID(UUID.randomUUID())
                        .processingTemplateUUID(UUID.randomUUID())
                        .build(),
                        false);

                //check all the values
                assertEquals(offenseHistoryRecordScriptObject.attributes, historyRecord.getAttributes());
                for (int k = 0; k < offenseHistoryRecordScriptObject.punishments.size(); k++) {
                    assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).attributes, historyRecord.getLinkedPunishments().get(k).getAttributes());
                    assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).startTime, historyRecord.getLinkedPunishments().get(k).getStartTime());
                    assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).duration, historyRecord.getLinkedPunishments().get(k).getDuration());
                    assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).activated, historyRecord.getLinkedPunishments().get(k).isActivated());
                    assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).scopes, historyRecord.getLinkedPunishments().get(k).getScopes());
                    assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).decrementsDuration, historyRecord.getLinkedPunishments().get(k).isDecrementsDuration());
                    for (int l = 0; l < offenseHistoryRecordScriptObject.punishments.get(k).punishmentReductions.size(); l++) {
                        assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).punishmentReductions.get(l).attributes,historyRecord.getLinkedPunishments().get(k).getPunishmentReductions().get(l).getAttributes());
                        assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).punishmentReductions.get(l).amountSubtracted,historyRecord.getLinkedPunishments().get(k).getPunishmentReductions().get(l).getAmountSubtracted());
                        assertEquals(offenseHistoryRecordScriptObject.punishments.get(k).punishmentReductions.get(l).priority,historyRecord.getLinkedPunishments().get(k).getPunishmentReductions().get(l).getPriority());
                    }
                }
            }));
        }
        return testList;
    }

    public static PunishmentReductionScriptObject[] generatePunishmentReductionScriptObjectArray(int amount) {
        PunishmentReductionScriptObject[] punishmentReductionScriptObjects = new PunishmentReductionScriptObject[amount];
        for (int i = 0;i<amount;i++) {
            int keycount = generateRandomInt(1,10);
            punishmentReductionScriptObjects[i] = generatePunishmentReductionScriptObject(generateRandomInt(1,100000),generateRandomInt(1,100000),generateStringArray(keycount),generateStringArray(keycount));
        }
        return punishmentReductionScriptObjects;
    }
    public static String generateString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    public static String[] generateStringArray(int amount) {
        String[] stringArray = new String[amount];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = generateString();
        }
        return stringArray;
    }

    public static int generateRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    public static long generateRandomLong(long min, long max) {
        return min + (long) (Math.random() * (max - min));
    }
    public static boolean generateRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static OffenseHistoryRecordScriptObject generateOffenseHistoryRecordScriptObject(String[] keys, String[] values, PunishmentScriptObject[] punishmentScriptObjects) {
        OffenseHistoryRecordScriptObject offenseHistoryRecordScriptObject = new OffenseHistoryRecordScriptObject();
        offenseHistoryRecordScriptObject.attributes = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            offenseHistoryRecordScriptObject.attributes.put(keys[i], values[i]);
        }
        offenseHistoryRecordScriptObject.punishments = List.of(punishmentScriptObjects);
        return offenseHistoryRecordScriptObject;
    }

    public static PunishmentScriptObject generatePunishmentScriptObject(String[] keys, String[] values, long startTime, long duration, boolean activated, String[] scopes, PunishmentReductionScriptObject[] punishmentReductionScriptObjects, boolean decrementsDuration) {
        PunishmentScriptObject punishmentScriptObject = new PunishmentScriptObject();
        punishmentScriptObject.attributes = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            punishmentScriptObject.attributes.put(keys[i], values[i]);
        }
        punishmentScriptObject.startTime = startTime;
        punishmentScriptObject.duration = duration;
        punishmentScriptObject.activated = activated;
        punishmentScriptObject.scopes = List.of(scopes);
        punishmentScriptObject.punishmentReductions = List.of(punishmentReductionScriptObjects);
        punishmentScriptObject.decrementsDuration = decrementsDuration;

        return punishmentScriptObject;
    }

    public static PunishmentReductionScriptObject generatePunishmentReductionScriptObject(int priority, int amountSubtracted, String[] keys, String[] values) {
        PunishmentReductionScriptObject punishmentReductionScriptObject = new PunishmentReductionScriptObject();
        punishmentReductionScriptObject.priority = priority;
        punishmentReductionScriptObject.amountSubtracted = amountSubtracted;
        punishmentReductionScriptObject.attributes = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            punishmentReductionScriptObject.attributes.put(keys[i], values[i]);
        }

        return punishmentReductionScriptObject;
    }
}
