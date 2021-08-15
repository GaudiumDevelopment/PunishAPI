package me.superbiebel.punishapi.data.servicesoperations;

import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface OffenseRecordStorageOperations {
    void storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException;

    OffenseHistoryRecord retrieveOffenseRecord(UUID offenseUUID) throws FailedDataOperationException;
}
