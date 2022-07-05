package me.superbiebel.punishapi.tests.testobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.serviceoperations.UserAccountOperations;
import me.superbiebel.punishapi.common.dataobjects.UserAccount;
import org.junit.jupiter.api.Disabled;

@Disabled
public class UserAccountStorageTestImpl extends BaseDataTestingService implements UserAccountOperations {

    final HashMap<UUID, UserAccount> userAccountStorage = new HashMap<>();

    @Override
    public Datamanager.DataServiceType[] supportsDataOperations() {
        Datamanager.DataServiceType[] dataServiceTypes = new Datamanager.DataServiceType[1];
        dataServiceTypes[0] = Datamanager.DataServiceType.OFFENSE_PROCESSING_TEMPLATE_STORAGE;
        return dataServiceTypes;
    }

    @Override
    public UserAccount createUser( Map<String, String> attributes) {
        UserAccount userAccount = UserAccount.builder().userUUID(UUID.randomUUID()).attributes(attributes).offenseHistory(null).build();
        userAccountStorage.put(userAccount.getUserUUID(), userAccount);
        return userAccount;
    }

    @Override
    public UserAccount retrieveUser(UUID userUUID) {
        return userAccountStorage.get(userUUID);
    }

    @Override
    public boolean setUserAttribute(UUID userUUID, String key, String value) {
        UserAccount userAccount = userAccountStorage.get(userUUID);

        HashMap<String, String> newUserAttributes = new HashMap<>(userAccount.getAttributes());
        boolean succeeded = newUserAttributes.put(key, value)!= null;

        UserAccount newUserAccount = UserAccount.builder().userUUID(userAccount.getUserUUID()).attributes(newUserAttributes).offenseHistory(userAccount.getOffenseHistory()).build();
        succeeded = userAccountStorage.put(newUserAccount.getUserUUID(), newUserAccount) != null && succeeded;
        return succeeded;
    }

    @Override
    public boolean removeUserAttribute(UUID userUUID, String key) {
        UserAccount userAccount = userAccountStorage.get(userUUID);
        return userAccount.getAttributes().remove(key) != null;//this is cheating and I know it
    }

    @Override
    public List<UserAccount> getUsersByAttribute(String key, String value) {
        return null;
    }

    @Override
    public List<UserAccount> getUsersByAttributeKey(String key) {
        return null;
    }

    @Override
    public List<UserAccount> getUsersByAttributeValue(String value) {
        return null;
    }
}
