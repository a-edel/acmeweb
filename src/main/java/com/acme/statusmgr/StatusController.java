package com.acme.statusmgr;

import com.acme.statusmgr.beans.*;
import com.acme.statusmgr.beans.facade.Facade;
import com.acme.statusmgr.beans.facade.FacadeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 * All start with /server
 * /status  will give back status of server
 * a param of 'name' specifies a requester name to appear in response
 * <p>
 * Examples:
 * http://localhost:8080/server/status
 * <p>
 * http://localhost:8080/server/status?name=Noach
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();
    protected static FacadeInterface facade = new Facade();

    /**
     * Sets the facade interface to be used for retrieving system information.
     */
    public static void setSystemInfoFacade(FacadeInterface facade) {
        StatusController.facade = facade;
    }

    /**
     * Process a request for server status information
     *
     * @param name optional param identifying the requester
     * @return a ServerStatus object containing the info to be returned to the requester
     * @apiNote TODO since Spring picks apart the object returned with Reflection and doesn't care what the return-object's type is, we can change the type of object we return if necessary, as long as the object returned contained the required fields and getter methods.
     */
    @RequestMapping("/status")
    public ServerStatus getStatus(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        return new ServerStatus(counter.incrementAndGet(), String.format(template, name), facade);
    }

    /**
     * Process a request for detailed server status information
     *
     * @param name optional param identifying the requester
     * @param details optional param with a list of server status details being requested
     * @return a ServerStatus object containing the info to be returned to the requester
     *      * @apiNote TODO since Spring picks apart the object returned with Reflection and doesn't care what the return-object's type is, we can change the type of object we return if necessary
     */
    @RequestMapping("/status/detailed")
    public Status getDetailedStatus(
            @RequestParam(value = "name", defaultValue = "Anonymous") String name,
            @RequestParam(required = false) List<String> details) {

        Logger logger = LoggerFactory.getLogger("StatusController");
        logger.info("Processing detailed status request for {}", name); // Logging statement for processing detailed status request

        if (details == null) {
            String errorMessage = "Required request parameter 'details' for method parameter type List is not present";
            logger.error("Bad request error: {}", errorMessage); // Logging statement for Bad Request error
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }

        Status detailedStatus = new ServerStatus(counter.incrementAndGet(), String.format(template, name), facade);

        if (details != null) {
            logger.info("Details were provided: " + Arrays.toString(details.toArray()));

            for(String detail : details) {
                detailedStatus = switch (detail) {
                    case "availableProcessors" -> new AvailableProcessorsStatus(detailedStatus, facade);
                    case "freeJVMMemory" -> new FreeJvmMemoryStatus(detailedStatus, facade);
                    case "totalJVMMemory" -> new TotalJvmMemoryStatus(detailedStatus, facade);
                    case "jreVersion" -> new JreVersionStatus(detailedStatus, facade);
                    case "tempLocation" -> new TempLocationStatus(detailedStatus, facade);
                    default -> {
                        String errorMessage = "Invalid details option: " + detail;
                        logger.error("Bad request error: {}", errorMessage); // Logging statement for Bad Request error
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
                    }
                };
            }
        }
        return detailedStatus;
    }
}
