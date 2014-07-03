package com.andrewq.planets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.analytics.tracking.android.EasyTracker;

public class FragmentE extends Fragment {

    //Variables
    Button button;
    Button button2;
    ImageView imageView;

    public FragmentE() {
        // Required empty public constructor
    }

    //Called when the view is created
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Find the buttons
        button = (Button) getView().findViewById(R.id.button_4);
        button2 = (Button) getView().findViewById(R.id.mars_satellite);
        imageView = (ImageView) getView().findViewById(R.id.mars);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url = "http://space-facts.com/mars/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        //Allow the user to be able to select which moon they want to view for mars
        button2.setOnClickListener(new View.OnClickListener() {
            //Handle the button being pressed
            @Override
            public void onClick(View v) {
                //Make a new alert dialog
                new AlertDialog.Builder(getActivity())
                        //Set the title
                        .setTitle("Select Moon")
                                //Set it's items to an array
                        .setItems(R.array.mars_moons, new DialogInterface.OnClickListener() {
                            //Handle what happens when each item is pressed
                            public void onClick(DialogInterface dialog, int which) {
                                //If the user pressed the first item
                                if (which == 0) {
                                    //Open the first moon activity
                                    Intent phobos = new Intent(getActivity(), Phobos.class);
                                    startActivity(phobos);
                                }
                                //Otherwise
                                else {
                                    //In this case, open the other moon activity
                                    Intent deimos = new Intent(getActivity(), Deimos.class);
                                    startActivity(deimos);
                                }
                            }
                        })
                                //Create a cancel button
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            //Handle when it's clicked. Here we don't need it to do anything
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Do nothing!
                            }
                        })

                                //Show the dialog after an item is pressed
                        .show();
            }
        });
    }

    //Called when the view is first created; Called before onViewCreated()
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_e, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(getActivity()).activityStart(getActivity());  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(getActivity()).activityStop(getActivity());  // Add this method.
    }
}