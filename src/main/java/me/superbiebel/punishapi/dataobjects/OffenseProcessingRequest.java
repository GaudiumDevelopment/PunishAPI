package me.superbiebel.punishapi.dataobjects;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@Builder(toBuilder = true)
public class OffenseProcessingRequest {

    private final UUID criminalUUID;
    private final UUID moderatorUUID;

    private final UUID processingTemplateUUID;
    private final Map<String, String> attributes;
}
