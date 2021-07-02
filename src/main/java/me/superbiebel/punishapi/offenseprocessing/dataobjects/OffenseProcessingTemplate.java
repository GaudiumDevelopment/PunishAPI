package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OffenseProcessingTemplate {
    
    protected UUID offenseProcessingTemplateUUID;
    protected long timeregistered;
    protected Map<String, String> attributes;
    
    public OffenseProcessingTemplate(UUID offenseProcessingTemplateUUID, long timeregistered, Map<String, String> attributes) {
        this.offenseProcessingTemplateUUID = offenseProcessingTemplateUUID;
        this.timeregistered = timeregistered;
        this.attributes = attributes;
    }
}
