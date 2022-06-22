package me.superbiebel.punishapi.dataobjects.verdict;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import me.superbiebel.punishapi.dataobjects.requestoffenseprocessing.Offense;
import me.superbiebel.punishapi.dataobjects.template.OffenseTemplate;

@Getter
@Builder
public class OffenseHistoryRecord {
    private final UUID recordUUID;
    private final UUID moderatorUUID;
    private final UUID criminalUUID;
    private final ZonedDateTime timeRegistered;
    private final List<Offense> offenses;
    private final Map<String, String> attributes;
    private final List<Punishment> linkedPunishments;
}
