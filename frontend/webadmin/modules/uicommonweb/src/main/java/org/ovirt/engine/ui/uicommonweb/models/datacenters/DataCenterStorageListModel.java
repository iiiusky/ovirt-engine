package org.ovirt.engine.ui.uicommonweb.models.datacenters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ovirt.engine.core.common.ActionUtils;
import org.ovirt.engine.core.common.action.ActionParametersBase;
import org.ovirt.engine.core.common.action.ActionType;
import org.ovirt.engine.core.common.action.AttachStorageDomainToPoolParameters;
import org.ovirt.engine.core.common.action.DetachStorageDomainFromPoolParameters;
import org.ovirt.engine.core.common.action.RemoveStorageDomainParameters;
import org.ovirt.engine.core.common.action.StorageDomainPoolParametersBase;
import org.ovirt.engine.core.common.businessentities.StorageDomain;
import org.ovirt.engine.core.common.businessentities.StorageDomainSharedStatus;
import org.ovirt.engine.core.common.businessentities.StorageDomainStatic;
import org.ovirt.engine.core.common.businessentities.StorageDomainStatus;
import org.ovirt.engine.core.common.businessentities.StorageDomainType;
import org.ovirt.engine.core.common.businessentities.StorageFormatType;
import org.ovirt.engine.core.common.businessentities.StoragePool;
import org.ovirt.engine.core.common.businessentities.storage.StorageType;
import org.ovirt.engine.core.common.queries.IdQueryParameters;
import org.ovirt.engine.core.common.queries.QueryType;
import org.ovirt.engine.ui.frontend.Frontend;
import org.ovirt.engine.ui.uicommonweb.Linq;
import org.ovirt.engine.ui.uicommonweb.UICommand;
import org.ovirt.engine.ui.uicommonweb.dataprovider.AsyncDataProvider;
import org.ovirt.engine.ui.uicommonweb.help.HelpTag;
import org.ovirt.engine.ui.uicommonweb.models.ConfirmationModel;
import org.ovirt.engine.ui.uicommonweb.models.EntityModel;
import org.ovirt.engine.ui.uicommonweb.models.ListModel;
import org.ovirt.engine.ui.uicommonweb.models.Model;
import org.ovirt.engine.ui.uicommonweb.models.SearchableListModel;
import org.ovirt.engine.ui.uicompat.ConstantsManager;
import org.ovirt.engine.ui.uicompat.NotifyCollectionChangedEventArgs;
import org.ovirt.engine.ui.uicompat.PropertyChangedEventArgs;

@SuppressWarnings("unused")
public class DataCenterStorageListModel extends SearchableListModel<StoragePool, StorageDomain> {

    private UICommand attachStorageCommand;

    public UICommand getAttachStorageCommand() {
        return attachStorageCommand;
    }

    private void setAttachStorageCommand(UICommand value) {
        attachStorageCommand = value;
    }

    private UICommand attachISOCommand;

    public UICommand getAttachISOCommand() {
        return attachISOCommand;
    }

    private void setAttachISOCommand(UICommand value) {
        attachISOCommand = value;
    }

    private UICommand attachBackupCommand;

    public UICommand getAttachBackupCommand() {
        return attachBackupCommand;
    }

    private void setAttachBackupCommand(UICommand value) {
        attachBackupCommand = value;
    }

    private UICommand detachCommand;

    public UICommand getDetachCommand() {
        return detachCommand;
    }

    private void setDetachCommand(UICommand value) {
        detachCommand = value;
    }

    private UICommand activateCommand;

    public UICommand getActivateCommand() {
        return activateCommand;
    }

    private void setActivateCommand(UICommand value) {
        activateCommand = value;
    }

    private UICommand maintenanceCommand;

    public UICommand getMaintenanceCommand() {
        return maintenanceCommand;
    }

    private void setMaintenanceCommand(UICommand value) {
        maintenanceCommand = value;
    }

    public void setEntity(StoragePool value) {
        super.setEntity(value);
    }

    private StorageDomainType storageDomainType = StorageDomainType.values()[0];

    public StorageDomainType getStorageDomainType() {
        return storageDomainType;
    }

    public void setStorageDomainType(StorageDomainType value) {
        storageDomainType = value;
    }

