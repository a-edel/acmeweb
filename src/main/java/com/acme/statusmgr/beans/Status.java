package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.FacadeInterface;

public abstract class Status {
    private long id;                // Unique identifier of request, sequential number
    private String contentHeader;   // Some info about the request
    private String statusDesc = "Unknown";  // the status being returned
    protected Integer requestCost;


    /**
     * get the id of this request
     *
     * @return a numeric id that increases during life of server for each request .
     */
    public long getId() {
        return id;
    }

    /**
     * Get the content header that was specified by the request
     *
     * @return some string
     */
    public String getContentHeader() {
        return contentHeader;
    }

    /**
     * Get an english-like description of the server's status
     *
     * @return A string describing status
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Get the cost of this request
     * @return Integer representing the cost of request as number of pennies
     */
    public Integer getRequestCost() {
        return requestCost;
    }
}
