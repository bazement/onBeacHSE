package com.example.user.minor.Connection;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestInfo {
    private int major;
    private int minor;

    public int getMinor() {
        return minor;
    }

    public int getMajor() {

        return major;
    }

    public RequestInfo(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }
    public JSONObject serialize() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("major", major);
            jsonObject.put("minor", minor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