    // A list of 'detach' action parameters
    private ArrayList<ActionParametersBase> detachParams;

    private ArrayList<ActionParametersBase> getDetachParams() {
        return detachParams;
    }

    private void setDetachParams(ArrayList<ActionParametersBase> value) {
        detachParams = value;
    }

    // A list of 'remove' action parameters
    private ArrayList<ActionParametersBase> removeParams;

    private ArrayList<ActionParametersBase> getRemoveParams() {
        return removeParams;
    }

    private void setRemoveParams(ArrayList<ActionParametersBase> value) {
        removeParams = value;
    }

    private ArrayList<StorageDomain> selectedStorageDomains;

    public DataCenterStorageListModel() {
        setTitle(ConstantsManager.getInstance().getConstants().storageTitle());
        setHelpTag(HelpTag.storage);
        setHashName("storage"); //$NON-NLS-1$

        setAttachStorageCommand(new UICommand("AttachStorage", this)); //$NON-NLS-1$
        setAttachISOCommand(new UICommand("AttachISO", this)); //$NON-NLS-1$
        setAttachBackupCommand(new UICommand("AttachBackup", this)); //$NON-NLS-1$
        setDetachCommand(new UICommand("Detach", this)); //$NON-NLS-1$
        setActivateCommand(new UICommand("Activate", this)); //$NON-NLS-1$
        setMaintenanceCommand(new UICommand("Maintenance", this)); //$NON-NLS-1$

        updateActionAvailability();
    }

    @Override
    protected void onEntityChanged() {
        super.onEntityChanged();
        getSearchCommand().execute();
    }

    @Override
    public void search() {
        if (getEntity() != null) {
            // omer - overriding AsyncSearch - using query instead of search
            // SearchString = StringFormat.format("storage: datacenter={0}", Entity.name);
            super.search();
        }
    }

    @Override
    protected void syncSearch() {
        IdQueryParameters tempVar = new IdQueryParameters(getEntity().getId());
        tempVar.setRefresh(getIsQueryFirstTime());
        Frontend.getInstance().runQuery(QueryType.GetStorageDomainsByStoragePoolId, tempVar, new SetItemsAsyncQuery());
    }

    public void onMaintenance() {
        ArrayList<ActionParametersBase> pb = new ArrayList<>();
        for (StorageDomain a : getSelectedItems()) {
            pb.add(new StorageDomainPoolParametersBase(a.getId(), getEntity().getId()));
        }

        final ConfirmationModel confirmationModel = (ConfirmationModel) getWindow();
        confirmationModel.startProgress();

        Frontend.getInstance().runMultipleAction(ActionType.DeactivateStorageDomainWithOvfUpdate, pb, result -> {
            confirmationModel.stopProgress();
            setWindow(null);
        });
    }

    private void maintenance() {
        ConfirmationModel model = new ConfirmationModel();
        model.setTitle(ConstantsManager.getInstance().getConstants().maintenanceStorageDomainsTitle());
        model.setMessage(ConstantsManager.getInstance().getConstants().areYouSureYouWantToPlaceFollowingStorageDomainsIntoMaintenanceModeMsg());
        model.setHashName("maintenance_storage_domain"); //$NON-NLS-1$
        setWindow(model);

        ArrayList<String> items = new ArrayList<>();
        for (Object selected : getSelectedItems()) {
            items.add(((StorageDomain) selected).getName());
        }
        model.setItems(items);

        UICommand maintenance = UICommand.createDefaultOkUiCommand("OnMaintenance", this); //$NON-NLS-1$
        model.getCommands().add(maintenance);

        UICommand cancel = UICommand.createCancelUiCommand("Cancel", this); //$NON-NLS-1$
        model.getCommands().add(cancel);
    }

    public void activate() {
        ArrayList<ActionParametersBase> pb = new ArrayList<>();
        for (StorageDomain a : getSelectedItems()) {
            pb.add(new StorageDomainPoolParametersBase(a.getId(), getEntity().getId()));
        }

        Frontend.getInstance().runMultipleAction(ActionType.ActivateStorageDomain, pb);
    }

    public void attachBackup() {
        ListModel listModel = new ListModel();
        listModel.setTitle(ConstantsManager.getInstance().getConstants().attachExportDomainTitle());
        listModel.setHelpTag(HelpTag.attach_export_domain);
        listModel.setHashName("attach_export_domain"); //$NON-NLS-1$
        attachInternal(listModel, StorageDomainType.ImportExport);
    }

