package com.example.root.bacon_squad_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
        setContentView(R.layout.activity_main);
        Button next = (Button) findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}
