package me.superbiebel.punishapi.data.services;

import me.superbiebel.punishapi.offenseprocessing.dataobjects.Offense;
import me.superbiebel.punishapi.services.Service;

public interface OffenseRegistry extends Service {
    void storeOffense(Offense offense);
}
