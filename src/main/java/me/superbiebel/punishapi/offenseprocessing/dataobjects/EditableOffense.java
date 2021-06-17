package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EditableOffense extends Offense {
    public void setUuid(UUID uuid) {
        super.uuid = uuid;
    }
    
    public void setTimeregistered(long timeregistered) {
        super.timeregistered = timeregistered;
    }
    
    public void setAttributes(Map<String, String> attributes) {
        super.attributes = attributes;
    }
    
    public void setLinkedPunishments(List<Punishment> linkedPunishments) {
        super.linkedPunishments = linkedPunishments;
    }
}
