package services;

import models.Booking;
import utils.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BookingRequests extends Request {

    //TODO change port number
    private static final String BASE_URL = "http://localhost:8080/";
    private static final String ALL = "all";
    private static final String IS_BOOKED = "isBooked/";
    private static final String BOOK_ROOM = "bookRoom";
    private static final String USER = "user/";
    private static final String BOOKINGS = "/bookings";

    public static List<Booking> getAllBookings() throws IOException {
        URL url = new URL(BASE_URL + ALL);
        HttpURLConnection con = initGetRequest(url);
        logRequestCode(con);
        String response = readResponse(con);
        con.disconnect();

        return JSONParser.parseBookingList(response);
    }

    public static Booking bookRoom() throws IOException {
        URL url = new URL(BASE_URL + USER);
        HttpURLConnection con = initPostRequest(url);
        con.
    }

}
