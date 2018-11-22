package services;

import models.User;
import utils.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LoginRequests extends Request{
    private static final String BASE_URL = "http://localhost:9000/";
    private static final String ALL = "all";
    private static final String GET_USER = "user";
    private static final String LOG_IN = "login";
    private static final String LOG_OUT = "logout";
    private static final String VERIFY = "verify";
    private static final String CREATE_USER = "user/createuser";
    private static final Logger LOG = Logger.getLogger(LoginRequests.class.getName());


    public static User getUser(long id) throws IOException {
        URL url = new URL(BASE_URL + GET_USER + "/" + id);
        HttpURLConnection con = initGetRequest(url);
        logRequestCode(con);
        String response = readResponse(con);

        return JSONParser.parseUser(response);
    }

    public static Boolean verify(String email, String token) throws IOException{
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("token", token);

        String response = sendRequest(BASE_URL + VERIFY, map, "POST");

        return response != null ? response.equals("true") : null;
    }

    public static String login(String email, String pwd) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("pwd", pwd);
        String response = sendRequest(BASE_URL + LOG_IN, map, "POST");

        return response != null ? JSONParser.parseToken(response) : null;
    }

    public static boolean logout(String email, String token) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("token", token);
        String response = sendRequest(BASE_URL + LOG_OUT, map, "POST");

        return response != null && response.equals("true");
    }

    public static User createUser(String name, String lName, String pwd, String email) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("fName", name);
        map.put("lName", lName);
        map.put("pwd", pwd);
        map.put("email", email);

        String response = sendRequest(BASE_URL + CREATE_USER, map, "POST");

        return response != null ? JSONParser.parseUser(response) : null;
    }

    public static String sendRequest(String urlAsString, Map<String, String> headers, String method) throws IOException {
        URL url = new URL(urlAsString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);

        // Set all headers
        headers.keySet().forEach((key) -> con.setRequestProperty(key, headers.get(key)));

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // Success
            return readResponse(con);
        }
        else { // Fail
            LOG.warning(method + " request to " + urlAsString + " failed with response code " + responseCode);
        }

        return null;
    }
}
