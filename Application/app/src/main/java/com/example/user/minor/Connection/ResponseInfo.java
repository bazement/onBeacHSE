package com.example.user.minor.Connection;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseInfo {
    private String info;
    private String fullInfo;

    public String getInfo() {
        return info;
    }
    public String getFullInfo() {
        return fullInfo;
    }

    ResponseInfo(JSONObject jsonObject) {
        try {
            info = jsonObject.getString("info");
            fullInfo = jsonObject.getString("full_info");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
