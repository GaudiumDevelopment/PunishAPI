package me.superbiebel.punishapi.data.serviceoperations;

import me.superbiebel.punishapi.common.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.data.serviceoperations.dataapi.OffenseHistoryRecordAPIOperations;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface OffenseRecordStorageOperations extends OffenseHistoryRecordAPIOperations {
    boolean storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException;

}
