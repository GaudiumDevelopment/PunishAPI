package me.superbiebel.punishapi.offenseprocessing;

import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.AbstractService;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.common.dataobjects.verdict.OffenseHistoryRecord;
import me.superbiebel.punishapi.common.dataobjects.requestoffenseprocessing.PunishmentCalculationRequest;
import me.superbiebel.punishapi.common.dataobjects.verdict.Punishment;
import me.superbiebel.punishapi.common.dataobjects.verdict.PunishmentCalculation;
import me.superbiebel.punishapi.exceptions.FailedDataOperationException;
import me.superbiebel.punishapi.exceptions.OffenseProcessingException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OffenseManager extends AbstractService {

    private final PunishCore core;
    private IOffenseCalculator offenseCalculator;

    public OffenseManager(PunishCore core) {
        this.core = core;
    }

    public OffenseHistoryRecord calculateRecord(@NotNull PunishmentCalculationRequest punishmentCalculationRequest) throws OffenseProcessingException {
        Datamanager datamanager = core.getDatamanager();
        OffenseHistoryRecord historyRecord;
        try {
            datamanager.tryLockUser(punishmentCalculationRequest.getCriminalUUID());
            List<PunishmentCalculation> calculations = offenseCalculator.processOffense(punishmentCalculationRequest);
            List<Punishment> punishments = new ArrayList<>();
            calculations.forEach(punishmentCalculation -> punishments.add(Punishment.builder()
                                                                                  .punishmentUUID(UUID.randomUUID())
                                                                                  .attributes(new HashMap<>())
                                                                                  .punishmentReductions(new ArrayList<>())
                                                                                  .build()));
            historyRecord = OffenseHistoryRecord.builder()
                           .recordUUID(UUID.randomUUID())
                           .moderatorUUID(punishmentCalculationRequest.getModeratorUUID())
                           .criminalUUID(punishmentCalculationRequest.getCriminalUUID())
                           .timeRegistered(ZonedDateTime.now())
                           .offenses(punishmentCalculationRequest.getOffenses())
                           .attributes(new HashMap<>())
                           .linkedPunishments(punishments)
                           .build();
            
            datamanager.storeOffenseRecord(historyRecord);
        } catch (FailedDataOperationException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                datamanager.unlockUser(punishmentCalculationRequest.getCriminalUUID());
            } catch (FailedDataOperationException e) {
                throw new RuntimeException(e);
            }
        }
        return historyRecord;
    }
    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseHistoryRecord) throws FailedDataOperationException {
        core.getDatamanager().storeOffenseRecord(offenseHistoryRecord);
    }
    
    @Override
    protected void onStartup(boolean force) throws StartupException {
        //TODO add punishmentCalculator loading
    }
    
    @Override
    protected void onShutdown() throws ShutDownException {
        //TODO add punishmentCalculator shutdown
    }
    
    @Override
    protected void onKill() throws ShutDownException {
        //TODO add punishmentCalculator kill
    }
}
