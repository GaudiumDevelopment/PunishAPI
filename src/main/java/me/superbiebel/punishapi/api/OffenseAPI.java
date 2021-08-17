package me.superbiebel.punishapi.api;

import java.util.UUID;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;

@SuppressWarnings("ClassCanBeRecord")
public class OffenseAPI {
    private final PunishCore core;

    public OffenseAPI(PunishCore core) {
        this.core = core;
    }

    public OffenseHistoryRecord submitOffense(OffenseProcessingRequest offenseProcessingRequest) throws ServiceNotFoundException, OffenseProcessingException, FailedDataOperationException {
        return core.getOffenseManager().submitOffense(offenseProcessingRequest);
    }

    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseRecord) throws FailedDataOperationException {
        core.getOffenseManager().submitOffenseWithoutProcessing(offenseRecord);
    }

    public void createOffenseProcessingTemplate(OffenseProcessingTemplate template) throws FailedDataOperationException {
        core.getDatamanager().storeOffenseProcessingTemplate(template);
    }

    public void retrieveOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        core.getDatamanager().retrieveOffenseProcessingTemplate(templateUUID);
    }
    public void deleteOffenseProcessingTemplate(UUID templateUUID) throws FailedDataOperationException {
        core.getDatamanager().deleteOffenseProcessingTemplate(templateUUID);
    }


}
