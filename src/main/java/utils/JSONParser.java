package utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.Booking;
import models.Room;
import models.User;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
        JsonObject jo = json.getAsJsonObject();
        return LocalDateTime.of(jo.get("year").getAsInt(),
                jo.get("monthValue").getAsInt(),
                jo.get("dayOfMonth").getAsInt(),
                jo.get("hour").getAsInt(),
                jo.get("minute").getAsInt(),
                jo.get("second").getAsInt(),
                jo.get("nano").getAsInt());
    }).create();

    public static User parseUser(String json) {
        return gson.fromJson(json, User.class);
    }

    public static List<User> parseUserList(String jsonArray) {
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        return gson.fromJson(jsonArray, listType);
    }

    public static Room parseRoom(String json) {
        return gson.fromJson(json, Room.class);
    }

    public static List<Room> parseRoomList(String jsonArray) {
        Type listType = new TypeToken<ArrayList<Room>>(){}.getType();
        return gson.fromJson(jsonArray, listType);
    }

    public static Booking parseBooking(String json) {
        return gson.fromJson(json, Booking.class);
    }

    public static List<Booking> parseBookingList(String jsonArray) {
        Type listType = new TypeToken<ArrayList<Booking>>(){}.getType();
        return gson.fromJson(jsonArray, listType);
    }

    public static Boolean parseVerification(String json){
        return gson.fromJson(json, Boolean.class);
    }

    public static String parseToken(String json){
        return gson.fromJson(json, String.class);
    }
}
