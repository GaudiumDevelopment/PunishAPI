package me.superbiebel.punishapi.data;

import me.superbiebel.punishapi.abstractions.Service;

public interface DataService extends Service {
    Datamanager.DataServiceType[] supportsDataOperations();
}
