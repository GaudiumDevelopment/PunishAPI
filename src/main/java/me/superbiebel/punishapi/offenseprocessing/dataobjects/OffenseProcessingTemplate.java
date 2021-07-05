package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.util.UUID;

@Getter
@Builder
public class OffenseProcessingTemplate {
    private final UUID offenseProcessingTemplateUUID;
    private final String command;
    private final File commandFile;
}
