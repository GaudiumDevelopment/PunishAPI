package me.superbiebel.punishapi.offenseprocessing.dataobjects;

import org.graalvm.polyglot.HostAccess;

import java.util.List;
import java.util.Map;

public class OffenseProcessingResult {
    @HostAccess.Export
    public Map<String, String> attributes;
    @HostAccess.Export
    public List<Punishment> linkedPunishments;
}
