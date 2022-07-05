package me.superbiebel.punishapi.common.dataobjects.requestoffenseprocessing;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class PunishmentCalculationRequest {

    private final UUID criminalUUID;
    private final UUID moderatorUUID;

    private final List<Offense> offenses;
    private final Map<String, String> attributes;
}
