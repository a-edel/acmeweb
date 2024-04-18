package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.systemInfo.FacadeInterface;


public class JreVersionStatus extends StatusDecorator {
    private final Integer requestCost = 19;

    public JreVersionStatus(Status status, FacadeInterface facade)
    {
        super(status, facade);
    }

    @Override
    public String getStatusDesc() {
        return getBaseStatusDesc() + ", and the JRE version is " + getFacade().getJREVersion();
    }

    @Override
    public Integer getRequestCost() {
        return getBaseRequestCost() + requestCost;
    }
}