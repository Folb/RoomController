package services;


import models.AvailableRooms;
import models.Booking;
import models.Room;
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
        return user.getAuthToken();
    }

    public boolean getVerificationVal(String email, String token){
        try {
            return LoginRequests.verify(email, token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String loginUser(String email, String pass){
        try {
            return LoginRequests.login(email, pass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean logoutUser(String email, String token){
        try {
            return LoginRequests.logout(email, token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User createUser(String name, String lName, String email, String pwd){
        try {
            return LoginRequests.createUser(name, lName, email, pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
