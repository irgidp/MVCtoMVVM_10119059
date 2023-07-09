package com.example.mvctomvvm_10119059;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class Meter {
    private static Meter instance;

    private double meter;

    private MutableLiveData<String> kilometer;
    private MutableLiveData<String> centimeter;

    private Meter() {
        this.meter = 0;
        this.kilometer = new MutableLiveData<>();
        this.centimeter = new MutableLiveData<>();
    }

    public static synchronized Meter getInstance() {
        if (instance == null) {
            instance = new Meter();
        }

        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public LiveData<String> getKilometer() {
        return Transformations.map(centimeter, input -> {
            double centimeterValue = Double.parseDouble(input);
            double kilometerValue = centimeterValue / 100000;
            return String.valueOf(kilometerValue);
        });
    }

    public LiveData<String> getCentimeter() {
        return Transformations.map(kilometer, input -> {
            double kilometerValue = Double.parseDouble(input);
            double centimeterValue = kilometerValue * 100000;
            return String.valueOf(centimeterValue);
        });
    }

    public void setMeter(double meter) {
        this.meter = meter;
        calculateLength();
    }

    private void calculateLength() {
        double kilometerValue = meter / 1000;
        kilometer.postValue(String.valueOf(kilometerValue));

        double centimeterValue = meter * 100;
        centimeter.postValue(String.valueOf(centimeterValue));
    }
}
