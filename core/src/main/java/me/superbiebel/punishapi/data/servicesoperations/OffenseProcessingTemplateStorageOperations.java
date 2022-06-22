package me.superbiebel.punishapi.data.servicesoperations;

import java.io.File;
import java.util.UUID;

import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

/**
 * The interface to store all the offense processing templates
 */
public interface OffenseProcessingTemplateStorageOperations {
    /**
     * Store offense processing template.
     *
     * @param template the template that should be stored
     * @throws FailedDataOperationException thrown if something went wrong (wrapped)
     */
    void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException;

    /**
     * Retrieve offense processing template by UUID.
     *
     * @param templateUUID the template uuid
     * @return the offense processing template that was found, null if not found
     * @throws FailedDataOperationException thrown if something went wrong (wrapped)
     */
    OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException;

    /**
     * Delete offense processing template boolean.
     *
     * @param templateUUID the template uuid
     * @return true if sucessfully deleted, otherwise false
     * @throws FailedDataOperationException thrown if something went wrong (wrapped)
     */
    boolean deleteOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException;

    /**
     * Update offense processor uuid in offense processing template boolean.
     *
     * @param templateUUID            the template uuid
     * @param newOffenseProcessorUUID the new offense processor uuid
     * @throws FailedDataOperationException thrown if something went wrong (wrapped)
     */
    void updateOffenseProcessorUUIDInOffenseProcessingTemplate(UUID templateUUID, UUID newOffenseProcessorUUID) throws FailedDataOperationException;

    /**
     * Update script file boolean.
     *
     * @param templateUUID  the template uuid
     * @param newScriptFile the new script file
     * @return the boolean
     * @throws FailedDataOperationException thrown if something went wrong (wrapped)
     */
    boolean updateScriptFile(UUID templateUUID, File newScriptFile) throws FailedDataOperationException;
}
