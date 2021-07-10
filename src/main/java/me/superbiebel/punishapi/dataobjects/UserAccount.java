package me.superbiebel.punishapi.dataobjects;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@Builder
public class UserAccount {
    private final UUID userUUID;
    private final Map<String, String> attributes;
}
