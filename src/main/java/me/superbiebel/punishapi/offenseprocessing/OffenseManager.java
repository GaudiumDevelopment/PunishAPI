package me.superbiebel.punishapi.offenseprocessing;

import lombok.Getter;
import me.superbiebel.punishapi.PunishCore;
import me.superbiebel.punishapi.abstractions.ServiceRegistry;
import me.superbiebel.punishapi.data.Datamanager;
import me.superbiebel.punishapi.dataobjects.OffenseHistoryRecord;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingRequest;
import me.superbiebel.punishapi.dataobjects.OffenseProcessingTemplate;
import me.superbiebel.punishapi.exceptions.FailedServiceOperationException;
import me.superbiebel.punishapi.exceptions.ServiceNotFoundException;
import me.superbiebel.punishapi.exceptions.ShutDownException;
import me.superbiebel.punishapi.exceptions.StartupException;
import me.superbiebel.punishapi.offenseprocessing.services.IOffenseProcessor;
import me.superbiebel.punishapi.offenseprocessing.services.premade.JSOffenseProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class OffenseManager extends ServiceRegistry<String> {
    
    private final PunishCore core;
    @Getter
    private final JSOffenseProcessor defaultOffenseProcessor;
    
    public OffenseManager(PunishCore core) {
        super(new ConcurrentHashMap<>());
        this.core = core;
        this.defaultOffenseProcessor = new JSOffenseProcessor(this.core);
    }
    
    /**
     * 1. Receives the request
     * 2. Gets the UUID of the offenseProcessingTemplate it wants to trigger and will download this template
     * 3. A custom offenseprocessor that is already supplied will be used otherwise the default processor will be used (JS)
     * 4. The offenseprocessor will process this offense and then generate an OffenseHistoryRecord
     * 5. The generated offenseHistoryRecord will then be stored inside the database.
     */
    
    public OffenseHistoryRecord submitOffense(@NotNull OffenseProcessingRequest offenseProcessingRequest) throws ServiceNotFoundException, FailedServiceOperationException {
        Datamanager datamanager = core.getDatamanager();
        try {
            datamanager.lockUser(offenseProcessingRequest.getCriminalUUID());
            OffenseProcessingTemplate template = datamanager.retrieveOffenseProcessingTemplate(offenseProcessingRequest.getProcessingTemplateUUID());
            IOffenseProcessor processor = (IOffenseProcessor) super.getService(template.getOffenseProcessorID());
            if (processor.isScriptBased()) {
                processor.processOffense(offenseProcessingRequest,template.getScriptFile());
            }
        } finally {
            datamanager.unlockUser(offenseProcessingRequest.getCriminalUUID());
        }
        return null;
    }
    public void submitOffenseWithoutProcessing(OffenseHistoryRecord offenseHistoryRecord) throws ServiceNotFoundException {
        core.getDatamanager().storeOffense(offenseHistoryRecord);
    }
    private OffenseHistoryRecord submitOffense(OffenseProcessingRequest offenseProcessingRequest, IOffenseProcessor offenseProcessor, @Nullable File scriptFile) throws ServiceNotFoundException, FailedServiceOperationException {
            OffenseHistoryRecord offenseHistoryRecord = offenseProcessor.processOffense(offenseProcessingRequest, scriptFile);
            
            core.getDatamanager().storeOffense(offenseHistoryRecord);
            return offenseHistoryRecord;
        
    }
    
    @Override
    protected void onServiceRegistryStartup(boolean force) throws StartupException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRegistryShutdown() throws ShutDownException {
        //implement if needed
    }
    
    @Override
    protected void onServiceRegistryKill() throws ShutDownException {
        //implement if needed
    }
}
