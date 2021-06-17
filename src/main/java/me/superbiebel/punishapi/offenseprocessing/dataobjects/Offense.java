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
}
