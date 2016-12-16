package com.example.user.minor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.minor.Beacon.AFMBeacon;
import com.example.user.minor.Beacon.BeaconInterface;
import com.example.user.minor.Beacon.BeaconListener;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        BeaconInterface beaconInterface = BeaconInterface.getInstance();
        beaconInterface.scanStart(this, new BeaconListener() {
            @Override
            public void onDataReceived(AFMBeacon beacon, boolean fresh) {
                if (fresh) {
                    String string = "New Beacon: " + beacon.getAddress() + "\nDistance: " + beacon.getDistance();
                    textView.setText(string);
                } else {
                    String string = "Old Beacon: " + beacon.getAddress() + "\nDistance: " + beacon.getDistance();
                    textView.setText(string);
                }
            }
        });
        AFMBeacon
    }


}