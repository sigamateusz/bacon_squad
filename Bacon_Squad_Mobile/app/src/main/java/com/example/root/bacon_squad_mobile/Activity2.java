package com.example.root.bacon_squad_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Activity2 extends AppCompatActivity {
    private static final UUID ESTIMOTE_PROXIMITY_UUID = UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);
    private BeaconManager beaconManager = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hot_or_cold);
        sendSomething();
        post();
        Button next = (Button) findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });


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
//                    Log.i("Beacon", String.valueOf(beacon.getMajor()));
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


    void sendSomething(){
        final TextView mTextView = (TextView) findViewById(R.id.request);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.170.20.177:8080/mateusz";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 20 characters of the response string.
                        Log.i("Respone: ", response);
                        String[] jakis = splitString(response);
//                        Log.i("Respone: ", jakis[0]);
//                        Log.i("Respone: ", jakis[1]);

                        int response_len = response.length();
                        mTextView.setText("Response is: "+ response.substring(0, response_len));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    String[] splitString(String text){
//        "1||1||Abdul-Jabbar||Karim||1996||1974"

        final String PLAYER = text;

        String[] data = PLAYER.split("\\|\\|");
        return data;

//            System.out.println(Arrays.toString(data));

    }

//    public void loginLayout(){
//        setContentView(R.layout.login_layout);
//    }


    void post() {
        final TextView mTextView = (TextView) findViewById(R.id.request);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.170.20.177:8080/mateusz";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
//                        Log.i("Respone: ", response);
                        String[] jakis = splitString(response);
//                        Log.i("Respone: ", jakis[0]);
//                        Log.i("Respone: ", jakis[1]);

                        int response_len = response.length();
                        mTextView.setText("Response is: "+ response.substring(0, response_len));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "blablabla");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
        };
        queue.add(postRequest);
    }

}





//package com.example.root.bacon_squad_mobile;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//
//public class Activity2 extends AppCompatActivity {
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login_layout);
//        Button next = (Button) findViewById(R.id.button2);
//        next.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//
//        });
//
//
//
//
//
//    }
//}