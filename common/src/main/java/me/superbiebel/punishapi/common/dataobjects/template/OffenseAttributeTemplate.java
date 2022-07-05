package me.superbiebel.punishapi.common.dataobjects.template;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class OffenseAttributeTemplate {
    private final String attributeName; //MUST BE UNIQUE
    private final String value;
    private final boolean isMandatory;
    private final List<ValueType> allowedTypes;
    private final List<String> possibleValues;
    enum ValueType {
        STRING, INT, LIST, DOUBLE, BOOLEAN
    }
}
