package org.ovirt.engine.ui.common.widget.uicommon.popup.networkinterface;

import org.ovirt.engine.core.common.businessentities.Network;
import org.ovirt.engine.ui.common.CommonApplicationConstants;
import org.ovirt.engine.ui.common.idhandler.ElementIdHandler;
import org.ovirt.engine.ui.common.idhandler.WithElementId;
import org.ovirt.engine.ui.common.widget.Align;
import org.ovirt.engine.ui.common.widget.dialog.AdvancedParametersExpander;
import org.ovirt.engine.ui.common.widget.editor.EntityModelCheckBoxEditor;
import org.ovirt.engine.ui.common.widget.editor.EntityModelTextBoxEditor;
import org.ovirt.engine.ui.common.widget.editor.ListModelListBoxEditor;
import org.ovirt.engine.ui.common.widget.renderer.EnumRenderer;
import org.ovirt.engine.ui.common.widget.renderer.NullSafeRenderer;
import org.ovirt.engine.ui.common.widget.uicommon.popup.AbstractModelBoundPopupWidget;
import org.ovirt.engine.ui.uicommonweb.models.vms.VmInterfaceModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class NetworkInterfacePopupWidget extends AbstractModelBoundPopupWidget<VmInterfaceModel> {

    interface Driver extends SimpleBeanEditorDriver<VmInterfaceModel, NetworkInterfacePopupWidget> {
        Driver driver = GWT.create(Driver.class);
    }

    interface ViewUiBinder extends UiBinder<FlowPanel, NetworkInterfacePopupWidget> {
        ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
    }

    interface ViewIdHandler extends ElementIdHandler<NetworkInterfacePopupWidget> {
        ViewIdHandler idHandler = GWT.create(ViewIdHandler.class);
    }

    @UiField
    @Path("name.entity")
    @WithElementId("name")
    EntityModelTextBoxEditor nameEditor;

    @UiField(provided = true)
    @Path("network.selectedItem")
    @WithElementId("network")
    ListModelListBoxEditor<Object> networkEditor;

    @UiField(provided = true)
    @Path("nicType.selectedItem")
    @WithElementId("nicType")
    ListModelListBoxEditor<Object> nicTypeEditor;

    @UiField
    @Path("portMirroring.entity")
    @WithElementId("portMirroring")
    protected EntityModelCheckBoxEditor portMirroringEditor;

    @UiField
    @Ignore
    @WithElementId("enableManualMac")
    CheckBox enableManualMacCheckbox;

    @UiField
    @Ignore
    Label enableManualMacCheckboxLabel;

    @UiField
    @Path("MAC.entity")
    @WithElementId("mac")
    EntityModelTextBoxEditor MACEditor;

    @UiField
    @Ignore
    Label macExample;

    @UiField(provided = true)
    @Path("Active.entity")
    @WithElementId("activate")
    protected EntityModelCheckBoxEditor activateCheckBox;

    @UiField
    @Ignore
    AdvancedParametersExpander expander;

    @UiField
    @Ignore
    Panel expanderContent;

    public NetworkInterfacePopupWidget(EventBus eventBus, CommonApplicationConstants constants) {
        initManualWidgets();
        initWidget(ViewUiBinder.uiBinder.createAndBindUi(this));
        expander.initWithContent(expanderContent.getElement());
        localize(constants);
        ViewIdHandler.idHandler.generateAndSetIds(this);
        Driver.driver.initialize(this);
    }

    private void localize(CommonApplicationConstants constants) {
        nameEditor.setLabel(constants.nameNetworkIntefacePopup());
        networkEditor.setLabel(constants.networkNetworkIntefacePopup());
        nicTypeEditor.setLabel(constants.typeNetworkIntefacePopup());
        enableManualMacCheckboxLabel.setText(constants.specipyCustMacNetworkIntefacePopup());
        activateCheckBox.setLabel(constants.activateNetworkIntefacePopup());
        portMirroringEditor.setLabel(constants.portMirroringNetworkIntefacePopup());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initManualWidgets() {
        networkEditor = new ListModelListBoxEditor<Object>(new NullSafeRenderer<Object>() {
            @Override
            public String renderNullSafe(Object object) {
                return ((Network) object).getname();
            }
        });

        nicTypeEditor = new ListModelListBoxEditor<Object>(new EnumRenderer());

        activateCheckBox = new EntityModelCheckBoxEditor(Align.RIGHT);
    }

    @Override
    public void focusInput() {
        nameEditor.setFocus(true);
    }

    @Override
    public void edit(final VmInterfaceModel iface) {
        Driver.driver.edit(iface);
        enableManualMacCheckbox.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                iface.getMAC().setIsChangable(enableManualMacCheckbox.getValue());
            }
        });

        hideMacWhenNotEnabled(iface);
    }

    private void hideMacWhenNotEnabled(VmInterfaceModel iface) {
        if (!iface.getMAC().getIsAvailable()) {
            enableManualMacCheckbox.setVisible(false);
            enableManualMacCheckboxLabel.setVisible(false);
            MACEditor.setVisible(false);
            macExample.setVisible(false);
        }
    }

    @Override
    public VmInterfaceModel flush() {
        return Driver.driver.flush();
    }
}
