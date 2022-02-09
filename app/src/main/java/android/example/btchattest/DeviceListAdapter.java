package android.example.btchattest;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private ArrayList<BluetoothDevice> devices;
    private int resourceId;
    private TextView textViewName;
    private TextView textViewAddress;
    private LayoutInflater inflater;

    public DeviceListAdapter(Context context, int resource, ArrayList<BluetoothDevice> devices) {
        super(context, resource, devices);
        this.devices = devices;
        resourceId = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(resourceId, null);

        BluetoothDevice device = devices.get(position);

        if(device != null) {
            textViewName = convertView.findViewById(R.id.textViewName);
            textViewAddress = convertView.findViewById(R.id.textViewAddress);
        }

        if(textViewName != null) {
            textViewName.setText(device.getName());
        }
        if (textViewAddress != null) {
            textViewAddress.setText(device.getAddress());
        }

        return convertView;
    }
}
