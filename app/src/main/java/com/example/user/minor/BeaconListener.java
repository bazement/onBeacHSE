package com.example.user.minor;

public interface BeaconListener {
    void onDataReceived(AFMBeacon afmBeacon, boolean fresh);
}
