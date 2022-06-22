package me.superbiebel.punishapi.tests.testobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.servicesoperations.OffenseRecordStorageOperations;
import me.superbiebel.punishapi.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.verdict.Punishment;

public class OffenseRecordStorageTestImpl extends BaseDataTestingService implements OffenseRecordStorageOperations {

    final Map<UUID, OffenseHistoryRecord> offenseHistoryRecordStorage = new HashMap<>();

    @Override
    public Datamanager.DataServiceType[] supportsDataOperations() {
        Datamanager.DataServiceType[] dataServiceTypes = new Datamanager.DataServiceType[1];
        dataServiceTypes[0] = Datamanager.DataServiceType.OFFENSE_RECORD_STORAGE;
        return dataServiceTypes;
    }

    @Override
    public boolean storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) {
        return offenseHistoryRecordStorage.put(offenseHistoryRecord.getRecordUUID(), offenseHistoryRecord) != null;
    }

    @Override
    public OffenseHistoryRecord retrieveOffenseRecord(UUID recordUUID) {
        return offenseHistoryRecordStorage.get(recordUUID);
    }

    @Override
    public boolean setOffenseHistoryRecordAttribute(UUID offenseUUID, String key, String value) {
        return false;
    }

    @Override
    public boolean removeOffenseHistoryRecordAttributeWithKey(UUID offenseUUID, String key) {
        return false;
    }

    @Override
    public boolean removeOffenseHistoryRecordAttributeWithValue(UUID offenseUUID, String value) {
        return false;
    }

    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttribute(String key, String value) {
        return null;
    }

    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeKey(String key) {
        return null;
    }

    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeValue(String value) {
        return null;
    }

    @Override
    public Punishment retrievePunishment(UUID offenseUUID) {
        return null;
    }

    @Override
    public boolean setPunishmentAttribute(UUID punishmentUUID, String key, String value) {
        return false;
    }

    @Override
    public boolean removePunishmentAttributeWithKey(UUID punishmentUUID, String key) {
        return false;
    }

    @Override
    public boolean removePunishmentAttributeWithValue(UUID punishmentUUID, String value) {
        return false;
    }

    @Override
    public List<Punishment> getPunishmentByAttribute(String key, String value) {
        return null;
    }

    @Override
    public List<Punishment> getPunishmentByAttributeKey(String key) {
        return null;
    }

    @Override
    public List<Punishment> getPunishmentByAttributeValue(String value) {
        return null;
    }

    @Override
    public boolean setDecrementsPunishmentDuration(UUID punishmentUUID, boolean value) {
        return false;
    }

    @Override
    public boolean setPunishmentActivation(UUID punishmentUUID, boolean value) {
        return false;
    }
}
