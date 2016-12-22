package com.example.user.minor.Beacon;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class BeaconInterface {
    final static int REQUEST_ENABLE_BT=1;
    ArrayList<AFMBeacon> mDevices;
    ArrayList<String> mDeviceAddress;                   //It allows faster access to already received beacons
    BeaconListener beaconListener;
    ArrayAdapter<AFMBeacon> adapter;

    private static BeaconInterface ourInstance = new BeaconInterface();

    public static BeaconInterface getInstance() {
        return ourInstance;
    }

    private BeaconInterface() {
        mDevices = new ArrayList<>();
        mDeviceAddress = new ArrayList<>();
    }
    public void scanStart(Activity activity, BeaconListener listener) {
        // If bluetooth is OFF ask from user to turn it ON
        // otherwise start scan
        beaconListener = listener;
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            activity = null;
            mBluetoothAdapter.startLeScan(scanCallback);

        } else {
            mBluetoothAdapter.startLeScan(scanCallback);
        }
    }

    private void newDataReceived(AFMBeacon afmBeacon) {
        if (mDeviceAddress.contains(afmBeacon.getAddress())) {                                      //Device already known?
            for (AFMBeacon iterator : mDevices) {                                                       //if yes than refresh his rssi
                if (iterator.getAddress().equals(afmBeacon.getAddress())) {
                    iterator.addDistance(afmBeacon.getReceivedDistance());
                    beaconListener.onDataReceived(iterator, false);
                    break;
                }
            }
        } else {                                                                                    //if no than add it to device list
            mDevices.add(afmBeacon);
            mDeviceAddress.add(afmBeacon.getAddress());
            beaconListener.onDataReceived(afmBeacon, true);
        }
        Log.d("DEVICE", mDevices.get(0).toString());
    }

    private BluetoothAdapter.LeScanCallback scanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            final int MAJOR_UPPER_INDEX = 25;
            final int BIT_MASK = 0xff;
            final int SHIFT_MASK = 0x100;
            final int MAJOR_LOWER_INDEX = 26;
            final int MINOR_UPPER_INDEX = 27;
            final int MINOR_LOWER_INDEX = 28;
            final int TXPOWER_INDEX = 29;

            final int major = (scanRecord[MAJOR_UPPER_INDEX] & BIT_MASK)
                    * SHIFT_MASK + (scanRecord[MAJOR_LOWER_INDEX] & BIT_MASK);
            final int minor = (scanRecord[MINOR_UPPER_INDEX] & BIT_MASK)
                    * SHIFT_MASK + (scanRecord[MINOR_LOWER_INDEX] & BIT_MASK);
            final int txPower = scanRecord[TXPOWER_INDEX];
            final double distance = Math.pow(10, (txPower - rssi)/20.0 );                             //Magic numbers from formula `rssi to distance`

            if (major != 0 && minor != 0)
                newDataReceived(new AFMBeacon(device.getName(), device.getAddress(), major, minor, distance));
        }
    };
}
