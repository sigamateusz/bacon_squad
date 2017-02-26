package com.example.root.bacon_squad_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final UUID ESTIMOTE_PROXIMITY_UUID = UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);
    private BeaconManager beaconManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setForegroundScanPeriod(3000, 0);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override public void onBeaconsDiscovered(Region region, final List <Beacon> beacons) {

                TextView t = (TextView) findViewById(R.id.link_to_page);
                t.setMovementMethod(LinkMovementMethod.getInstance());

                View link = findViewById(R.id.link_to_page);
                link.setVisibility(View.INVISIBLE);

                View hint = findViewById(R.id.hint);
                hint.setVisibility(View.INVISIBLE);



                boolean found = false;
//                for (Beacon beacon: beacons){
//                    if(String.valueOf(beacon.getMajor()).equals("57784")){
//                        TextView textViewToChange = (TextView) findViewById(R.id.text);
//                        textViewToChange.setText(
//                                "Znalazłem BEACONA!! :)");
//
//                        TextView signalValue = (TextView) findViewById(R.id.signal);
//                        signalValue.setText(
//                                "Siganl: " + String.valueOf(beacon.getRssi()));
//
//                        if (beacon.getRssi() > -90){
//                            TextView distance = (TextView) findViewById(R.id.distance);
//                            distance.setText(
//                                    "HOT!!!");
//                        }
//                        else if (beacon.getRssi() > -95){
//                            TextView distance = (TextView) findViewById(R.id.distance);
//                            distance.setText(
//                                "WARM!!!");
//                        }
//                        else{
//                            TextView distance = (TextView) findViewById(R.id.distance);
//                            distance.setText(
//                                    "COLD...");
//                        }
//
//                        found = true;
//
//                    }
//
//                    if(!found){
//                        TextView textViewToChange = (TextView) findViewById(R.id.text);
//                        textViewToChange.setText(
//                                "Szukam...");
//
//                        TextView signalValue = (TextView) findViewById(R.id.signal);
//                        signalValue.setText(
//                                "No signal");
//
//                        TextView distance = (TextView) findViewById(R.id.distance);
//                        distance.setText(
//                                "COLD...");
//
//                    }
//                    Log.i("Beacon", String.valueOf(beacon.getMajor()));
//                }



                for (Beacon beacon: beacons){
                    if(String.valueOf(beacon.getMajor()).equals("57784")){

                        if (beacon.getRssi() > -90){
                            ImageView image = (ImageView) findViewById(R.id.imageView);
                            image.setImageResource(R.drawable.hot);
//
//                            TextView t2 = (TextView) findViewById(R.id.text2);
//                            t2.setMovementMethod(LinkMovementMethod.getInstance());
                            link.setVisibility(View.VISIBLE);
                            hint.setVisibility(View.VISIBLE);
                        }
                        else if (beacon.getRssi() > -95){
                            ImageView image = (ImageView) findViewById(R.id.imageView);
                            image.setImageResource(R.drawable.warm);
                            link.setVisibility(View.INVISIBLE);
                            hint.setVisibility(View.INVISIBLE);
                        }
                        else{
                            ImageView image = (ImageView) findViewById(R.id.imageView);
                            image.setImageResource(R.drawable.cold);
                            link.setVisibility(View.INVISIBLE);
                            hint.setVisibility(View.INVISIBLE);
                        }

                        found = true;

                    }

                    if(!found){
//                        TextView textViewToChange = (TextView) findViewById(R.id.text);
//                        textViewToChange.setText(
//                                "Szukam...");
//
//                        ImageView image = (ImageView) findViewById(R.id.imageView);
//                        image.setImageResource(R.drawable.cold);

                        ImageView image = (ImageView) findViewById(R.id.imageView);
                        image.setImageResource(R.drawable.cold);
                        link.setVisibility(View.INVISIBLE);
                        hint.setVisibility(View.INVISIBLE);

                    }
                    Log.i("Beacon", String.valueOf(beacon.getMajor()));
                }



            }

        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {

                // Beacons ranging.
                beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);

                // Nearable discovery.
                beaconManager.startNearableDiscovery();

                // Eddystone scanning.
                beaconManager.startEddystoneScanning();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }



}