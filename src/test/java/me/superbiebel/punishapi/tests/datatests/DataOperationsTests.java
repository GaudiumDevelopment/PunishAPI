package me.superbiebel.punishapi.tests.datatests;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.UUID;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.OffenseProcessingTemplateStorageTestImpl;
import me.superbiebel.punishapi.tests.testobjects.OffenseRecordStorageTestImpl;
import me.superbiebel.punishapi.tests.testobjects.UserLockTestImpl;
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
        PunishAPI api = new PunishAPI();
        api.startup();

        OffenseRecordStorageTestImpl TestHistoryRecordService = new OffenseRecordStorageTestImpl();

        api.getDataAPI().addService(Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE,TestHistoryRecordService);

        UUID requestUUID = UUID.randomUUID();
        assertDoesNotThrow(()->api.getUnsafeCore().getDatamanager().storeOffenseRecord(OffenseHistoryRecord.builder().recordUUID(requestUUID).build()));
        assertEquals(requestUUID,api.getDataAPI().retrieveOffense(requestUUID).getRecordUUID());
    }
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void lockUserTest() throws StartupException, FailedDataOperationException, ServiceAlreadyRegisteredException {
        PunishAPI api = new PunishAPI();
        api.startup();
        UserLockTestImpl userLockTestImpl = new UserLockTestImpl();
        api.getDataAPI().addService(Datamanager.DataServiceType.USER_LOCKING,userLockTestImpl);

        UUID userUUID = UUID.randomUUID();

        assertTrue(api.getDataAPI().tryLockUser(userUUID));
        assertTrue(api.getDataAPI().isUserLocked(userUUID));
        assertFalse(api.getDataAPI().tryLockUser(userUUID));
        assertDoesNotThrow(()->api.getDataAPI().unlockUser(userUUID));
        assertFalse(api.getDataAPI().isUserLocked(userUUID));
        assertTrue(api.getDataAPI().tryLockUser(userUUID));

    }
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void offenseProcessingTemplateStorageTest() throws StartupException, ServiceAlreadyRegisteredException, URISyntaxException, FailedDataOperationException {
        PunishAPI api = new PunishAPI();
        api.startup();
        DataAPI dataAPI = api.getDataAPI();

        OffenseProcessingTemplateStorageTestImpl templateStorage = new OffenseProcessingTemplateStorageTestImpl();
        dataAPI.addService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE, templateStorage);

        OffenseProcessingTemplate testTemplate = OffenseProcessingTemplate.builder()
                .offenseProcessorUUID(UUID.randomUUID())
                .scriptFile(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testFile.txt")).toURI())).build();
        UUID offenseProcessorUUID = UUID.randomUUID();
        File testFile2 = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("testFile2.txt")).toURI());

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
