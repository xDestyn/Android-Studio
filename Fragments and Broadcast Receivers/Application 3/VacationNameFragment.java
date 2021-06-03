package com.example.cs478project3app3;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class VacationNameFragment extends ListFragment {
    private static final String TAG = "VacationNameFragment";
    private ListSelectionListener mListener = null;

    // Callback interface that allows this Fragment to notify the MainActivity when
    // user clicks on List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);

        // Inform the Main Activity that the item in position pos has been selected
        mListener.onListSelection(pos);
        ((MainActivity)getActivity()).selectedIndex = pos;
    }

    @Override
    public void onAttach(Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ": entered onAttach()");
        super.onAttach(activity);

        try {
            // Set the ListSelectionListener for communicating with the MainActivity
            // Try casting the containing activity to ListSelectionListener
            mListener  = (ListSelectionListener) activity;
        } catch(ClassCastException e) {
            // Cast failed: This is not going to work because activity may not
            // have been implemented on ListSelection() method
            throw new ClassCastException(activity.toString() +
                    " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);
    }

    // UB:  Notice that the superclass's method does an OK job of inflating the
    //      container layout.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedState);

        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.vacation_name_items, MainActivity.mVacationNameArray));

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        int selectedIndex = ((MainActivity)getActivity()).selectedIndex;
        if(selectedIndex != -1) {
            getListView().setItemChecked(selectedIndex, true);
        }
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }

}
