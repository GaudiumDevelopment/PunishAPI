package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class OffenseProcessingRequest {
    private final UUID uuid;
    private final long timeregistered;
    private final Map<String,String> attributes;
}
