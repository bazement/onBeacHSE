package com.example.user.minor.Beacon;

public interface BeaconListener {
    void onDataReceived(AFMBeacon afmBeacon, boolean fresh);
}
