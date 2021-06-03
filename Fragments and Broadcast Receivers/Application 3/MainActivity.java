package com.example.cs478project3app3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity
        implements VacationNameFragment.ListSelectionListener {

    public static String[] mVacationNameArray;
    public static ArrayList<Integer> mVacationImageArray;
    public static ArrayList<String> mVacationWebArray;
    public static ArrayList<String> mVacationNames;

    private final VacationImageFragment mVacationImageFragment = new VacationImageFragment();

    private FrameLayout mVacationNameFrameLayout, mVacationImageFrameLayout;

    FragmentManager mFragmentManager;

    private static final String TAG = "MainActivity";

    public int selectedIndex;
    // Immediately after orientation changes, but before activity is started again
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SelectedIndex", selectedIndex);
        Log.d("AAA", "Orientation Changed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ": entered onCreate()");

        super.onCreate(savedInstanceState);

        selectedIndex = -1;
        if(savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt("SelectedIndex");
        }
        // Get the string arrays with the vacation names
        mVacationNameArray = getResources().getStringArray(R.array.VacationNames);

        // Get the Drawable images into an ArrayList
        mVacationImageArray = new ArrayList<Integer>();
        mVacationImageArray.add(R.drawable.bali);
        mVacationImageArray.add(R.drawable.borabora);
        mVacationImageArray.add(R.drawable.puertovallarta);
        mVacationImageArray.add(R.drawable.sydney);

        // Get the website Strings into an ArrayList
        mVacationWebArray = new ArrayList<String>();
        mVacationWebArray.add("https://www.bali.com/");
        mVacationWebArray.add("https://www.borabora.com/");
        mVacationWebArray.add("https://visitpuertovallarta.com/");
        mVacationWebArray.add("https://www.sydney.com/");

        // Get the name of Strings of locations into an ArrayList
        mVacationNames = new ArrayList<String>();
        mVacationNames.add("Bali");
        mVacationNames.add("Bora Bora");
        mVacationNames.add("Puerto Vallarta");
        mVacationNames.add("Sydney");


        setContentView(R.layout.activity_main);

        // Get references to the VacationNameFragment and VacationImageFragment
        mVacationNameFrameLayout = (FrameLayout) findViewById(R.id.vacation_name_fragment_container);
        mVacationImageFrameLayout = (FrameLayout) findViewById(R.id.vacation_image_fragment_container);

        // Get a reference to the SupportFragmentManager instead of original FragmentManager
        mFragmentManager = getSupportFragmentManager();

        // Start a new FragmentTransaction with backward compatibility
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // Add the VacationNameFragment to the layout
        fragmentTransaction.replace(
                R.id.vacation_name_fragment_container,
                new VacationNameFragment());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the backstack changes
        mFragmentManager.addOnBackStackChangedListener(
                new androidx.fragment.app.FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

        Toolbar myToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);

        getSupportActionBar().setIcon(getDrawable(R.drawable.my_icon));

    }

    private void setLayout() {
        // Determine whether the VacationImageFragment has been added
        if(!mVacationImageFragment.isAdded()) {

            // Make the VacationNameFragment occupy the entire layout
            mVacationNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            mVacationImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        }
        else {

            // Change the weights of the fragments if the device is in Portrait mode
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                // Make the VacationNameLayout disappear
                mVacationNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));

                // Make the VacationImageLayout take up the whole screen
                mVacationImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
            }

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                // Make the VacationNameLayout disappear
                mVacationNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));

                // Make the VacationImageLayout take up the whole screen
                mVacationImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
            }

        }
    }

    // Implement Java interface ListSelectionListener defined in VacationNameFragment
    // Called by VacationNameFragment when the user selects an item in the VacationNameFragment
    @Override
    public void onListSelection(int index) {
        // If the VacationImageFragment has not been added, add it now
        if(!mVacationImageFragment.isAdded()) {

            // Start a new FragmentTransaction
            androidx.fragment.app.FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            // Add the VacationImageFragment to the layout
            fragmentTransaction.add(R.id.vacation_image_fragment_container, mVacationImageFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if(mVacationImageFragment.getShownIndex() != index) {

            // Tell the VacationImageFragment to show the image at the position index
            mVacationImageFragment.showImageAtIndex(index);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.actionOne:
                Intent i = new Intent("uic.edu.cs478.f20.kaboom");
                // Add the applications to be launched
                i.putExtra("Application 2", "com.example.cs478project3app2");
                i.putExtra("Application 1", "com.example.cs478project3app1");

                if(!mVacationImageFragment.isAdded() || mVacationImageFragment.getShownIndex() == -1) {
                    i.putExtra("name", "None selected");
                    Toast.makeText(this, "Not selected", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Adding the website of the vacation spot
                    i.putExtra("website", mVacationWebArray.get(mVacationImageFragment.getShownIndex()));
                    // Adding the name of the vacation spot
                    i.putExtra("name", mVacationNames.get(mVacationImageFragment.getShownIndex()));
                    // Send orderedBroadcast
                    sendOrderedBroadcast(i, "uic.edu.cs478.f20.kaboom");
                }
                return true;

            case R.id.actionTwo:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}