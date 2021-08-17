package me.superbiebel.punishapi.api;

import java.io.File;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.Service;
import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.jetbrains.annotations.Nullable;

public class DataAPI {
    private final Datamanager datamanager;

    protected DataAPI(PunishCore core) {
        core.setDataAPI(this);
        datamanager = core.getDatamanager();
    }

    public void addService(Datamanager.DataServiceType dataServiceType, DataService service) throws StartupException, ServiceAlreadyRegisteredException {
        datamanager.addService(dataServiceType, service);
    }
    
    @Nullable
    public Service removeService(Datamanager.DataServiceType dataServiceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        return datamanager.removeService(dataServiceType, kill);
    }
    
    public Service getService(Datamanager.DataServiceType dataServiceType) throws ServiceNotFoundException {
        return datamanager.getService(dataServiceType);
    }
    
    public OffenseHistoryRecord retrieveOffense(UUID offenseUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseRecord(offenseUUID);
    }
    
    public UserAccount retrieveUser(UUID userUUID) throws FailedDataOperationException {
        return datamanager.retrieveUser(userUUID);
    }
    
    public void setUserAttribute(UUID userUUID, String key, String value) throws FailedDataOperationException {
        datamanager.setUserAttribute(userUUID, key, value);
    }
    
    public void removeUserAttribute(UUID userUUID,String key) throws FailedDataOperationException {
        datamanager.removeUserAttribute(userUUID, key);
    }
    
    public List<UserAccount> getUsersByAttribute(String key, String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttribute(key, value);
    }
    
    public List<UserAccount> getUsersByAttributeKey(String key) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeKey(key);
    }
    
    public List<UserAccount> getUsersByAttributeValue(String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeValue(value);
    }

    public boolean tryLockUser(UUID uuid) throws FailedDataOperationException {
        return datamanager.tryLockUser(uuid);
    }

    //The processing is done
    public void unlockUser(UUID uuid) throws FailedDataOperationException{
        datamanager.unlockUser(uuid);
    }

    //Checks if the user is locked
    public boolean isUserLocked(UUID userUUID) throws FailedDataOperationException{
        return datamanager.isUserLocked(userUUID);
    }

    public void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException {
        datamanager.storeOffenseProcessingTemplate(template);
    }

    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseProcessingTemplate(templateUUID);
    }

    public boolean deleteOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        return datamanager.deleteOffenseProcessingTemplate(templateUUID);
    }

    public boolean updateOffenseProcessorUUIDInOffenseProcessingTemplate(UUID templateUUID, UUID newOffenseProcessorUUID) throws FailedDataOperationException {
        return datamanager.updateOffenseProcessorUUIDInOffenseProcessingTemplate(templateUUID, newOffenseProcessorUUID);
    }

    public boolean updateScriptFile(UUID templateUUID, File newScriptFile) throws FailedDataOperationException {
        return datamanager.updateScriptFile(templateUUID, newScriptFile);
    }
}
