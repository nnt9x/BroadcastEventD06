package com.bkacad.nnt.broadcasteventd06;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class MyReceiver extends BroadcastReceiver {

    protected abstract void eventBluetoothON();

    protected abstract void eventBluetoothOFF();

    protected abstract void eventPowerConnected();

    protected abstract void eventPowerDisconnected();

    protected abstract void myEvent();

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();

        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    eventBluetoothOFF();
                    break;
                case BluetoothAdapter.STATE_ON:
                    eventBluetoothON();
                    break;
            }
        } else if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
            eventPowerConnected();

        } else if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            eventPowerDisconnected();
        } else if (action.equals("TEST MY ACTION")) {
            myEvent();
        }
    }
}
