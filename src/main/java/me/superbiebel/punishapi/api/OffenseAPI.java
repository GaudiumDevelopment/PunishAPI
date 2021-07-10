package me.superbiebel.punishapi.api;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.services.OffenseProcessingTemplateStorage;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;

import java.util.UUID;

public class OffenseAPI {
    private final PunishCore core;
    
    public OffenseAPI(PunishCore core) {
        this.core = core;
    }
    public void submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException, ServiceNotFoundException {
        core.getOffenseManager().submitOffense(offenseProcessingRequest);
    }
    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseRecord) throws ServiceNotFoundException {
        core.getOffenseManager().submitOffenseWithoutProcessing(offenseRecord);
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
