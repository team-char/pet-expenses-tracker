package com.levibrooke.petexpensestracker.Model;

public class DataPoint {
    String label;
    Double y;

    public DataPoint(String label, Double y) {
        this.label = label;
        this.y = y;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public Double getY() {
        return y;
    }

}
