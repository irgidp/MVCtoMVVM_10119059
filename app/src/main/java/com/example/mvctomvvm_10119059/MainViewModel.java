package com.example.mvctomvvm_10119059;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> meter = new MutableLiveData<>();
    private LiveData<String> kilometer;
    private LiveData<String> centimeter;
    private Meter model;

    public MainViewModel() {
        model = Meter.getInstance();
        kilometer = model.getKilometer();
        centimeter = model.getCentimeter();
    }

    public void setMeter(String meterValue) {
        model.setMeter(Double.parseDouble(meterValue));
    }

    public LiveData<String> getKilometer() {
        return kilometer;
    }

    public LiveData<String> getCentimeter() {
        return centimeter;
    }

    @Override
    protected void onCleared() {
        model.destroy();
        super.onCleared();
    }
}

