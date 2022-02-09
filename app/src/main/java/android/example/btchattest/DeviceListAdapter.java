package android.example.btchattest;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.widget.ArrayAdapter;



import java.util.ArrayList;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private ArrayList<BluetoothDevice> devices;
    private int resourceId;

    public DeviceListAdapter(Context context, int resource, ArrayList<BluetoothDevice> devices) {
        super(context, resource, devices);
        this.devices = devices;
        resourceId = resource;
    }
}
