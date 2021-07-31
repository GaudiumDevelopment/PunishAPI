package me.superbiebel.punishapi.dataobjects;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAccount {
    private final UUID userUUID;
    private final Map<String, String> attributes;
}
