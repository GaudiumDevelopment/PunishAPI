package me.superbiebel.punishapi.data.servicesoperations;

import java.util.List;
import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.Punishment;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface OffenseRecordStorageOperations {
    boolean storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException;

    OffenseHistoryRecord retrieveOffenseRecord(UUID offenseUUID) throws FailedDataOperationException;

    boolean setOffenseHistoryRecordAttribute(UUID offenseUUID, String key, String value) throws FailedDataOperationException;

    boolean removeOffenseHistoryRecordAttributeWithKey(UUID offenseUUID, String key) throws FailedDataOperationException;

    boolean removeOffenseHistoryRecordAttributeWithValue(UUID offenseUUID, String value) throws FailedDataOperationException;

    List<OffenseHistoryRecord> getOffenseHistoryRecordByAttribute(String key, String value) throws FailedDataOperationException;

    List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeKey(String key) throws FailedDataOperationException;

    List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeValue(String value) throws FailedDataOperationException;

    Punishment retrievePunishment(UUID offenseUUID) throws FailedDataOperationException;

    boolean setPunishmentattribute(UUID punishmentUUID, String key, String value) throws FailedDataOperationException;

    boolean removePunishmentattributeWithKey(UUID punishmentUUID, String key) throws FailedDataOperationException;

    boolean removePunishmentattributeWithValue(UUID punishmentUUID, String value) throws FailedDataOperationException;

    List<Punishment> getPunishmentbyAttribute(String key, String value) throws FailedDataOperationException;

    List<Punishment> getPunishmentbyAttributeKey(String key) throws FailedDataOperationException;

    List<Punishment> getPunishmentbyAttributeValue(String value) throws FailedDataOperationException;

    boolean setDecrementsPunishmentDuration(UUID punishmentUUID, boolean value) throws FailedDataOperationException;

    boolean setPunishmentActivation(UUID punishmentUUID, boolean value) throws FailedDataOperationException;

}
