package controllers;

import models.AvailableRooms;
import models.User;
import org.jboss.logging.Logger;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;
import services.ControllerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@ApplicationScoped
@Transactional
public class IndexController {

    @Inject
    @ConfigurationValue("some.string.property")
    private String stringProperty;

    @Inject
    private ControllerService controllerService;

    private static final Logger LOG = Logger.getLogger(IndexController.class.getName());

    @GET
    @Path("str")
    @Produces("plain/text")
    public String getStr() {
        return stringProperty;
    }

    @GET
    @Path("allavailablerooms")
    @Produces("application/json")
    public AvailableRooms getAllAvailableRooms() {
        return controllerService.getAvailableRoomTimes();
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findById(@PathParam("id") long id) {
        return controllerService.getUser(id);
    }

    @GET
    @Path("/user/{id}/token")
    @Produces(MediaType.APPLICATION_JSON)
    public String getToken(@PathParam("id") long id) {
        return controllerService.getToken(id);
    }



    //TODO remember to verify token before every request except login/logout
    //TODO get available times
    //TODO get available offices today
    //TODO login
    //TODO logout
    //TODO book room
    //TODO mark room as available

}
