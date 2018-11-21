package controllers;

import models.AvailableRooms;
import models.User;
import org.jboss.logging.Logger;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;
import services.ControllerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
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

    @POST
    @Path("/verify")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean getVerificationVal(@HeaderParam("email") String email, @HeaderParam("token") String token) {
        return controllerService.getVerificationVal(email, token);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@HeaderParam("email") String email, @HeaderParam("pwd") String pwd) {
        return controllerService.loginUser(email, pwd);
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean logout(@HeaderParam("email") String email, @HeaderParam("token") String token) {
        return controllerService.logoutUser(email, token);
    }

    @POST
    @Path("/createuser")
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(@HeaderParam("fName") String name, @HeaderParam("lName") String lName,
                           @HeaderParam("pwd") String pwd, @HeaderParam("email") String email) {

        return controllerService.createUser(name, lName, email, pwd);
    }


    //TODO remember to verify token before every request except login/logout
    //TODO get available times
    //TODO get available offices today
    //TODO login
    //TODO logout
    //TODO book room
    //TODO mark room as available

}