    public void attachISO() {
        ListModel listModel = new ListModel();
        listModel.setTitle(ConstantsManager.getInstance().getConstants().attachISOLibraryTitle());
        listModel.setHelpTag(HelpTag.attach_iso_library);
        listModel.setHashName("attach_iso_library"); //$NON-NLS-1$
        attachInternal(listModel, StorageDomainType.ISO);
    }

    public void attachStorage() {
        ListModel listModel = new ListModel();
        listModel.setTitle(ConstantsManager.getInstance().getConstants().attachStorageTitle());
        listModel.setHelpTag(HelpTag.attach_storage);
        listModel.setHashName("attach_storage"); //$NON-NLS-1$
        attachInternal(listModel, StorageDomainType.Data);
    }

    private void attachInternal(ListModel listModel, StorageDomainType storageType) {
        if (getWindow() != null) {
            return;
        }

        this.setStorageDomainType(storageType);

        setWindow(listModel);

        if (storageType == StorageDomainType.ISO) {
            AsyncDataProvider.getInstance().getISOStorageDomainList(new AsyncQuery<>(list -> {
                ArrayList<EntityModel> models;
                models = new ArrayList<>();
                Collection<StorageDomain> items1 = getItems() != null ? getItems() : new ArrayList<>();
                for (StorageDomain a : list) {
                    if (items1.stream().noneMatch(new Linq.IdPredicate<>(a.getId()))) {
                        EntityModel tempVar = new EntityModel();
                        tempVar.setEntity(a);
                        models.add(tempVar);
                    }
                }
                postAttachInternal(models);

            }));
        }
        else {

            AsyncDataProvider.getInstance().getStorageDomainList(new AsyncQuery<>(list -> {
                ArrayList<EntityModel> models = new ArrayList<>();
                boolean addToList;
                Collection<StorageDomain> items1 = getItems() != null ? getItems() : new ArrayList<>();
                for (StorageDomain a : list) {
                    addToList = false;
                    if (a.getStorageDomainSharedStatus() != StorageDomainSharedStatus.Unattached ||
                            items1.stream().anyMatch(new Linq.IdPredicate<>(a.getId()))) {
                        continue;
                    }
                    if (a.getStorageDomainType() == StorageDomainType.Volume) {
                        addToList = true;
                    }
                    else if (a.getStorageDomainType() == getStorageDomainType()) {
                        if (getStorageDomainType() == StorageDomainType.Data) {
                            if (getEntity().getStoragePoolFormatType() == null) {
                                addToList = true;
                            }
                            else if (getEntity().getStoragePoolFormatType().compareTo(a.getStorageStaticData()
                                    .getStorageFormat()) >= 0) {
                                addToList = true;
                            }
                            else {
                                if (a.getStorageStaticData().getStorageFormat() == StorageFormatType.V1
                                        || a.getStorageStaticData().getStorageFormat() == StorageFormatType.V2) {
                                    addToList = true;
                                }
                            }
                        }
                        else if (getStorageDomainType() == StorageDomainType.ImportExport) {
                            addToList = true;
                        }
                    }
                    if (addToList) {
                        EntityModel tempVar2 = new EntityModel();
                        tempVar2.setEntity(a);
                        models.add(tempVar2);
                    }
                }
                postAttachInternal(models);
            }));
        }

    }

    private void postAttachInternal(ArrayList<EntityModel> models) {
        ListModel listModel = (ListModel) getWindow();
        listModel.setItems(models);

        if (models.isEmpty()) {
            listModel.setMessage(ConstantsManager.getInstance()
                    .getConstants()
                    .thereAreNoCompatibleStorageDomainsAttachThisDcMsg());

            UICommand tempVar = new UICommand("Cancel", this); //$NON-NLS-1$
            tempVar.setTitle(ConstantsManager.getInstance().getConstants().close());
            tempVar.setIsDefault(true);
            tempVar.setIsCancel(true);
            listModel.getCommands().add(tempVar);
        }
        else {
            UICommand tempVar2 = UICommand.createDefaultOkUiCommand("OnAttach", this); //$NON-NLS-1$
            listModel.getCommands().add(tempVar2);
            UICommand tempVar3 = UICommand.createCancelUiCommand("Cancel", this); //$NON-NLS-1$
            listModel.getCommands().add(tempVar3);
        }
    }

