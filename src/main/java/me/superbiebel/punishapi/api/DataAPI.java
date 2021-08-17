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

    protected DataAPI(final PunishCore core) {
        core.setDataAPI(this);
        datamanager = core.getDatamanager();
    }

    public void addService(final Datamanager.DataServiceType dataServiceType, final DataService service) throws StartupException, ServiceAlreadyRegisteredException {
        datamanager.addService(dataServiceType, service);
    }
    
    @Nullable
    public Service removeService(final Datamanager.DataServiceType dataServiceType, final boolean kill) throws ShutDownException, ServiceNotFoundException {
        return datamanager.removeService(dataServiceType, kill);
    }
    
    public Service getService(final Datamanager.DataServiceType dataServiceType) throws ServiceNotFoundException {
        return datamanager.getService(dataServiceType);
    }
    
    public OffenseHistoryRecord retrieveOffense(final UUID offenseUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseRecord(offenseUUID);
    }
    
    public UserAccount retrieveUser(final UUID userUUID) throws FailedDataOperationException {
        return datamanager.retrieveUser(userUUID);
    }
    
    public void setUserAttribute(final UUID userUUID, final String key, final String value) throws FailedDataOperationException {
        datamanager.setUserAttribute(userUUID, key, value);
    }
    
    public void removeUserAttribute(final UUID userUUID, final String key) throws FailedDataOperationException {
        datamanager.removeUserAttribute(userUUID, key);
    }
    
    public List<UserAccount> getUsersByAttribute(final String key, final String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttribute(key, value);
    }
    
    public List<UserAccount> getUsersByAttributeKey(final String key) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeKey(key);
    }
    
    public List<UserAccount> getUsersByAttributeValue(final String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeValue(value);
    }

    public boolean tryLockUser(final UUID uuid) throws FailedDataOperationException {
        return datamanager.tryLockUser(uuid);
    }

    //The processing is done
    public void unlockUser(final UUID uuid) throws FailedDataOperationException {
        datamanager.unlockUser(uuid);
    }

    //Checks if the user is locked
    public boolean isUserLocked(final UUID userUUID) throws FailedDataOperationException {
        return datamanager.isUserLocked(userUUID);
    }

    public void storeOffenseProcessingTemplate(final OffenseProcessingTemplate template) throws FailedDataOperationException {
        datamanager.storeOffenseProcessingTemplate(template);
    }

    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(final UUID templateUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseProcessingTemplate(templateUUID);
    }

    public boolean deleteOffenseProcessingTemplate(final UUID templateUUID) throws FailedDataOperationException {
        return datamanager.deleteOffenseProcessingTemplate(templateUUID);
    }

    public boolean updateOffenseProcessorUUIDInOffenseProcessingTemplate(final UUID templateUUID, final UUID newOffenseProcessorUUID) throws FailedDataOperationException {
        return datamanager.updateOffenseProcessorUUIDInOffenseProcessingTemplate(templateUUID, newOffenseProcessorUUID);
    }

    public boolean updateScriptFile(final UUID templateUUID, final File newScriptFile) throws FailedDataOperationException {
        return datamanager.updateScriptFile(templateUUID, newScriptFile);
    }
}
