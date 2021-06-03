package com.example.cs478proj1_oflore9;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
//import android.telephony.PhoneNumberUtils;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;

import java.util.Locale;

public class ActivityTwo extends AppCompatActivity {

    // Field to be bound to GUI widgets
    protected EditText phoneNumTF;

    // Stores the converted number in a string
    protected String convNumber;

    // Stores the validity of the number
    protected boolean goodNum;

    // Our return result
    protected Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // Bind the EditText to corresponding field
        phoneNumTF = (EditText) findViewById(R.id.phoneNumberTF);
        // Set up key listener for when enter key is pressed
        phoneNumTF.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER) {

                    onBackPressed();
                }
                return false;
            }
        });
    }

//    protected void onPause() {
//        super.onPause();
//        setResult(RESULT_OK);
//    }

    public void onBackPressed() {


        //convNumber = PhoneNumberUtils.formatNumberToRFC3966(phoneNumTF.getText().toString(), Locale.getDefault().toString());
        convNumber = phoneNumTF.getText().toString();

        // Regular expression provided in Piazza
        String regExpression = "^\\+?[1]? ?\\(?[0-9]{3}\\)? ?-?[0-9]{3} ?-?[0-9]{4}";
        //String noSpaces = "";

        // Stripping the phone number of spaces and parenthesis
//        for(int i = 0; i < convNumber.length(); i++) {
//            if(convNumber.charAt(i) == ' ' || convNumber.charAt(i) == '(' || convNumber.charAt(i) == ')')
//                noSpaces += "";
//            else
//                noSpaces +=convNumber.charAt(i);
//        }

        //Log.d("ActivityTwo", convNumber);
        // If it's a valid phone number
//        if(PhoneNumberUtils.isGlobalPhoneNumber(noSpaces) && noSpaces.length() == 10)
//            goodNum = true;
//        else
//            goodNum = false;
//
//        resultIntent = new Intent();
//        resultIntent.putExtra("result", noSpaces);

        // Checks to see if number is valid using regular expression
        if(convNumber.matches(regExpression))
            goodNum = true;
        else
            goodNum = false;

        resultIntent = new Intent();
        resultIntent.putExtra("result", convNumber);

        // Setting the result codes back depending on the given phone number input
        if (goodNum) {
            setResult(RESULT_OK, resultIntent);
        }
        else {
            setResult(RESULT_CANCELED, resultIntent);
        }

        finish();
        super.onBackPressed();
    }

}
