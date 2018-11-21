package services;

import controllers.IndexController;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Request {

    static final Logger LOG = Logger.getLogger(Request.class.getName());

    static HttpURLConnection initGetRequest(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con;
    }

    static HttpURLConnection initPostRequest(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        return con;
    }

    static void logRequestCode(HttpURLConnection con) throws IOException {
        LOG.info("Sending '" + con.getRequestMethod() + "' to URL " + con.getURL());
        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            LOG.info("Response code = " + responseCode + ". ResponseMessage = " + con.getResponseMessage());
        } else {
            LOG.error("Response code = " + responseCode + ". ResponseMessage = " + con.getResponseMessage());
        }
    }

    static String readResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}
