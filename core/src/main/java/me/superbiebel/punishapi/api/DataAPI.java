package me.superbiebel.punishapi.api;

import java.io.File;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.Service;
import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.serviceoperations.dataapi.LockCheckOperations;
import me.superbiebel.punishapi.data.serviceoperations.dataapi.OffenseHistoryRecordAPIOperations;
import me.superbiebel.punishapi.data.serviceoperations.UserAccountAttributeOperations;
import me.superbiebel.punishapi.data.serviceoperations.UserRetrieveOperations;
import me.superbiebel.punishapi.common.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.common.dataobjects.verdict.Punishment;
import me.superbiebel.punishapi.common.dataobjects.UserAccount;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceAlreadyRegisteredException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * The class where the application can safely modify any data.
 */
@SuppressWarnings("ALL")
public class DataAPI implements OffenseHistoryRecordAPIOperations
                                        , LockCheckOperations
                                        , UserAccountAttributeOperations
                                        , UserRetrieveOperations {
    private final Datamanager datamanager;

    /**
     * Instantiates a new Data api, internal only.
     *
     * @param core the core
     */
    @ApiStatus.Internal
    protected DataAPI(final PunishCore core) {
        core.setDataAPI(this);
        datamanager = core.getDatamanager();
    }

    /**
     * Adds and starts up the service.
     *
     * @param dataServiceType the type of operations it supports.
     * @param service         th service that is to be added.
     * @throws StartupException                  thrown when there went something wrong during startup.
     * @throws ServiceAlreadyRegisteredException thrown when the service is already registered exception
     */
    public void addService(final Datamanager.DataServiceType dataServiceType, final DataService service) throws StartupException, ServiceAlreadyRegisteredException {
        datamanager.addService(dataServiceType, service);
    }

    /**
     * Removes and shuts down the service.
     *
     * @param dataServiceType the data service type
     * @param kill            the kill
     * @return the service
     * @throws ShutDownException        the shut down exception
     * @throws ServiceNotFoundException the service not found exception
     */
    @Nullable
    public Service removeService(final Datamanager.DataServiceType dataServiceType, final boolean kill) throws ShutDownException, ServiceNotFoundException {
        return datamanager.removeService(dataServiceType, kill);
    }

    /**
     * Gets service.
     *
     * @param dataServiceType the data service type
     * @return the service
     * @throws ServiceNotFoundException the service not found exception
     */
    public Service getService(final Datamanager.DataServiceType dataServiceType) throws ServiceNotFoundException {
        return datamanager.getService(dataServiceType);
    }
    
    /**
     * Retrieve the record of an offense that was processed and stored.
     *
     * @param offenseUUID the offense uuid
     * @return the offense history record
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public OffenseHistoryRecord retrieveOffenseRecord(final UUID offenseUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseRecord(offenseUUID);
    }
    
    @Override
    public boolean setOffenseHistoryRecordAttribute(UUID offenseUUID, String key, String value) throws FailedDataOperationException {
        return datamanager.setOffenseHistoryRecordAttribute(offenseUUID, key, value);
    }
    
    @Override
    public boolean removeOffenseHistoryRecordAttributeWithKey(UUID offenseUUID, String key) throws FailedDataOperationException {
        return datamanager.removeOffenseHistoryRecordAttributeWithKey(offenseUUID, key);
    }
    
    @Override
    public boolean removeOffenseHistoryRecordAttributeWithValue(UUID offenseUUID, String value) throws FailedDataOperationException {
        return datamanager.removeOffenseHistoryRecordAttributeWithValue(offenseUUID, value);
    }
    
    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttribute(String key, String value) throws FailedDataOperationException {
        return datamanager.getOffenseHistoryRecordByAttribute(key, value);
    }
    
    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeKey(String key) throws FailedDataOperationException {
        return datamanager.getOffenseHistoryRecordByAttributeKey(key);
    }
    
    @Override
    public List<OffenseHistoryRecord> getOffenseHistoryRecordByAttributeValue(String value) throws FailedDataOperationException {
        return datamanager.getOffenseHistoryRecordByAttributeValue(value);
    }
    
    @Override
    public Punishment retrievePunishment(UUID offenseUUID) throws FailedDataOperationException {
        return datamanager.retrievePunishment(offenseUUID);
    }
    
    @Override
    public boolean setPunishmentAttribute(UUID punishmentUUID, String key, String value) throws FailedDataOperationException {
        return datamanager.setPunishmentAttribute(punishmentUUID, key, value);
    }
    
    @Override
    public boolean removePunishmentAttributeWithKey(UUID punishmentUUID, String key) throws FailedDataOperationException {
        return datamanager.removePunishmentAttributeWithKey(punishmentUUID,key);
    }
    
    @Override
    public boolean removePunishmentAttributeWithValue(UUID punishmentUUID, String value) throws FailedDataOperationException {
        return datamanager.removePunishmentAttributeWithValue(punishmentUUID, value);
    }
    
    @Override
    public List<Punishment> getPunishmentByAttribute(String key, String value) throws FailedDataOperationException {
        return datamanager.getPunishmentByAttribute(key, value);
    }
    
    @Override
    public List<Punishment> getPunishmentByAttributeKey(String key) throws FailedDataOperationException {
        return datamanager.getPunishmentByAttributeKey(key);
    }
    
    @Override
    public List<Punishment> getPunishmentByAttributeValue(String value) throws FailedDataOperationException {
        return datamanager.getPunishmentByAttributeValue(value);
    }
    
    @Override
    public boolean setDecrementsPunishmentDuration(UUID punishmentUUID, boolean value) throws FailedDataOperationException {
        return datamanager.setDecrementsPunishmentDuration(punishmentUUID, value);
    }
    
    @Override
    public boolean setPunishmentActivation(UUID punishmentUUID, boolean value) throws FailedDataOperationException {
        return datamanager.setPunishmentActivation(punishmentUUID, value);
    }
    
    /**
     * Retrieve user account.
     *
     * @param userUUID the user uuid
     * @return the user account
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public UserAccount retrieveUser(final UUID userUUID) throws FailedDataOperationException {
        return datamanager.retrieveUser(userUUID);
    }

    /**
     * Sets user attribute.
     *
     * @param userUUID the user uuid
     * @param key      the key
     * @param value    the value
     * @return if the attribute was already there
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public boolean setUserAttribute(final UUID userUUID, final String key, final String value) throws FailedDataOperationException {
        return datamanager.setUserAttribute(userUUID, key, value);
    }

    /**
     * Remove user attribute.
     *
     * @param userUUID the user uuid
     * @param key      the key
     * @return true if the userAttribute could be found and removed, false if not exist
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public boolean removeUserAttribute(final UUID userUUID, final String key) throws FailedDataOperationException {
        return datamanager.removeUserAttribute(userUUID, key);
    }

    /**
     * Gets users by attribute.
     *
     * @param key   the key
     * @param value the value
     * @return the users by attribute
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public List<UserAccount> getUsersByAttribute(final String key, final String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttribute(key, value);
    }

    /**
     * Gets users by attribute key.
     *
     * @param key the key
     * @return the users by attribute key
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public List<UserAccount> getUsersByAttributeKey(final String key) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeKey(key);
    }

    /**
     * Gets users by attribute value.
     *
     * @param value the value
     * @return the users by attribute value
     * @throws FailedDataOperationException the failed data operation exception
     */
    @Override
    public List<UserAccount> getUsersByAttributeValue(final String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeValue(value);
    }

    /**
     * Checks if the user is locked.
     *
     * @param userUUID the user uuid
     * @return the boolean
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    @Override
    public boolean isUserLocked(final UUID userUUID) throws FailedDataOperationException {
        return datamanager.isUserLocked(userUUID);
    }

    /**
     * Store an offense processing template, see the wiki for what an offense processing template actually is.
     *
     * @param template the template
     * @throws FailedDataOperationException thrown if something went wrong.
     */
}
