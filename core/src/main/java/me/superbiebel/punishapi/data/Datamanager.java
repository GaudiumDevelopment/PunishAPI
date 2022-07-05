package me.superbiebel.punishapi.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import me.superbiebel.punishapi.abstractions.Service;
import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.data.serviceoperations.OffenseRecordStorageOperations;
import me.superbiebel.punishapi.data.serviceoperations.TestDataOperations;
import me.superbiebel.punishapi.data.serviceoperations.UserAccountOperations;
import me.superbiebel.punishapi.data.serviceoperations.UserLockOperations;
import me.superbiebel.punishapi.data.serviceoperations.UserAccountAttributeOperations;
import me.superbiebel.punishapi.common.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.common.dataobjects.verdict.Punishment;
import me.superbiebel.punishapi.common.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.ApiStatus;


@ApiStatus.Internal
public final class Datamanager extends ServiceRegistry<Datamanager.DataServiceType>
        implements OffenseRecordStorageOperations
        , TestDataOperations
        , UserAccountOperations
        , UserLockOperations {
    
    @Override
    protected void onServiceRegistryStartup(final boolean force) {
        //to be implemented if needed
    }
    
    @Override
    protected void onServiceRegistryShutdown() {
        serviceRegistryMap.keySet().iterator().forEachRemaining(dataServiceType -> {
            try {
                this.removeService(dataServiceType, false);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown service type: {}", dataServiceType, e);
            }
        });
    }
    
    @Override
    protected void onServiceRegistryKill() {
        //to be implemented if needed
    }
    
    @Override
    protected boolean addServiceCheck(DataServiceType serviceType, Service service) {
        if(!(service instanceof DataService)) throw new IllegalArgumentException("A service (NOT a dataservice) was passed in");
        return Arrays.asList(((DataService) service).supportsDataOperations()).contains(serviceType);
    }
    
    public int serviceCount() {
        return serviceRegistryMap.size();
    }
    
    public enum DataServiceType {
        TEST, OFFENSE_PROCESSING_TEMPLATE_STORAGE, OFFENSE_RECORD_STORAGE, USER_LOCKING, USER_ACCOUNT_STORAGE
    }
    public Datamanager() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    public OffenseHistoryRecord retrieveOffenseRecord(final UUID offenseUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).retrieveOffenseRecord(offenseUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean setOffenseHistoryRecordAttribute(UUID offenseUUID, String key, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).setOffenseHistoryRecordAttribute(offenseUUID, key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removeOffenseHistoryRecordAttributeWithKey(UUID offenseUUID, String key) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).removeOffenseHistoryRecordAttributeWithKey(offenseUUID, key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removeOffenseHistoryRecordAttributeWithValue(UUID offenseUUID, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).removeOffenseHistoryRecordAttributeWithValue(offenseUUID, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttribute(String key, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getOffenseHistoryRecordByAttribute(key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeKey(String key) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getOffenseHistoryRecordByAttributeKey(key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeValue(String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getOffenseHistoryRecordByAttributeValue(value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public Punishment retrievePunishment(UUID offenseUUID) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).retrievePunishment(offenseUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean setPunishmentAttribute(UUID punishmentUUID, String key, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).setPunishmentAttribute(punishmentUUID, key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removePunishmentAttributeWithKey(UUID punishmentUUID, String key) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).removePunishmentAttributeWithKey(punishmentUUID, key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removePunishmentAttributeWithValue(UUID punishmentUUID, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).removePunishmentAttributeWithValue(punishmentUUID, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<Punishment> getPunishmentByAttribute(String key, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getPunishmentByAttribute(key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<Punishment> getPunishmentByAttributeKey(String key) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getPunishmentByAttributeKey(key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<Punishment> getPunishmentByAttributeValue(String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getPunishmentByAttributeValue(value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean setDecrementsPunishmentDuration(UUID punishmentUUID, boolean value) {
        return false;
    }

    @Override
    public boolean setPunishmentActivation(UUID punishmentUUID, boolean value) {
        return false;
    }

    @Override
    public String exampleOperation(final String returnString) {
        super.canInteract();
        return returnString;
    }

    @Override
    public boolean tryLockUser(final UUID uuid) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserLockOperations) getService(Datamanager.DataServiceType.USER_LOCKING)).tryLockUser(uuid);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public void unlockUser(final UUID userUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            ((UserLockOperations) getService(Datamanager.DataServiceType.USER_LOCKING)).unlockUser(userUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean isUserLocked(final UUID userUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserLockOperations) getService(Datamanager.DataServiceType.USER_LOCKING)).isUserLocked(userUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean storeOffenseRecord(final OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((OffenseRecordStorageOperations) getService(DataServiceType.OFFENSE_RECORD_STORAGE)).storeOffenseRecord(offenseHistoryRecord);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public UserAccount createUser(final Map<String, String> attributes) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).createUser(attributes);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public UserAccount retrieveUser(final UUID userUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).retrieveUser(userUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean setUserAttribute(final UUID userUUID, final String key, final String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountAttributeOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).setUserAttribute(userUUID, key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removeUserAttribute(final UUID userUUID, final String key) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountAttributeOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).removeUserAttribute(userUUID, key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<UserAccount> getUsersByAttribute(final String key, final String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountAttributeOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).getUsersByAttribute(key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<UserAccount> getUsersByAttributeKey(final String key) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountAttributeOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).getUsersByAttributeKey(key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<UserAccount> getUsersByAttributeValue(final String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountAttributeOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).getUsersByAttributeValue(value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }
}
