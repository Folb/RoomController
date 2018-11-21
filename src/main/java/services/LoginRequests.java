package services;

import models.User;
import utils.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginRequests extends Request{
    private static final String BASE_URL = "http://localhost:9000/";
    private static final String ALL = "all";
    private static final String GET_USER = "user";
    private static final String LOG_IN = "login";
    private static final String LOG_OUT = "logout";
    private static final String VERIFY = "verify";
    private static final String CREATE_USER = "createuser";

    public static User getUser(long id) throws IOException {
        URL url = new URL(BASE_URL + GET_USER + "/" + id);
        HttpURLConnection con = initGetRequest(url);
        logRequestCode(con);
        String response = readResponse(con);

        return JSONParser.parseUser(response);
    }

    public static String getToken() throws IOException{
        URL url = new URL(BASE_URL + LOG_IN);
        HttpURLConnection con = initGetRequest(url);
        logRequestCode(con);
        String response = readResponse(con);

        return JSONParser.parseToken(response);
    }
}
