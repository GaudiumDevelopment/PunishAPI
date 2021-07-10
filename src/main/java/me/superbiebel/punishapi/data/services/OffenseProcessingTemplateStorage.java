package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;

import java.util.UUID;

public interface OffenseProcessingTemplateStorage extends DataService {
    void storeOffenseProcessingTemplate(OffenseProcessingTemplate template);
    OffenseProcessingTemplate retrieveOffenseProcessingTemplate(UUID templateUUID);
    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE;
    }
}
