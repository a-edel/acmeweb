package com.acme.statusmgr.beans.systemInfo;

import com.acme.servermgr.ServerManager;

/**
 * An implementation of the FacadeInterface,
 * providing a facade to access real data for system information.
 */
public class Facade implements FacadeInterface{
    @Override
    public String getServerStatus() {
        return ServerManager.getCurrentServerStatus();
    }

    @Override
    public int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long getFreeJVMMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getTotalJVMMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @Override
    public String getJREVersion() {
        return System.getProperty("java.version");
    }

    @Override
    public String getTempLocation() {
        return System.getProperty("java.io.tmpdir");
    }
}
