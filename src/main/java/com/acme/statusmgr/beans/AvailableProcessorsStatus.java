package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.systemInfo.FacadeInterface;

public class AvailableProcessorsStatus extends StatusDecorator {
    private final Integer requestCost = 3;

    public AvailableProcessorsStatus(Status status, FacadeInterface facade)
    {
        super(status, facade);
    }

    @Override
    public String getStatusDesc() {
        return getBaseStatusDesc() + ", and there are " + getFacade().getAvailableProcessors() + " processors available";
    }

    @Override
    public Integer getRequestCost() {
        return getBaseRequestCost() + requestCost;
    }
}
