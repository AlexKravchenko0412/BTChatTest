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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter btAdapter;
    private BluetoothDevice btDevice;
    private BluetoothSocket btSocket;
    private ListView listViewDevices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewDevices = findViewById(R.id.listViewDevices);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        enableBT();

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

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}