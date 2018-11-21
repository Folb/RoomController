package services;


import models.Booking;
import models.User;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class ControllerService {

    public List<Booking> getAllBookings()  {
        try {
            return BookingRequests.getAllBookings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //TODO requests to services

    public User getUser(long id){
        try {
            return LoginRequests.getUser(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getToken(long id){
        User user = getUser(id);
        return null;
    }
}
