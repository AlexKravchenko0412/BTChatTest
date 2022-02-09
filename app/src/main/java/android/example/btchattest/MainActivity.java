package android.example.btchattest;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter btAdapter;
    private BluetoothDevice btDevice;
    private BluetoothSocket btSocket;


    private DeviceListAdapter adapter;
    private ListView listViewDevices;
    private ArrayList<BluetoothDevice> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewDevices = findViewById(R.id.listViewDevices);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        enableBT();
        discoverDevice();
        devices = new ArrayList<>();
        btDevice = getIntent().getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        adapter = new DeviceListAdapter(this, R.layout.device_list, devices);
        devices.add(btDevice);
        listViewDevices.setAdapter(adapter);
    }


    @SuppressLint("MissingPermission")
    private void enableBT() {
        if(btAdapter == null) {
            toast("No BT adapter");
        }
        if(!btAdapter.isEnabled()) {
            Intent enableIntentBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableIntentBT);
        }
    }


    @SuppressLint("MissingPermission")
    public void discoverDevice() {
        if(btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
            checkBTPermissions();
            btAdapter.startDiscovery();
        }
        if(!btAdapter.isDiscovering()) {
            checkBTPermissions();
            btAdapter.startDiscovery();
        }

    }

    @SuppressLint("NewApi")
    private void checkBTPermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
            }
        }else{
            toast("Do not need to check permissions");
        }
    }


    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}