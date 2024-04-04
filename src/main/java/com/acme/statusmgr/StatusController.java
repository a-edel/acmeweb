package com.acme.statusmgr;

import com.acme.statusmgr.beans.*;
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
        return new ServerStatus(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * Process a request for detailed server status information
     *
     * @param name    optional param identifying the requester
     * @param details optional param with a list of server status details being requested
     * @return a ServerStatus object containing the info to be returned to the requester
     *      * @apiNote TODO since Spring picks apart the object returned with Reflection and doesn't care what the return-object's type is, we can change the type of object we return if necessary
     */
    @RequestMapping("/status/detailed")
    public Status getDetailedStatus(
            @RequestParam(value = "name", defaultValue = "Anonymous") String name,
            @RequestParam List<String> details) {


        Status detailedStatus = new ServerStatus(counter.incrementAndGet(), String.format(template, name), facade);

        if (details != null) {
            Logger logger = LoggerFactory.getLogger("StatusController");
            logger.info("Details were provided: " + Arrays.toString(details.toArray()));

            for(String detail : details) {
                switch (detail) {
                    case "availableProcessors":
                        detailedStatus = new AvailableProcessorsStatus(detailedStatus, facade);
                        break;
                    case "freeJVMMemory":
                        detailedStatus = new FreeJvmMemoryStatus(detailedStatus, facade);
                        break;
                    case "totalJVMMemory":
                        detailedStatus = new TotalJvmMemoryStatus(detailedStatus, facade);
                        break;
                    case "jreVersion":
                        detailedStatus = new JreVersionStatus(detailedStatus, facade);
                        break;
                    case "tempLocation":
                        detailedStatus = new TempLocationStatus(detailedStatus, facade);
                        break;
                    default:
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Invalid details option: " + detail
                        );
                }
            }
        }
        return detailedStatus;
    }
}
