package com.example.root.bacon_squad_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.UUID;


public class Questions extends AppCompatActivity {
    private static final UUID ESTIMOTE_PROXIMITY_UUID = UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);
    private BeaconManager beaconManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        final Button button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBeaconId();
                Intent myIntent = new Intent(getApplicationContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
                // Perform action on click
            }
        });


    }



//    @Override
//    public void onClick(View v) {
//        Log.d("MR.bool", "Button1 was clicked ");
//    }

    public void setBeaconId(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.170.20.177:8080/beacon";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 20 characters of the response string.
                        Log.i("Respone: ", response);
                        Activity2.beacon_id = response;
                        String[] jakis = Activity2.splitString(response);
//                        Log.i("Respone: ", jakis[0]);
//                        Log.i("Respone: ", jakis[1]);

                        int response_len = response.length();
//                        mTextView.setText("Response is: "+ response.substring(0, response_len));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }




}

