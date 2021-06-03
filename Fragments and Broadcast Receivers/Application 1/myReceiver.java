package com.example.cs478project3app1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class myReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Launch second activity
        if(intent.getAction().equals("uic.edu.cs478.f20.kaboom")) {
            Intent secondActivityIntent = new Intent();
            // Retrieve vacation name and put in launch intent
            secondActivityIntent.putExtra("Vacation Name", intent.getStringExtra("name"));
            // Retrieve vacation website and put in launch intent
            secondActivityIntent.putExtra("Vacation Website", intent.getStringExtra("website"));
            secondActivityIntent.setClassName(intent.getStringExtra("Application 1"), intent.getStringExtra("Application 1") + ".LaunchWebsiteActivity");
            secondActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d("BBB", "Going inside");
            // Start activity 2 in application 1
            context.startActivity(secondActivityIntent);
            //Toast.makeText(context, "I made it", Toast.LENGTH_LONG).show();
        }
    }
}
