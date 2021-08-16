package me.superbiebel.punishapi.tests.datatests;

import java.util.UUID;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.OffenseRecordStorageTestImpl;
import me.superbiebel.punishapi.tests.testobjects.UserLockTestImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    void storeOffenseProcessingTemplateTest(){

    }
}
