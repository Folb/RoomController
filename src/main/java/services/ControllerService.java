package services;


import models.Booking;

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

}
