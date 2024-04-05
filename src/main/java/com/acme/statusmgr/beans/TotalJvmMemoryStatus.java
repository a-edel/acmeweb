package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.facade.FacadeInterface;

public class TotalJvmMemoryStatus extends StatusDecorator {
    private final Integer requestCost = 13;

    public TotalJvmMemoryStatus(Status status, FacadeInterface facade)
    {
        super(status, facade);
    }

    public String getStatusDesc() {
        return getBaseStatusDesc() + ", and there is a total of " + getFacade().getTotalJVMMemory() + " bytes of JVM memory";
    }

    public Integer getRequestCost() {
        return getBaseRequestCost() + requestCost;
    }
}