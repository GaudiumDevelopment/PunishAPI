package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.services.OffenseProcessingTemplateStorage;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingTemplate;

import java.io.IOException;
import java.util.UUID;

public class OffenseAPI {
    private final PunishCore core;
    
    public OffenseAPI(PunishCore core) {
        this.core = core;
    }
    public void submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException, ServiceNotFoundException {
        try {
            core.getOffenseManager().submitOffense(offenseProcessingRequest);
        } catch (IOException e) {
            throw new FailedServiceOperationException(e);
        }
    }
    public void createOffenseProcessingTemplate(OffenseProcessingTemplate template) throws ServiceNotFoundException {
        OffenseProcessingTemplateStorage service = (OffenseProcessingTemplateStorage) core.getDatamanager().getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE);
        service.storeOffenseProcessingTemplate(template);
    }
    public void retrieveOffenseProcessingTemplate(UUID templateUUID) throws ServiceNotFoundException {
        OffenseProcessingTemplateStorage service = (OffenseProcessingTemplateStorage) core.getDatamanager().getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE);
        service.retrieveOffenseProcessingTemplate(templateUUID);
    }
    
    
}
