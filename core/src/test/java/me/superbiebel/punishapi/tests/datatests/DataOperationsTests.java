package me.superbiebel.punishapi.tests.datatests;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.UUID;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.OffenseProcessingTemplateStorageTestImpl;
import me.superbiebel.punishapi.tests.testobjects.OffenseRecordStorageTestImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataOperationsTests {
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void storeAndRetrieveOffenseRecordTest() throws StartupException, ServiceAlreadyRegisteredException, FailedDataOperationException {
        final PunishAPI api = new PunishAPI();
        api.startup();

        final OffenseRecordStorageTestImpl TestHistoryRecordService = new OffenseRecordStorageTestImpl();

        api.getDataAPI().addService(Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE,TestHistoryRecordService);

        final UUID requestUUID = UUID.randomUUID();
        assertDoesNotThrow(()->api.getUnsafeCore().getDatamanager().storeOffenseRecord(OffenseHistoryRecord.builder().recordUUID(requestUUID).build()));
        assertEquals(requestUUID,api.getDataAPI().retrieveOffenseRecord(requestUUID).getRecordUUID());
    }
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void offenseProcessingTemplateStorageTest() throws StartupException, ServiceAlreadyRegisteredException, URISyntaxException, FailedDataOperationException {
        final PunishAPI api = new PunishAPI();
        api.startup();
        final DataAPI dataAPI = api.getDataAPI();

        OffenseProcessingTemplateStorageTestImpl templateStorage = new OffenseProcessingTemplateStorageTestImpl();
        dataAPI.addService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE, templateStorage);

        final OffenseProcessingTemplate testTemplate = OffenseProcessingTemplate.builder()
                .offenseProcessorUUID(UUID.randomUUID())
                .scriptFile(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testFile.txt")).toURI())).build();
        final UUID offenseProcessorUUID = UUID.randomUUID();
        final File testFile2 = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testFile2.txt")).toURI());

        assertDoesNotThrow(()->dataAPI.storeOffenseProcessingTemplate(testTemplate));
        assertEquals(testTemplate,dataAPI.retrieveOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID()));
        assertDoesNotThrow(()->dataAPI.updateOffenseProcessorUUIDInOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID(), offenseProcessorUUID));
        assertEquals(offenseProcessorUUID,dataAPI.retrieveOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID()).getOffenseProcessorUUID());
        assertDoesNotThrow(()->dataAPI.updateScriptFile(testTemplate.getOffenseProcessingTemplateUUID(), testFile2));
        assertEquals(testFile2,dataAPI.retrieveOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID()).getScriptFile());
        assertTrue(dataAPI.deleteOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID()));
        assertFalse(dataAPI.deleteOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID()));
        assertNull(dataAPI.retrieveOffenseProcessingTemplate(testTemplate.getOffenseProcessingTemplateUUID()));
    }
}
