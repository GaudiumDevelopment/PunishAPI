package me.superbiebel.punishapi.api;

import java.util.UUID;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.services.offenseprocessingtemplatestorage.OffenseProcessingTemplateStorage;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;

public class OffenseAPI {
    private final PunishCore core;

    public OffenseAPI(PunishCore core) {
        this.core = core;
    }

    public OffenseHistoryRecord submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws FailedServiceOperationException, ServiceNotFoundException, OffenseProcessingException, FailedDataOperationException {
        return core.getOffenseManager().submitOffense(offenseProcessingRequest);
    }

    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseRecord) throws FailedDataOperationException {
        core.getOffenseManager().submitOffenseWithoutProcessing(offenseRecord);
    }

    public void createOffenseProcessingTemplate(OffenseProcessingTemplate template) throws ServiceNotFoundException, FailedDataOperationException {
        OffenseProcessingTemplateStorage service = (OffenseProcessingTemplateStorage) core.getDatamanager().getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE);
        service.storeOffenseProcessingTemplate(template);
    }

    public void retrieveOffenseProcessingTemplate(UUID templateUUID) throws ServiceNotFoundException, FailedDataOperationException {
        OffenseProcessingTemplateStorage service = (OffenseProcessingTemplateStorage) core.getDatamanager().getService(Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE);
        service.retrieveOffenseProcessingTemplate(templateUUID);
    }


}
