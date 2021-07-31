package me.superbiebel.punishapi.data;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.data.services.offenseprocessingtemplatestorage.OffenseProcessingTemplateStorage;
import me.superbiebel.punishapi.data.services.offenseprocessingtemplatestorage.OffenseProcessingTemplateStorageOperations;
import me.superbiebel.punishapi.data.services.offenserecordstorage.OffenseRecordStorage;
import me.superbiebel.punishapi.data.services.offenserecordstorage.OffenseRecordStorageOperations;
import me.superbiebel.punishapi.data.services.testdataservice.TestDataOperations;
import me.superbiebel.punishapi.data.services.useraccountservice.UserAccountOperations;
import me.superbiebel.punishapi.data.services.useraccountservice.UserAccountService;
import me.superbiebel.punishapi.data.services.userlockservice.UserLockOperations;
import me.superbiebel.punishapi.data.services.userlockservice.UserLockService;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import org.apache.logging.log4j.LogManager;

/**
 * FOR INTERNAL USE ONLY!!!!
 */
public class Datamanager extends ServiceRegistry<Datamanager.DataServiceType>
        implements OffenseProcessingTemplateStorageOperations, OffenseRecordStorageOperations, TestDataOperations, UserAccountOperations, UserLockOperations {
    
    @Getter
    private static final int MAXSERVICECOUNT = 2;
    
    public Datamanager() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    public OffenseHistoryRecord retrieveOffenseRecord(UUID offenseUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((OffenseRecordStorage) getService(DataServiceType.OFFENSE_RECORD_STORAGE)).retrieveOffenseRecord(offenseUUID);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }

    @Override
    public String exampleOperation(String returnString) {
        super.canInteract();
        return returnString;
    }

    public enum DataServiceType {
        TEST,OFFENSE_PROCESSING_TEMPLATE_STORAGE,OFFENSE_RECORD_STORAGE,USER_LOCKING,USER_ACCOUNT_STORAGE
    }
    @Override
    public void lockUser(UUID uuid) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserLockService userLockService = (UserLockService) getService(Datamanager.DataServiceType.USER_LOCKING);
            userLockService.lockUser(uuid);

        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public void unlockUser(UUID uuid) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserLockService userLockService = (UserLockService) getService(Datamanager.DataServiceType.USER_LOCKING);
            userLockService.unlockUser(uuid);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public void storeOffenseRecord(OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException {
        super.canInteract();
        try {
            OffenseRecordStorage service = (OffenseRecordStorage) getService(DataServiceType.OFFENSE_RECORD_STORAGE);
            service.storeOffenseRecord(offenseHistoryRecord);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }

    }
    @Override
    public void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException {
        super.canInteract();
        try {
            OffenseProcessingTemplateStorage service = (OffenseProcessingTemplateStorage) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE);
            service.storeOffenseProcessingTemplate(template);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            OffenseProcessingTemplateStorage service = (OffenseProcessingTemplateStorage) getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE);
            return service.retrieveOffenseProcessingTemplate(templateUUID);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public UserAccount createUser(UUID userUUID, Map<String, String> attributes) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountService service = (UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            service.createUser(userUUID, attributes);
            return UserAccount.builder().userUUID(userUUID).attributes(attributes).build();
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public UserAccount retrieveUser(UUID userUUID) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountService service = (UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            return service.retrieveUser(userUUID);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public void setUserAttribute(String key, String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountService service = (UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            service.setUserAttribute(key, value);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public void removeUserAttribute(String key) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountService service = (UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            service.removeUserAttribute(key);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public List<UserAccount> getUsersByAttribute(String key, String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountService service = (UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            return service.getUsersByAttribute(key, value);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public List<UserAccount> getUsersByAttributekey(String key) throws FailedDataOperationException {
        super.canInteract();
        try {
            return ((UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE)).getUsersByAttributekey(key);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    @Override
    public List<UserAccount> getUsersByAttributeValue(String value) throws FailedDataOperationException {
        super.canInteract();
        try {
            UserAccountService service = (UserAccountService) getService(DataServiceType.USER_ACCOUNT_STORAGE);
            return service.getUsersByAttributeValue(value);
        } catch (ServiceNotFoundException e) {
            throw new FailedDataOperationException(e);
        }
    }
    
    @Override
    protected void onServiceRegistryStartup(boolean force) {
        //to be implemented if needed
    }
    @Override
    protected void onServiceRegistryShutdown() {
        serviceRegistryMap.keySet().iterator().forEachRemaining(dataServiceType -> {
            try {
                this.removeService(dataServiceType, false);
            } catch (Exception e) {
                LogManager.getLogger().error("Could not unregister and shutdown servicetype: {}", dataServiceType, e);
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
}
