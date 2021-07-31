package me.superbiebel.punishapi.dataobjects;

import java.io.File;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
@Builder
public class OffenseProcessingTemplate {
    private final UUID offenseProcessingTemplateUUID;
    private final String offenseProcessorID; //for custom offenseprocessors
    @Nullable
    private final File scriptFile; //currently only graalvm JS is supported.
}
