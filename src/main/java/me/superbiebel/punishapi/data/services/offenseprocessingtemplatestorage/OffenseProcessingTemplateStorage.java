package me.superbiebel.punishapi.data.services.offenseprocessingtemplatestorage;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;

public interface OffenseProcessingTemplateStorage extends DataService, OffenseProcessingTemplateStorageOperations {
    default Datamanager.DataServiceType serviceType() {
        return Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE;
    }
}
