package utils;

import com.google.gson.Gson;
import models.Booking;
import models.Room;
import models.User;

public class JSONParser {

    public static User parseUser(String json) {
        return new Gson().fromJson(json, User.class);
    }

    public static Room parseRoom(String json) {
        return new Gson().fromJson(json, Room.class);
    }

    public static Booking parseBooking(String json) {
        return new Gson().fromJson(json, Booking.class);
    }

}
