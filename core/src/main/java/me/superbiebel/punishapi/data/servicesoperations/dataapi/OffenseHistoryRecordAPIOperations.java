package me.superbiebel.punishapi.data.servicesoperations.dataapi;

import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.Punishment;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

import java.util.List;
import java.util.UUID;

public interface OffenseHistoryRecordAPIOperations {
    OffenseHistoryRecord retrieveOffenseRecord(UUID offenseUUID) throws FailedDataOperationException;
    
    boolean setOffenseHistoryRecordAttribute(UUID offenseUUID, String key, String value) throws FailedDataOperationException;
    
    boolean removeOffenseHistoryRecordAttributeWithKey(UUID offenseUUID, String key) throws FailedDataOperationException;
    
    boolean removeOffenseHistoryRecordAttributeWithValue(UUID offenseUUID, String value) throws FailedDataOperationException;
    
    List<OffenseHistoryRecord> getOffenseHistoryRecordByAttribute(String key, String value) throws FailedDataOperationException;
    
    List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeKey(String key) throws FailedDataOperationException;
    
    List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeValue(String value) throws FailedDataOperationException;
    
    Punishment retrievePunishment(UUID offenseUUID) throws FailedDataOperationException;
    
    boolean setPunishmentAttribute(UUID punishmentUUID, String key, String value) throws FailedDataOperationException;
    
    boolean removePunishmentAttributeWithKey(UUID punishmentUUID, String key) throws FailedDataOperationException;
    
    boolean removePunishmentAttributeWithValue(UUID punishmentUUID, String value) throws FailedDataOperationException;
    
    List<Punishment> getPunishmentByAttribute(String key, String value) throws FailedDataOperationException;
    
    List<Punishment> getPunishmentByAttributeKey(String key) throws FailedDataOperationException;
    
    List<Punishment> getPunishmentByAttributeValue(String value) throws FailedDataOperationException;
    
    boolean setDecrementsPunishmentDuration(UUID punishmentUUID, boolean value) throws FailedDataOperationException;
    
    boolean setPunishmentActivation(UUID punishmentUUID, boolean value) throws FailedDataOperationException;
}