    public void onAttach() {
        ListModel<EntityModel<StorageDomain>> model = (ListModel<EntityModel<StorageDomain>>) getWindow();
        ArrayList<StorageDomain> selectedDataStorageDomains = new ArrayList<>();

        if (getEntity() == null) {
            cancel();
            return;
        }

        selectedStorageDomains = new ArrayList<>();
        for (EntityModel<StorageDomain> a : model.getItems()) {
            if (a.getIsSelected()) {
                StorageDomain storageDomain = a.getEntity();
                selectedStorageDomains.add(storageDomain);
                if (storageDomain.getStorageDomainType() == StorageDomainType.Data) {
                    selectedDataStorageDomains.add(storageDomain);
                }
            }
        }

        if (selectedStorageDomains.isEmpty()) {
            cancel();
            return;
        }

        AsyncDataProvider.getInstance().getStorageDomainsWithAttachedStoragePoolGuid(
                new AsyncQuery<>(attachedStorageDomains -> {
                    if (!attachedStorageDomains.isEmpty()) {
                        ConfirmationModel confirmationModel = new ConfirmationModel();
                        setWindow(null);
                        setWindow(confirmationModel);

                        List<String> stoageDomainNames = new ArrayList<>();
                        for (StorageDomainStatic domain : attachedStorageDomains) {
                            stoageDomainNames.add(domain.getStorageName());
                        }
                        confirmationModel.setItems(stoageDomainNames);

                        confirmationModel.setTitle(ConstantsManager.getInstance()
                                .getConstants()
                                .storageDomainsAttachedToDataCenterWarningTitle());
                        confirmationModel.setMessage(ConstantsManager.getInstance()
                                .getConstants()
                                .storageDomainsAttachedToDataCenterWarningMessage());

                        confirmationModel.setHelpTag(HelpTag.attach_storage_domain_confirmation);
                        confirmationModel.setHashName("attach_storage_domain_confirmation"); //$NON-NLS-1$

                        confirmationModel.getLatch().setIsAvailable(true);
                        confirmationModel.getLatch().setIsChangeable(true);

                        UICommand onApprove = new UICommand("OnAttachApprove", DataCenterStorageListModel.this); //$NON-NLS-1$
                        onApprove.setTitle(ConstantsManager.getInstance().getConstants().ok());
                        onApprove.setIsDefault(true);
                        confirmationModel.getCommands().add(onApprove);

                        UICommand cancel = new UICommand("Cancel", DataCenterStorageListModel.this); //$NON-NLS-1$
                        cancel.setTitle(ConstantsManager.getInstance().getConstants().cancel());
                        cancel.setIsCancel(true);
                        confirmationModel.getCommands().add(cancel);
                    } else {
                        executeAttachStorageDomains();
                    }
                }), getEntity(), selectedDataStorageDomains);
    }

    public void onAttachApprove() {
        ConfirmationModel model = (ConfirmationModel) getWindow();
        if (!model.validate()) {
            return;
        }

        executeAttachStorageDomains();
    }

    public void executeAttachStorageDomains() {
        ArrayList<ActionParametersBase> pb = new ArrayList<>();
        for (StorageDomain storageDomain : selectedStorageDomains) {
            pb.add(new AttachStorageDomainToPoolParameters(storageDomain.getId(), getEntity().getId()));
        }
        Frontend.getInstance().runMultipleAction(ActionType.AttachStorageDomainToPool, pb);
        cancel();
    }

