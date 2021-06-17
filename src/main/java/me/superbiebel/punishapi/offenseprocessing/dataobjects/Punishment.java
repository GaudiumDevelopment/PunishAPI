package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Punishment {
    UUID uuid;
    Map<String,String> attributes;
    long startTime;
    long originalDuration;
    long duration;
    boolean activated;
    List<String> scopes;
}
