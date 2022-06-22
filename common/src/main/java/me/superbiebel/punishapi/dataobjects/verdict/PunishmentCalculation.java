package me.superbiebel.punishapi.dataobjects.verdict;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder(toBuilder = true)
public class PunishmentCalculation {
    private final long startTime;
    private final long originalDuration;
    private final boolean activated;
    private final List<String> scopes;
    private final Map<String, String> attributes;
    
    //if the duration counts down
    private final boolean decrementsDuration;
    private final long durationLeft;
}