    public void detach() {
        if (getWindow() != null) {
            return;
        }

        ConfirmationModel model = new ConfirmationModel();
        setWindow(model);
        model.setTitle(ConstantsManager.getInstance().getConstants().detachStorageTitle());
        model.setHelpTag(HelpTag.detach_storage);
        model.setHashName("detach_storage"); //$NON-NLS-1$
        model.setMessage(ConstantsManager.getInstance().getConstants().areYouSureYouWantDetachFollowingStoragesMsg());

        ArrayList<String> list = new ArrayList<>();
        boolean shouldAddressWarnning = false;
        for (StorageDomain item : getSelectedItems()) {
            list.add(item.getStorageName());
            if (item.getStorageDomainType().isDataDomain()) {
                shouldAddressWarnning = true;
                break;
            }
        }
        model.setItems(list);

        if (containsLocalStorage(model)) {
            shouldAddressWarnning = false;
            model.getForce().setIsAvailable(true);
            model.getForce().setIsChangeable(true);
            model.setForceLabel(ConstantsManager.getInstance().getConstants().storageRemovePopupFormatLabel());

            model.setNote(ConstantsManager.getInstance().getMessages().detachNote(getLocalStoragesFormattedString()));
        }

        if (shouldAddressWarnning) {
            model.setNote(ConstantsManager.getInstance().getConstants().detachWarnningNote());
        }
        UICommand tempVar = UICommand.createDefaultOkUiCommand("OnDetach", this); //$NON-NLS-1$
        model.getCommands().add(tempVar);
        UICommand tempVar2 = UICommand.createCancelUiCommand("Cancel", this); //$NON-NLS-1$
        model.getCommands().add(tempVar2);
    }

    private String getLocalStoragesFormattedString() {
        StringBuilder localStorages = new StringBuilder();
        for (StorageDomain a : getSelectedItems()) {
            if (a.getStorageType() == StorageType.LOCALFS) {
                localStorages.append(a.getStorageName()).append(", "); //$NON-NLS-1$
            }
        }
        return localStorages.substring(0, localStorages.length() - 2);
    }

    private boolean containsLocalStorage(ConfirmationModel model) {
        for (StorageDomain a : getSelectedItems()) {
            if (a.getStorageType() == StorageType.LOCALFS) {
                return true;
            }
        }
        return false;
    }

    public void onDetach() {
        final ConfirmationModel confirmModel = (ConfirmationModel) getWindow();

        if (confirmModel.getProgress() != null) {
            return;
        }

        if (!confirmModel.validate()) {
            return;
        }

        // A list of 'detach' action parameters
        setDetachParams(new ArrayList<>());
        // A list of 'remove' action parameters
        setRemoveParams(new ArrayList<>());
        String localStorgaeDC = null;
        for (StorageDomain a : getSelectedItems()) {
            // For local storage - remove; otherwise - detach
            if (a.getStorageType() == StorageType.LOCALFS && a.getStorageDomainType() != StorageDomainType.ISO) {
                getRemoveParams().add(new RemoveStorageDomainParameters(a.getId()));
                localStorgaeDC = a.getStoragePoolName();
            }
            else {
                getDetachParams().add(new DetachStorageDomainFromPoolParameters(a.getId(), getEntity().getId()));
            }
        }

        confirmModel.startProgress();

        if (getRemoveParams().size() > 0) {
            AsyncDataProvider.getInstance().getLocalStorageHost(new AsyncQuery<>(locaVds -> {
                for (ActionParametersBase item : getRemoveParams()) {
                    ((RemoveStorageDomainParameters) item).setVdsId(locaVds != null ? locaVds.getId() : null);
                    ((RemoveStorageDomainParameters) item).setDoFormat(confirmModel.getForce().getEntity());
                }

                postDetach(getWindow());
            }), localStorgaeDC);
        }
        else {
            postDetach(confirmModel);
        }
    }

    public void postDetach(Model model) {
        Frontend.getInstance().runMultipleAction(ActionType.RemoveStorageDomain, getRemoveParams(),
                outerResult -> {

                    Object[] array = (Object[]) outerResult.getState();
                    ConfirmationModel localModel1 = (ConfirmationModel) array[0];
                    ArrayList<ActionParametersBase> parameters =
                            (ArrayList<ActionParametersBase>) array[1];
                    Frontend.getInstance().runMultipleAction(ActionType.DetachStorageDomainFromPool, parameters,
                            innerResult -> {
                                ConfirmationModel localModel2 = (ConfirmationModel) innerResult.getState();
                                localModel2.stopProgress();
                                cancel();

                            }, localModel1);

                }, new Object[] { model, getDetachParams() });
    }

    public void cancel() {
        setWindow(null);
    }

    @Override
    protected void onSelectedItemChanged() {
        super.onSelectedItemChanged();
        updateActionAvailability();
    }

    @Override
    protected void selectedItemsChanged() {
        super.selectedItemsChanged();
        updateActionAvailability();
    }

