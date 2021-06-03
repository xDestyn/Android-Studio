package com.example.cs478project3app1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

public class LaunchWebsiteActivity extends AppCompatActivity {

    private String vacationName;
    private String vacationWebSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BBB", "Inside on create");

        final Intent receivedIntent = getIntent();
        vacationName = receivedIntent.getStringExtra("Vacation Name");
        vacationWebSite = receivedIntent.getStringExtra("Vacation Website");

        switch(vacationName) {
            case "Bali":
                displayWebsite(vacationWebSite);
                break;
            case "Bora Bora":
                displayWebsite(vacationWebSite);
                break;
            case "Puerto Vallarta":
                displayWebsite(vacationWebSite);
                break;
            case "Sydney":
                displayWebsite(vacationWebSite);
                break;
            default:
                break;
        }
    }
    private void displayWebsite(String web) {
        Uri uri = Uri.parse(web);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        // Check if there's a resource available to do the intent
        if(browserIntent.resolveActivity(getPackageManager()) != null) {
            // Start the activity (visit the webpage)
            startActivity(browserIntent);
        }
    }
}
