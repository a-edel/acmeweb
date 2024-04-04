package com.acme.statusmgr;

public interface FacadeInterface {
    String getServerStatus();
    int getAvailableProcessors();
    long getFreeJVMMemory();
    long getTotalJVMMemory();
    String getJREVersion();
    String getTempLocation();
}
