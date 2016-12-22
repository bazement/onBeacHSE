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

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
//    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
//
//        adapter = new ArrayAdapter<AFMBeacon>(this, android.R.layout.simple_list_item_1, mDevices);
//        listView.setAdapter(adapter);

        BeaconInterface beaconInterface = BeaconInterface.getInstance();
        beaconInterface.scanStart(this, new BeaconListener() {
            @Override
            public void onDataReceived(AFMBeacon beacon, boolean fresh) {
                if (fresh) {
                    RestClientGet restClientGet = new RestClientGet();
                    restClientGet.execute(new RequestInfo(beacon.getMajor(), beacon.getMinor()));
                    try {
                        textView.setText(restClientGet.get().getInfo());

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    textView2.setText(String.valueOf(beacon.getDistance()));
                } else {
                    textView2.setText(String.valueOf(beacon.getDistance()));
                }
            }
        });
    }
}