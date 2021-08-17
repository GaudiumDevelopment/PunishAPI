package me.superbiebel.punishapi.tests.testobjects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.servicesoperations.OffenseRecordStorageOperations;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;

public class OffenseRecordStorageTestImpl extends BaseDataTestingService implements OffenseRecordStorageOperations {

    final Map<UUID, OffenseHistoryRecord> offenseHistoryRecordStorage = new HashMap<>();

    @Override
    public Datamanager.DataServiceType[] supportsDataOperations() {
        Datamanager.DataServiceType[] dataServiceTypes = new Datamanager.DataServiceType[1];
        dataServiceTypes[0] = Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE;
        return dataServiceTypes;
    }

    @Override
    public void storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) {
        offenseHistoryRecordStorage.put(offenseHistoryRecord.getRecordUUID(), offenseHistoryRecord);
    }

    @Override
    public OffenseHistoryRecord retrieveOffenseRecord(UUID recordUUID) {
        return offenseHistoryRecordStorage.get(recordUUID);
    }
}
