package com.example.cs478project3app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class myReceiver extends BroadcastReceiver {
    public String receivedString = "Inside Application 2: ";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("uic.edu.cs478.f20.kaboom")) {
            receivedString = receivedString + intent.getStringExtra("name");
            // Display the vacation name in toast message
            Toast.makeText(context, receivedString, Toast.LENGTH_LONG).show();
            receivedString = "Inside Application 2: ";
        }
    }
}
