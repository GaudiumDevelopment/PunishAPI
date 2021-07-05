package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.offenseprocessing.dataobjects.OffenseProcessingTemplate;

import java.util.UUID;

public interface OffenseProcessingTemplateStorage extends DataService {
    void storeOffenseProcessingTemplate(OffenseProcessingTemplate template);
    void retrieveOffenseProcessingTemplate(UUID templateUUID);
}
