package me.superbiebel.punishapi.data.services.offenseprocessingtemplatestorage;

import java.util.UUID;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;

public interface OffenseProcessingTemplateStorageOperations {
    void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException;
    OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws ServiceNotFoundException, FailedDataOperationException;
}
