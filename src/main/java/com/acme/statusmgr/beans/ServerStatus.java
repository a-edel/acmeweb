package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.systemInfo.FacadeInterface;

/**
 * Represents the status of a server.
 * Implements the Status interface to ensure that it provides the necessary methods.
 */
public class ServerStatus implements Status {
    private long id;                // Unique identifier of request, sequential number
    private String contentHeader;   // Some info about the request
    private final Integer requestCost = 1;  // the cost in pennies of this request.
    private FacadeInterface facade;

    /**
     * Construct a ServerStatus using info passed in for identification.
     * This class must return a pretty, english-like representation of the server status.
     *
     * @param id            a numeric identifier/counter of which request this is
     * @param contentHeader info about the request
     * @param facade        a FacadeInterface injected to provide access to the facade for server status information.
     */
    public ServerStatus(long id, String contentHeader, FacadeInterface facade) {
        this.facade = facade;
        this.id = id;
        this.contentHeader = contentHeader;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getContentHeader() {
        return contentHeader;
    }

    @Override
    public String getStatusDesc() {
        return "Server is " + facade.getServerStatus();
    }

    @Override
    public Integer getRequestCost() {
        return requestCost;
    }
}
