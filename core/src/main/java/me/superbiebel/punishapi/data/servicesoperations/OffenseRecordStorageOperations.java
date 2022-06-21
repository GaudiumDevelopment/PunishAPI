package me.superbiebel.punishapi.data.servicesoperations;

import me.superbiebel.punishapi.data.servicesoperations.dataapi.OffenseHistoryRecordAPIOperations;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface OffenseRecordStorageOperations extends OffenseHistoryRecordAPIOperations {
    boolean storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException;

}
