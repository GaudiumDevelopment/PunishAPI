package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Offense {
    protected UUID uuid;
    protected long timeregistered;
    protected Map<String,String> attributes;
    protected List<Punishment> linkedPunishments;
    
    public Offense(UUID uuid, long timeregistered, Map<String, String> attributes, List<Punishment> linkedPunishments) {
        this.uuid = uuid;
        this.timeregistered = timeregistered;
        this.attributes = attributes;
        this.linkedPunishments = linkedPunishments;
    }
}
