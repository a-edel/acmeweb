package com.acme.statusmgr.beans.facade;

import com.acme.servermgr.ServerManager;

/**
 * Implementation of the FacadeInterface interface
 * providing access to various server status information.
 */
public class Facade implements FacadeInterface {

    /**
     * Retrieves the status of the server from the ServerManager.
     */
    @Override
    public String getServerStatus() {
        return ServerManager.getCurrentServerStatus();
    }

    /**
     * Retrieves the number of available processors on the server.
     */
    @Override
    public int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * Retrieves the amount of free JVM memory on the server.
     */
    @Override
    public long getFreeJVMMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * Retrieves the total amount of JVM memory on the server.
     */
    @Override
    public long getTotalJVMMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * Retrieves the version of the Java Runtime Environment running on the server.
     */
    @Override
    public String getJREVersion() {
        return Runtime.version().toString();
    }

    /**
     * Retrieves the temporary file location on the server.
     */
    @Override
    public String getTempLocation() {
        return System.getenv("TEMP");
    }
}
