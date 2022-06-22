package me.superbiebel.punishapi.dataobjects.requestoffenseprocessing;

import lombok.Builder;
import lombok.Getter;
import me.superbiebel.punishapi.dataobjects.template.OffenseTemplate;

import java.util.Map;

@Getter
@Builder(toBuilder = true)
public class Offense {
    private final OffenseTemplate offenseTemplate;
    private final Map<String, String> filledInAttributes;
}
