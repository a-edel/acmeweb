package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.facade.FacadeInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Abstract class representing a base decorator for decorating a Status object.
 */
@JsonPropertyOrder({"id", "contentHeader", "requestCost", "statusDesc"})
public abstract class StatusDecorator implements Status {
    private Status status;
    private FacadeInterface facade;

    /**
     * Constructs a StatusDecorator with the given Status object and FacadeInterface.
     *
     * @param status The Status object to decorate.
     * @param facade The FacadeInterface used for obtaining server status information.
     */
    public StatusDecorator(Status status, FacadeInterface facade) {
        this.status = status;
        this.facade = facade;
    }

    /**
     * Retrieves a FacadeInterface used for obtaining server status information.
     *
     * @return A FacadeInterface.
     */
    @JsonIgnore
    public FacadeInterface getFacade() {
        return facade;
    }

    @Override
    public long getId() {
        return status.getId();
    }

    @Override
    public String getContentHeader() {
        return status.getContentHeader();
    }

    /**
     * Get an english-like description of the server's base status without the description of the details
     *
     * @return A string describing the base status
     */
    @JsonIgnore
    public String getBaseStatusDesc() {
        return status.getStatusDesc();
    }

    @Override
    public abstract String getStatusDesc();

    /**
     * Get the base cost of the request without the cost of the details
     *
     * @return Integer representing the base cost of request as number of pennies
     */
    @JsonIgnore
    public Integer getBaseRequestCost() {
        return status.getRequestCost();
    }

    @Override
    public abstract Integer getRequestCost();
}
