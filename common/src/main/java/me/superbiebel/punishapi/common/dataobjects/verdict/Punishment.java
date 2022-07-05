package me.superbiebel.punishapi.common.dataobjects.verdict;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import me.superbiebel.punishapi.common.dataobjects.PunishmentReduction;

@Getter
@Builder
public class Punishment {
    private final UUID punishmentUUID;
    private final Map<String, String> attributes;
    private final PunishmentCalculation calculation;
    private final List<PunishmentReduction> punishmentReductions;
}
