package com.acme.statusmgr.beans;

import com.acme.statusmgr.FacadeInterface;

public class FreeJvmMemoryStatus extends Status {
    private Status status;
    private FacadeInterface facade;
    private final Integer requestCost = 7;

    public FreeJvmMemoryStatus(Status status, FacadeInterface facade)
    {
        this.status = status;
        this.facade = facade;
    }

    /**
     * get the id of this request
     *
     * @return a numeric id that increases during life of server for each request .
     */
    public long getId() {
        return status.getId();
    }

    /**
     * Get the content header that was specified by the request
     *
     * @return some string
     */
    public String getContentHeader() {
        return status.getContentHeader();
    }

    /**
     * Get an english-like description of the server's status
     *
     * @return A string describing status
     */
    public String getStatusDesc() {
        return status.getStatusDesc() + ", and there are " + facade.getFreeJVMMemory() + " bytes of JVM memory free";
    }

    /**
     * Get the cost of this request
     * @return Integer representing the cost of request as number of pennies
     */
    public Integer getRequestCost() {
        return status.getRequestCost() + requestCost;
    }
}
