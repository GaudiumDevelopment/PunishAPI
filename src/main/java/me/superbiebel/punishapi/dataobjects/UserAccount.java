package me.superbiebel.punishapi.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@Builder
public class UserAccount {
    private final UUID userUUID;
    private final Map<String, String> attributes;
    private final List<OffenseHistoryRecord> offenseHistory;
}