    @Override
    protected void itemsCollectionChanged(Object sender, NotifyCollectionChangedEventArgs e) {
        super.itemsCollectionChanged(sender, e);
        updateActionAvailability();
    }

    @Override
    protected void itemsChanged() {
        super.itemsChanged();
        updateActionAvailability();
    }

    @Override
    protected void selectedItemPropertyChanged(Object sender, PropertyChangedEventArgs e) {
        super.selectedItemPropertyChanged(sender, e);

        if (e.propertyName.equals("status")) { //$NON-NLS-1$
            updateActionAvailability();
        }
    }

    @Override
    protected void itemPropertyChanged(Object sender, PropertyChangedEventArgs e) {
        super.itemPropertyChanged(sender, e);

        if (e.propertyName.equals("status")) { //$NON-NLS-1$
            updateActionAvailability();
        }
    }

    @Override
    protected boolean getNotifyPropertyChangeForAnyItem() {
        return true;
    }

    private void updateActionAvailability() {
        Collection<StorageDomain> items = getItems() != null ? getItems() : new ArrayList<>();
        List<StorageDomain> selectedItems =
                getSelectedItems() != null ? getSelectedItems() : new ArrayList<>();
        getAttachStorageCommand().setIsExecutionAllowed(true);

        boolean isMasterPresent = false;
        boolean isISOPresent = false;
        boolean isBackupPresent = false;
        for (StorageDomain domain : items) {
            if (isDomainMasterAndActive(domain)) {
                isMasterPresent = true;
            } else if (domain.getStorageDomainType() == StorageDomainType.ISO) {
                isISOPresent = true;
            } else if (domain.getStorageDomainType() == StorageDomainType.ImportExport) {
                isBackupPresent = true;
            }

            if (isMasterPresent && isISOPresent && isBackupPresent) {
                break;
            }
        }

        getAttachISOCommand().setIsExecutionAllowed(false);
        getAttachISOCommand().setIsExecutionAllowed(items.size() > 0 && isMasterPresent && !isISOPresent);

        getAttachBackupCommand().setIsExecutionAllowed(items.size() > 0 && isMasterPresent && !isBackupPresent);

        getDetachCommand().setIsExecutionAllowed(selectedItems.size() > 0
                && ActionUtils.canExecute(selectedItems,
                StorageDomain.class,
                ActionType.DetachStorageDomainFromPool));

        getActivateCommand().setIsExecutionAllowed(selectedItems.size() == 1
                && ActionUtils.canExecute(selectedItems, StorageDomain.class, ActionType.ActivateStorageDomain));

        getMaintenanceCommand().setIsExecutionAllowed(selectedItems.size() == 1
                && ActionUtils.canExecute(selectedItems,
                StorageDomain.class,
                ActionType.DeactivateStorageDomainWithOvfUpdate));
    }

    private boolean isDomainMasterAndActive(StorageDomain domain) {
        return domain.getStorageDomainType() == StorageDomainType.Master && domain.getStatus() == StorageDomainStatus.Active;
    }

    @Override
    public void executeCommand(UICommand command) {
        super.executeCommand(command);

        if (command == getAttachStorageCommand()) {
            attachStorage();
        }
        else if (command == getAttachISOCommand()) {
            attachISO();
        }
        else if (command == getAttachBackupCommand()) {
            attachBackup();
        }
        else if (command == getDetachCommand()) {
            detach();
        }
        else if (command == getActivateCommand()) {
            activate();
        }
        else if (command == getMaintenanceCommand()) {
            maintenance();
        }
        else if ("OnAttach".equals(command.getName())) { //$NON-NLS-1$
            onAttach();
        }
        else if ("OnAttachApprove".equals(command.getName())) { //$NON-NLS-1$
            onAttachApprove();
        }
        else if ("OnDetach".equals(command.getName())) { //$NON-NLS-1$
            onDetach();
        }
        else if ("OnMaintenance".equals(command.getName())) { //$NON-NLS-1$
            onMaintenance();
        }
        else if ("Cancel".equals(command.getName())) { //$NON-NLS-1$
            cancel();
        }
    }

    @Override
    protected String getListName() {
        return "DataCenterStorageListModel"; //$NON-NLS-1$
    }
}
