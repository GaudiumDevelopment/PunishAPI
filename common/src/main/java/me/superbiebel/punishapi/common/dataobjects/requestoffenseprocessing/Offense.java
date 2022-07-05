package me.superbiebel.punishapi.common.dataobjects.requestoffenseprocessing;

import lombok.Builder;
import lombok.Getter;
import me.superbiebel.punishapi.common.dataobjects.template.OffenseTemplate;

import java.util.Map;

@Getter
@Builder(toBuilder = true)
public class Offense {
    private final OffenseTemplate offenseTemplate;
    private final Map<String, String> filledInAttributes;
}
