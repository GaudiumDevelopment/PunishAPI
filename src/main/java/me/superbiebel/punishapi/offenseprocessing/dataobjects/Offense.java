package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Offense {
    private UUID uuid;
    private long timeregistered;
    private Map<String,String> attributes;
}
