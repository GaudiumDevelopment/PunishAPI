package me.superbiebel.punishapi.data.servicesoperations;

import java.io.File;
import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface OffenseProcessingTemplateStorageOperations {
    void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException;

    OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException;

    boolean deleteOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException;

    boolean updateOffenseProcessorUUIDInOffenseProcessingTemplate(UUID templateUUID, UUID newOffenseProcessorUUID) throws FailedDataOperationException;

    boolean updateScriptFile(UUID templateUUID, File newScriptFile) throws FailedDataOperationException;
}
