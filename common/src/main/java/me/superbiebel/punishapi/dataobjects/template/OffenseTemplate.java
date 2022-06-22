package me.superbiebel.punishapi.dataobjects.template;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

/**
 * This is something that went wrong
 */
@Getter
@Builder(toBuilder = true)
public class OffenseTemplate {
    private final UUID offenseTemplateUUID;
    private final String displayName;
    private final Map<String, OffenseAttributeTemplate> attributes;
}
