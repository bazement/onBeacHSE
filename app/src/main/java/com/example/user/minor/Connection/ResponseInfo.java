package com.example.user.minor.Connection;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseInfo {
    public String getInfo() {
        return info;
    }

    private String info;

    ResponseInfo(JSONObject jsonObject) {
        try {
            info = jsonObject.getString("info");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
