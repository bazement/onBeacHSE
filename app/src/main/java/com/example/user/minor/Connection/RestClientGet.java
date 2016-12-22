package com.example.user.minor.Connection;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class RestClientGet extends AsyncTask<RequestInfo, Void, ResponseInfo>{
    final String TAG = "RestClientGet";
    final String URL_IP = "192.168.0.58";
    final String URL_BASE = "http://" +
            URL_IP + ":8000/beacon/?";


    protected ResponseInfo doInBackground(RequestInfo... params) {
        RequestInfo requestInfo = params[0];
        JSONObject response = new JSONObject();
        try {
            URL url = new URL(URL_BASE
                    + "major=" + requestInfo.getMajor() + "&"
                    + "minor=" + requestInfo.getMinor());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            String result = "";
            Log.d(TAG, "Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                result += output;
            }
            response = new JSONObject(result);
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseInfo(response);
    }
}