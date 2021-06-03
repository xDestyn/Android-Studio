package com.example.cs478proj1_oflore9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Fields to be bound to GUI widgets
    protected Button firstActivityButton;
    protected Button secondActivityButton;
    protected TextView phoneNum;

    // Result Code
    protected final int resultCode = 1;

    // Returned phone number by the activity
    protected String returnedNumber;

    // Good or bad number
    protected boolean goodNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind the interface elements to the corresponding fields
        firstActivityButton = (Button) findViewById(R.id.firstActivity);
        secondActivityButton = (Button) findViewById(R.id.secondActivity);
        phoneNum = (TextView) findViewById(R.id.returnedNumTV);

        // Second button is turned off in the beginning
        secondActivityButton.setEnabled(false);

        // Set up listeners for the buttons
        firstActivityButton.setOnClickListener(firstActivityButtonListener);
        secondActivityButton.setOnClickListener(secondActivityButtonListener);

    }

    // Listener for the first activity button
    public View.OnClickListener firstActivityButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switchToSecondActivity();
        }
    };

    // Switches from first activity to second activity (where user is asked to enter a valid phone number)
    private void switchToSecondActivity() {
        Intent i = new Intent(MainActivity.this, ActivityTwo.class);
        startActivityForResult(i, resultCode);
    }
    // Listener for second activity button
    public View.OnClickListener secondActivityButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(goodNum) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + returnedNumber));
                startActivity(dialIntent);
            }
            else {
                // Displaying Toast message
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, returnedNumber + " is an incorrect phone number", Toast.LENGTH_SHORT);;
                toast.show();
            }
        }
    };
    // Have to have definition in place, so when second activity sets a result, MainActivity can retrieve it
    protected void onActivityResult(int code, int result_code, Intent i) {
        super.onActivityResult(code, result_code, i);
        phoneNum.setText(i.getStringExtra("result"));
        secondActivityButton.setEnabled(true);
        if(resultCode == 1) {
            if(result_code == RESULT_OK) {

                goodNum = true;
            }
            if(result_code == RESULT_CANCELED) {
                goodNum = false;
            }

            returnedNumber = i.getStringExtra("result");
        }
    }

}