package com.example.user.minor.Beacon;


import java.util.ArrayList;

public interface BeaconListener {
    void onDataReceived(ArrayList<AFMBeacon> afmBeacon);
}
