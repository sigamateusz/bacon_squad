package com.example.root.bacon_squad_mobile.estimote;

import com.estimote.sdk.cloud.model.Color;

public class EstimoteCloudBeaconDetails {

    private String beaconName;
    private Color beaconColor;

    public EstimoteCloudBeaconDetails(String beaconName, Color beaconColor) {
        this.beaconName = beaconName;
        this.beaconColor = beaconColor;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public Color getBeaconColor() {
        return beaconColor;
    }

    @Override
    public String toString() {
        return "[beaconName: " + getBeaconName() + ", beaconColor: " + getBeaconColor() + "]";
    }
}
