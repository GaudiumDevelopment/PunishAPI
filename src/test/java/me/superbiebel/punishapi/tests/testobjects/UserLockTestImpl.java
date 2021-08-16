package me.superbiebel.punishapi.tests.testobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.data.servicesoperations.UserLockOperations;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;

public class UserLockTestImpl extends BaseDataTestingService implements UserLockOperations {

    List<UUID> userLockList = new ArrayList<>();

    @Override
    public Datamanager.DataServiceType[] supportsDataOperations() {
        Datamanager.DataServiceType[] supportedDataOperations = new Datamanager.DataServiceType[1];
        supportedDataOperations[0] = Datamanager.DataServiceType.USER_LOCKING;
        return supportedDataOperations;
    }

    @Override
    public boolean tryLockUser(UUID uuid) throws FailedDataOperationException {
        if (userLockList.contains(uuid)) {
            return false;
        } else {
            userLockList.add(uuid);
            return true;
        }
    }

    @Override
    public void unlockUser(UUID uuid) throws FailedDataOperationException {
        userLockList.remove(uuid);
    }

    @Override
    public boolean isUserLocked(UUID userUUID) throws FailedDataOperationException {
        return userLockList.contains(userUUID);
    }
}
