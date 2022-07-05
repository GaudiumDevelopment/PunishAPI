package me.superbiebel.punishapi.tests.datatests;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.UUID;
import me.superbiebel.punishapi.api.DataAPI;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.common.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.StartupException;
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
}
