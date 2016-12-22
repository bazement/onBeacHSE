package com.example.user.minor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.minor.Beacon.AFMBeacon;
import com.example.user.minor.Beacon.BeaconInterface;
import com.example.user.minor.Beacon.BeaconListener;
import com.example.user.minor.Connection.RequestInfo;
import com.example.user.minor.Connection.RestClientGet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);


        BeaconInterface beaconInterface = BeaconInterface.getInstance();
        beaconInterface.scanStart(this, new BeaconListener() {
            @Override
            public void onDataReceived(ArrayList<AFMBeacon> beacons) {
                List<String> beaconsInfo = new ArrayList<>();
                for (AFMBeacon beacon : beacons)
                    beaconsInfo.add(beacon.getInformation());
//                beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");beaconsInfo.add("Avokado");
//                beaconsInfo.add("Banana");
//                beaconsInfo.add("Cocoa");

                adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.beacon_layout, beaconsInfo);
                listView.setAdapter(adapter);
            }
        });
    }
}