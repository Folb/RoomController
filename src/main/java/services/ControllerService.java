package services;


import models.AvailableRooms;
import models.Booking;
import models.Room;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Room> getAllRooms() {
        try {
            return RoomRequests.getAllBookings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AvailableRooms getAvailableRoomTimes() {
        AvailableRooms ar = new AvailableRooms();
        List<Room> rooms = getAllRooms();
        List<Booking> bookings = getAllBookings();
        ar.generateAllAvailableRooms(rooms, bookings);
        return ar;
    }

    //TODO requests to services

}
