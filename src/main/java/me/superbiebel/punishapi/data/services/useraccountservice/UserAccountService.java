package me.superbiebel.punishapi.data.services.useraccountservice;

import me.superbiebel.punishapi.data.DataService;
import me.superbiebel.punishapi.data.Datamanager;

public interface UserAccountService extends DataService, UserAccountOperations {

    default Datamanager.DataServiceType serviceType(){
        return Datamanager.DataServiceType.USER_ACCOUNT_STORAGE;
    }
}
