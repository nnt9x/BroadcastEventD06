package com.bkacad.nnt.broadcasteventd06;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    // View
    private ConstraintLayout rootView;
    private TextView tvResult;

    // Receiver
    private IntentFilter intentFilter;
    private MyReceiver myReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(R.id.rootView);
        tvResult = findViewById(R.id.tvResult);

        // Định nghĩa Intentfilter
        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("TEST MY ACTION");

        myReceiver = new MyReceiver() {
            @Override
            protected void eventBluetoothON() {
                rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
                Toast.makeText(MainActivity.this, "Bluetooth ON", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void eventBluetoothOFF() {
                rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                Toast.makeText(MainActivity.this, "Bluetooth OFF", Toast.LENGTH_SHORT).show();

            }

            @Override
            protected void eventPowerConnected() {
                tvResult.setText("Power connected");
            }

            @Override
            protected void eventPowerDisconnected() {
                tvResult.setText("Power disconnected");
            }

            @Override
            protected void myEvent() {
                tvResult.setText("My EVENT");
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    public void sendMyEvent(View view) {
        sendBroadcast(new Intent("TEST MY ACTION"));
    }
}