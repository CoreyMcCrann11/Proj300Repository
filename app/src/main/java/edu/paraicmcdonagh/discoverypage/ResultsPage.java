package edu.paraicmcdonagh.discoverypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultsPage extends StepTimer {

    TextView TimeDisplay;
    TextView DateDisplay;
    TextView CaloriesDisplay;
    TextView MeterDisplay;

    private Integer StepCounter = 0;
    private String timer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        TimeDisplay = findViewById(R.id.tvTimeDisplay);
        DateDisplay = findViewById(R.id.tvDateDisplay);
        setDate();
        CaloriesDisplay = findViewById(R.id.tvCaloriesDisplay);
        MeterDisplay = findViewById(R.id.tvMeterDisplay);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            StepCounter = (int) extras.get("stepCounter");
            timer = (String) extras.get("timer");
        }
        setCalories();
        setTime();
        setMeters();
        setAmbientEnabled();
    }

    private void setTime() {
        TimeDisplay.setText(String.valueOf(timer));
    }

    private void setCalories() {
        double cal = StepCounter * 0.04;
        CaloriesDisplay.setText(String.valueOf(cal));
    }

    private void setMeters() {
        double mtrs = StepCounter * 0.8;
        MeterDisplay.setText(String.valueOf(mtrs));
    }

    private void setDate() {
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        String date = formater.format(now);
        DateDisplay.setText(date);
    }

    private void setAmbientEnabled() {
    }

    public void doReturn(View view) {
        Intent StepTimer = new Intent( this, StepTimer.class);
        startActivity(StepTimer);
    }
}