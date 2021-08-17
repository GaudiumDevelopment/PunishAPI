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
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * The class where the application can safely modify any data.
 */
public class DataAPI {
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
    public OffenseHistoryRecord retrieveOffenseRecord(final UUID offenseUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseRecord(offenseUUID);
    }

    /**
     * Retrieve user account.
     *
     * @param userUUID the user uuid
     * @return the user account
     * @throws FailedDataOperationException the failed data operation exception
     */
    public UserAccount retrieveUser(final UUID userUUID) throws FailedDataOperationException {
        return datamanager.retrieveUser(userUUID);
    }

    /**
     * Sets user attribute.
     *
     * @param userUUID the user uuid
     * @param key      the key
     * @param value    the value
     * @throws FailedDataOperationException the failed data operation exception
     */
    public void setUserAttribute(final UUID userUUID, final String key, final String value) throws FailedDataOperationException {
        datamanager.setUserAttribute(userUUID, key, value);
    }

    /**
     * Remove user attribute.
     *
     * @param userUUID the user uuid
     * @param key      the key
     * @throws FailedDataOperationException the failed data operation exception
     */
    public void removeUserAttribute(final UUID userUUID, final String key) throws FailedDataOperationException {
        datamanager.removeUserAttribute(userUUID, key);
    }

    /**
     * Gets users by attribute.
     *
     * @param key   the key
     * @param value the value
     * @return the users by attribute
     * @throws FailedDataOperationException the failed data operation exception
     */
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
    public List<UserAccount> getUsersByAttributeValue(final String value) throws FailedDataOperationException {
        return datamanager.getUsersByAttributeValue(value);
    }

    /**
     * Indicates to the data services that that users has done something bad, and an offense is currently being calculated and processed.
     * This can be an actual lock HOWEVER IT SHOULD NEVER ACTUALLY BLOCK! This is just to make sure that services dont grab old data when processing another offense from another location.
     * @param uuid the uuid of the user on which the offense is being processed.
     * @return true if a lock is acquired, false if already locked.
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public boolean tryLockUser(final UUID uuid) throws FailedDataOperationException {
        return datamanager.tryLockUser(uuid);
    }

    /**
     * Indicates that the processing of the offense has stopped.
     *
     * @param uuid the uuid
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public void unlockUser(final UUID uuid) throws FailedDataOperationException {
        datamanager.unlockUser(uuid);
    }

    /**
     * Checks if the user is locked.
     *
     * @param userUUID the user uuid
     * @return the boolean
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public boolean isUserLocked(final UUID userUUID) throws FailedDataOperationException {
        return datamanager.isUserLocked(userUUID);
    }

    /**
     * Store an offense processing template, see the wiki for what an offense processing template actually is.
     *
     * @param template the template
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public void storeOffenseProcessingTemplate(final OffenseProcessingTemplate template) throws FailedDataOperationException {
        datamanager.storeOffenseProcessingTemplate(template);
    }

    /**
     * Retrieve an offense processing template by UUID.
     *
     * @param templateUUID the template UUID
     * @return the offense processing template
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(final UUID templateUUID) throws FailedDataOperationException {
        return datamanager.retrieveOffenseProcessingTemplate(templateUUID);
    }

    /**
     * Delete an offense processing template by UUID.
     *
     * @param templateUUID the template uuid
     * @return the boolean
     * @throws FailedDataOperationException thrown if something went wrong.
     */
    public boolean deleteOffenseProcessingTemplate(final UUID templateUUID) throws FailedDataOperationException {
        return datamanager.deleteOffenseProcessingTemplate(templateUUID);
    }

    /**
     * Change the offense processor UUID for this offense processing template.
     *
     * @param templateUUID            the template uuid
     * @param newOffenseProcessorUUID the new offense processor uuid
     * @return the boolean
     * @throws FailedDataOperationException the failed data operation exception
     */
    public boolean updateOffenseProcessorUUIDInOffenseProcessingTemplate(final UUID templateUUID, final UUID newOffenseProcessorUUID) throws FailedDataOperationException {
        return datamanager.updateOffenseProcessorUUIDInOffenseProcessingTemplate(templateUUID, newOffenseProcessorUUID);
    }

    /**
     * Change scriptfile for this offense processing template.
     *
     * @param templateUUID  the template uuid
     * @param newScriptFile the new script file
     * @return the boolean
     * @throws FailedDataOperationException the failed data operation exception
     */
    public boolean updateScriptFile(final UUID templateUUID, final File newScriptFile) throws FailedDataOperationException {
        return datamanager.updateScriptFile(templateUUID, newScriptFile);
    }
}
