package me.superbiebel.punishapi.tests.testobjects;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.servicesoperations.OffenseProcessingTemplateStorageOperations;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public class OffenseProcessingTemplateStorageTestImpl extends BaseDataTestingService implements OffenseProcessingTemplateStorageOperations {

    private final HashMap<UUID,OffenseProcessingTemplate> offenseProcessingTemplateStorage = new HashMap<>();

    @Override
    public Datamanager.DataServiceType[] supportsDataOperations() {
        Datamanager.DataServiceType[] dataServiceTypes = new Datamanager.DataServiceType[1];
        dataServiceTypes[0] = Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE;
        return dataServiceTypes;
    }

    @Override
    public void storeOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException {
        offenseProcessingTemplateStorage.put(template.getOffenseProcessingTemplateUUID(), template);
    }

    @Override
    public OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        return offenseProcessingTemplateStorage.get(templateUUID);
    }

    @Override
    public boolean deleteOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        return offenseProcessingTemplateStorage.remove(templateUUID) != null;
    }

    @Override
    public boolean updateOffenseProcessorUUIDInOffenseProcessingTemplate(UUID templateUUID, UUID newOffenseProcessorUUID) throws FailedDataOperationException {
        OffenseProcessingTemplate fetchedTemplate = offenseProcessingTemplateStorage.get(templateUUID);
        OffenseProcessingTemplate newTemplate = OffenseProcessingTemplate.builder().offenseProcessingTemplateUUID(fetchedTemplate.getOffenseProcessingTemplateUUID())
                .offenseProcessorUUID(newOffenseProcessorUUID)
                .scriptFile(fetchedTemplate.getScriptFile())
                .build();
        return offenseProcessingTemplateStorage.put(templateUUID,newTemplate) == null;
    }

    @Override
    public boolean updateScriptFile(UUID templateUUID, File newScriptFile) throws FailedDataOperationException {
        OffenseProcessingTemplate fetchedTemplate = offenseProcessingTemplateStorage.get(templateUUID);
        OffenseProcessingTemplate newTemplate = OffenseProcessingTemplate.builder()
                .offenseProcessingTemplateUUID(fetchedTemplate.getOffenseProcessingTemplateUUID())
                .offenseProcessorUUID(fetchedTemplate.getOffenseProcessorUUID())
                .scriptFile(newScriptFile)
                .build();
        return offenseProcessingTemplateStorage.put(templateUUID,newTemplate) == null;
    }
}