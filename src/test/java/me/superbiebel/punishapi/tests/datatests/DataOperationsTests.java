package me.superbiebel.punishapi.tests.datatests;

import java.util.UUID;
import me.superbiebel.punishapi.api.PunishAPI;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.tests.testobjects.OffenseRecordStorageTestImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataOperationsTests {
    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void storeAndRetrieveOffenseTest() throws StartupException, ServiceAlreadyRegisteredException, FailedDataOperationException {
        PunishAPI api = new PunishAPI();
        api.startup();

        OffenseRecordStorageTestImpl TestHistoryRecordService = new OffenseRecordStorageTestImpl();

        api.getDataAPI().addService(Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE,TestHistoryRecordService);

        UUID requestUUID = UUID.randomUUID();
        assertDoesNotThrow(()->api.getCore().getDatamanager().storeOffenseRecord(OffenseHistoryRecord.builder().recordUUID(requestUUID).build()));
        assertEquals(requestUUID,api.getDataAPI().retrieveOffense(requestUUID).getRecordUUID());
    }
}
