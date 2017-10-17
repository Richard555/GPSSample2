package com.example.richardchen.gpssample2;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

    Button btnShowLocation;

    // GPSTracker class
    GPSTracker gps = null;

    TextView GPS_TextView;
    TextView NETWROK_TextView;
    Location location;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        GPS_TextView = (TextView) findViewById(R.id.GPS_STATUS);
        NETWROK_TextView = (TextView) findViewById(R.id.NETWORK_STATUS);


        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                if (gps==null) {
                    gps = new GPSTracker(AndroidGPSTrackingActivity.this);
                }

                gps.getLocation();

                if (gps.isGPSEnabled)
                    GPS_TextView.setText("GPS is ON");
                else
                    GPS_TextView.setText("GPS is OFF");

                if (gps.isNetworkEnabled)
                    NETWROK_TextView.setText("Network positioning is ON");
                else
                    NETWROK_TextView.setText("Network positioning is OFF");

                // check if GPS enabled
                if(gps.canGetLocation()){

                    location=gps.location;
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Your time\n " + location.getTime() , Toast.LENGTH_LONG).show();


                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }

}