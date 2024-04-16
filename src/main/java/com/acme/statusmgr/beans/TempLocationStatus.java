package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.systemInfo.FacadeInterface;

public class TempLocationStatus extends StatusDecorator {
    private final Integer requestCost = 29;

    public TempLocationStatus(Status status, FacadeInterface facade)
    {
        super(status, facade);
    }

    @Override
    public String getStatusDesc() {
        return getBaseStatusDesc() + ", and the server's temp file location is " + getFacade().getTempLocation();
    }

    @Override
    public Integer getRequestCost() {
        return getBaseRequestCost() + requestCost;
    }
}