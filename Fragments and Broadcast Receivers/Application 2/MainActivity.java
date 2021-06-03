package com.example.cs478project3app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int PERMISSION_CODE = 1;
    private Button mButton;
    private IntentFilter mFilter;
    private BroadcastReceiver mReceiver;
    //private static final String STRING_FILTER = "com.example.cs478project3app2";
    private static final String NEEDED_PERMISSION =
            "uic.edu.cs478.f20.kaboom" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.startButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionNeeded();
            }
        });
    }
    private void checkPermissionNeeded() {
        // Permission already granted
        if(ContextCompat.checkSelfPermission(this, NEEDED_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            // Application 2 registers its broadcast receiver and Application 3's Main Activity launched
            registerReceiver();
            Toast.makeText(this, "Application 2 permission granted", Toast.LENGTH_LONG).show();
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.cs478project3app3");
            startActivity(launchIntent);
        }

        // Request permission
        else {
            ActivityCompat.requestPermissions(this, new String[]{NEEDED_PERMISSION}, PERMISSION_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(requestCode == PERMISSION_CODE) {
            // Permission granted
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // // Application 2 registers its broadcast receiver and Application 3's Main Activity launched
                registerReceiver();
                Toast.makeText(this, "Application 2 permission granted", Toast.LENGTH_LONG).show();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.cs478project3app3");
                startActivity(launchIntent);
            }
//            else if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                Toast.makeText(this, "Application 2 permission denied", Toast.LENGTH_LONG).show();
//                finish();
//            }
            else {
                // Display toast message of permission denied
                Toast.makeText(this, "Application 2 permission was denied", Toast.LENGTH_LONG).show();
                // Terminates itself
                finish();
            }
        }

    }

    // Registers receiver
    private void registerReceiver() {
        mFilter = new IntentFilter(NEEDED_PERMISSION);
        mFilter.setPriority(20);
        mReceiver = new myReceiver();
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
        // Try/Catch so application won't crash
        try {
            unregisterReceiver(mReceiver);
        } catch(Exception e) {
          e.printStackTrace();
        }
    }
}