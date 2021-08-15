package me.superbiebel.punishapi.data.servicesoperations;

import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public interface OffenseProcessingTemplateStorageOperations {
    void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException;

    OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException;

    void deleteOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException;
}
