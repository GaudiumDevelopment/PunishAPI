package me.superbiebel.punishapi.dataobjects;

import java.io.File;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@Builder
public class OffenseProcessingTemplate {
    private final UUID offenseProcessingTemplateUUID;
    private final UUID offenseProcessorUUID;
    @Nullable
    private final File scriptFile;
}
