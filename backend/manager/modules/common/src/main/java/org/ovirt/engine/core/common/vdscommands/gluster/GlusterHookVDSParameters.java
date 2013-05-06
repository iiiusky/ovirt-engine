package org.ovirt.engine.core.common.vdscommands.gluster;

import org.ovirt.engine.core.common.businessentities.gluster.GlusterHookStage;
import org.ovirt.engine.core.common.vdscommands.VdsIdVDSCommandParametersBase;
import org.ovirt.engine.core.compat.Guid;

/**
 * Gluster Hook VDS parameter class with gluster command, hook name, and level as parameters. <br>
 * This will be used by Gluster Hook VDS commands <br>
 */
public class GlusterHookVDSParameters extends VdsIdVDSCommandParametersBase {

    private String glusterCommand;
    private String hookName;
    private GlusterHookStage stage;
    private String content;
    private String checksum;

    public GlusterHookVDSParameters(Guid serverId, String glusterCommand, GlusterHookStage stage, String hookName) {
        super(serverId);
        setGlusterCommand(glusterCommand);
        setHookStage(stage);
        setHookName(hookName);
    }

    /**
     *
     * @param serverId
     * @param glusterCommand - gluster command the hook is intended for
     * @param stage - PRE/POST stage
     * @param hookName - file name
     * @param content - non-encoded text content
     */
    public GlusterHookVDSParameters(Guid serverId, String glusterCommand, GlusterHookStage stage, String hookName,
            String content, String checksum) {
        super(serverId);
        setGlusterCommand(glusterCommand);
        setHookStage(stage);
        setHookName(hookName);
        setHookContent(content);
        setChecksum(checksum);
    }

    public String getHookContent() {
        return content;
    }

    public void setHookContent(String content) {
        this.content = content;
    }

    public String getGlusterCommand() {
        return glusterCommand;
    }

    public void setGlusterCommand(String glusterCommand) {
        this.glusterCommand = glusterCommand;
    }

    public String getHookName() {
        return hookName;
    }

    public void setHookName(String hookName) {
        this.hookName = hookName;
    }

    public GlusterHookStage getHookStage() {
        return stage;
    }

    public void setHookStage(GlusterHookStage stage) {
        this.stage = stage;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

}
