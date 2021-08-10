package me.superbiebel.punishapi.api.dataapi;

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

    public DataAPI(PunishCore core) {
        core.setDataAPI(this);
        datamanager = core.getDatamanager();
    }

    
    public void addService(Datamanager.DataServiceType dataServiceType, DataService service) throws StartupException, ServiceAlreadyRegisteredException {
        datamanager.addService(dataServiceType, service);
    }
    
    @Nullable
    public Service<Datamanager.DataServiceType> removeService(Datamanager.DataServiceType dataServiceType, boolean kill) throws ShutDownException, ServiceNotFoundException {
        return datamanager.removeService(dataServiceType, kill);
    }
    
    public Service<Datamanager.DataServiceType> getService(Datamanager.DataServiceType dataServiceType) throws ServiceNotFoundException {
        return datamanager.getService(dataServiceType);
    }
    
    public OffenseHistoryRecord retrieveOffense(UUID offenseUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseRecord(offenseUUID);
    }
    
    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseProcessingTemplate(templateUUID);
    }
    
    public UserAccount retrieveUser(UUID userUUID) throws FailedDataOperationException {
        return datamanager.retrieveUser(userUUID);
    }
    
    public void setUserAttribute(String key, String value) throws FailedDataOperationException {
        datamanager.setUserAttribute(key, value);
    }
    
    public void removeUserAttribute(String key) throws FailedDataOperationException {
        datamanager.removeUserAttribute(key);
    }
    
    public List<UserAccount> getUsersByAttribute(String key, String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttribute(key, value);
    }
    
    public List<UserAccount> getUsersByAttributekey(String key) throws FailedDataOperationException {
        return datamanager.getUsersByAttributekey(key);
    }
    
    public List<UserAccount> getUsersByAttributeValue(String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeValue(value);
    }
}
