package me.superbiebel.punishapi.data;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import me.superbiebel.punishapi.abstractions.Service;
import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.data.servicesoperations.OffenseProcessingTemplateStorageOperations;
import me.superbiebel.punishapi.data.servicesoperations.OffenseRecordStorageOperations;
import me.superbiebel.punishapi.data.servicesoperations.TestDataOperations;
import me.superbiebel.punishapi.data.servicesoperations.UserAccountOperations;
import me.superbiebel.punishapi.data.servicesoperations.UserLockOperations;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.dataobjects.Punishment;
import me.superbiebel.punishapi.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.ApiStatus;


@ApiStatus.Internal
public final class Datamanager extends ServiceRegistry<Datamanager.DataServiceType>
        implements OffenseProcessingTemplateStorageOperations
        , OffenseRecordStorageOperations
        , TestDataOperations
        , UserAccountOperations
        , UserLockOperations {

    public Datamanager() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    public Service getService(final DataServiceType serviceType) throws ServiceNotFoundException {
        final DataService foundService = (DataService) super.getService(serviceType);
        if (Arrays.stream(foundService.supportsDataOperations()).noneMatch(dataServiceType -> dataServiceType.equals(serviceType))) {
            throw new IllegalStateException("A service was found but it didn't actually support the data operation");
        }
        return foundService;
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
    public boolean setPunishmentattribute(UUID punishmentUUID, String key, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).setPunishmentattribute(punishmentUUID, key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removePunishmentattributeWithKey(UUID punishmentUUID, String key) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).removePunishmentattributeWithKey(punishmentUUID, key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removePunishmentattributeWithValue(UUID punishmentUUID, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).removePunishmentattributeWithValue(punishmentUUID, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<Punishment> getPunishmentbyAttribute(String key, String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getPunishmentbyAttribute(key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<Punishment> getPunishmentbyAttributeKey(String key) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getPunishmentbyAttributeKey(key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<Punishment> getPunishmentbyAttributeValue(String value) throws FailedDataOperationException {
        try {
            return ((OffenseRecordStorageOperations) super.getService(DataServiceType.OFFENSE_RECORD_STORAGE)).getPunishmentbyAttributeValue(value);
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
    public void storeOffenseProcessingTemplate(final OffenseProcessingTemplate template) throws FailedDataOperationException {
        super.canInteract();
        try {
            ((OffenseProcessingTemplateStorageOperations) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE)).storeOffenseProcessingTemplate(template);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(final UUID templateUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((OffenseProcessingTemplateStorageOperations) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE)).retrieveOffenseProcessingTemplate(templateUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean deleteOffenseProcessingTemplate(final UUID templateUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((OffenseProcessingTemplateStorageOperations) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE)).deleteOffenseProcessingTemplate(templateUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public void updateOffenseProcessorUUIDInOffenseProcessingTemplate(final UUID templateUUID, final  UUID newOffenseProcessorUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            ((OffenseProcessingTemplateStorageOperations) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE)).updateOffenseProcessorUUIDInOffenseProcessingTemplate(templateUUID, newOffenseProcessorUUID);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean updateScriptFile(final UUID templateUUID, final  File newScriptFile) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((OffenseProcessingTemplateStorageOperations) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE)).updateScriptFile(templateUUID, newScriptFile);
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
            return ((UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).setUserAttribute(userUUID, key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public boolean removeUserAttribute(final UUID userUUID, final String key) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).removeUserAttribute(userUUID, key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<UserAccount> getUsersByAttribute(final String key, final String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).getUsersByAttribute(key, value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<UserAccount> getUsersByAttributeKey(final String key) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE)).getUsersByAttributeKey(key);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public List<UserAccount> getUsersByAttributeValue(final String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountOperations service = (UserAccountOperations) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            return service.getUsersByAttributeValue(value);
        } catch (Exception e) {
            throw new FailedDataOperationException(e);
        }
    }

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

    public int serviceCount() {
        return serviceRegistryMap.size();
    }

    public enum DataServiceType {
        TEST, OFFENSE_PROCESSING_TEMPLATE_STORAGE, OFFENSE_RECORD_STORAGE, USER_LOCKING, USER_ACCOUNT_STORAGE
    }
}
