package com.acme.statusmgr.beans.facade;

/**
 * A mock implementation of the FacadeInterface interface,
 * providing predefined mock data for server status information.
 */
public class Mock implements FacadeInterface {

    /**
     * Returns a mock server status.
     */
    @Override
    public String getServerStatus() {
        return "up";
    }

    /**
     * Returns a mock number of available processors.
     */
    @Override
    public int getAvailableProcessors() {
        return 4;
    }

    /**
     * Returns a mock amount of free JVM memory.
     */
    @Override
    public long getFreeJVMMemory() {
        return 127268272L;
    }

    /**
     * Returns a mock total amount of JVM memory.
     */
    @Override
    public long getTotalJVMMemory() {
        return 159383552L;
    }

    /**
     * Returns a mock version of the Java Runtime Environment (JRE).
     */
    @Override
    public String getJREVersion() {
        return "15.0.2+7-27";
    }

    /**
     * Returns a mock temporary file location.
     */
    @Override
    public String getTempLocation() {
        return "M:\\\\AppData\\\\Local\\\\Temp";
    }
}
