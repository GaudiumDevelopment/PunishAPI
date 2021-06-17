package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Punishment {
    private UUID uuid;
    private Map<String,String> attributes;
    private long startTime;
    private long originalDuration;
    private long duration;
    private boolean activated;
    private List<String> scopes;
}
