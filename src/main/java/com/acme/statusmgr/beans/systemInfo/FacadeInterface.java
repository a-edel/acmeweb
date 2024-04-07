package com.acme.statusmgr.beans.systemInfo;

/**
 * The FacadeInterface provides a facade for methods that obtain system information from various sources
 */
public interface FacadeInterface {
    /**
     * Retrieves the server status.
     *
     * @return The server status as a String.
     */
    String getServerStatus();

    /**
     * Retrieves the number of available processors.
     *
     * @return The number of available processors.
     */
    int getAvailableProcessors();

    /**
     * Retrieves the amount of free JVM memory.
     *
     * @return The amount of free JVM memory in bytes.
     */
    long getFreeJVMMemory();

    /**
     * Retrieves the total JVM memory.
     *
     * @return The total JVM memory in bytes.
     */
    long getTotalJVMMemory();

    /**
     * Retrieves the JRE version.
     *
     * @return The JRE version as a String.
     */
    String getJREVersion();

    /**
     * Retrieves the location of the temporary files.
     *
     * @return The location of the temporary files as a String.
     */
    String getTempLocation();
}
