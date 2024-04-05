package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.facade.FacadeInterface;

public class TempLocationStatus extends StatusDecorator {
    private final Integer requestCost = 29;

    public TempLocationStatus(Status status, FacadeInterface facade)
    {
        super(status, facade);
    }

    public String getStatusDesc() {
        return getBaseStatusDesc() + ", and the server's temp file location is " + getFacade().getTempLocation();
    }

    public Integer getRequestCost() {
        return getBaseRequestCost() + requestCost;
    }
}