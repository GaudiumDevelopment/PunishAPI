package me.superbiebel.punishapi.data.services.userlockservice;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;

public interface UserLockService extends DataService, UserLockOperations {

    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.USER_LOCKING;
    }
}
