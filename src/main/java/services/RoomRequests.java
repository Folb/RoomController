package services;

import models.Booking;
import models.Room;
import utils.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RoomRequests extends Request {

    private static final String BASE_URL = "http://localhost:8081/";
    private static final String ALL = "all";

    public static List<Room> getAllBookings() throws IOException {
        URL url = new URL(BASE_URL + ALL);
        HttpURLConnection con = initGetRequest(url);
        logRequestCode(con);
        String response = readResponse(con);
        con.disconnect();

        return JSONParser.parseRoomList(response);
    }
}
