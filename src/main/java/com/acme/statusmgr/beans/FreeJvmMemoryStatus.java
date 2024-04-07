package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.systemInfo.FacadeInterface;

public class FreeJvmMemoryStatus extends StatusDecorator {
    private final Integer requestCost = 7;
    public FreeJvmMemoryStatus(Status status, FacadeInterface facade)
    {
        super(status, facade);
    }

    public String getStatusDesc() {
        return getBaseStatusDesc() + ", and there are " + getFacade().getFreeJVMMemory() + " bytes of JVM memory free";
    }

    public Integer getRequestCost() {
        return getBaseRequestCost() + requestCost;
    }
}
