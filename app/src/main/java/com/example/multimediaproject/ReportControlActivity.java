package com.example.multimediaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReportControlActivity extends AppCompatActivity {
    private static final String TAG = "ReportControlActivity";
    float x1,y1,x2,y2;
    private MainActivity mainActivity = new MainActivity();
    private final List<String> autoCompleteStationsList = new ArrayList<>();
    private String[] autoCompleteStationsArray;
    public Map<String, Object> currentControlStationDoc = new HashMap<>();

    Button btnControlSubmit;
    AutoCompleteTextView tvControlStationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_control);

        btnControlSubmit = (Button) findViewById(R.id.btnControleSubmit);
        tvControlStationInput = findViewById(R.id.TextViewControlStation);
        // Auto Complete Input
        createAutoCompleteStations();
        autoCompleteStationsArray = new String[autoCompleteStationsList.size()]; // we can only use an array for the autocomplete -> first use list as omweg
        getStationStringList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, autoCompleteStationsArray);
        tvControlStationInput.setAdapter(adapter);

        tvControlStationInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvControlStationInput.setText("");
            }
        });

        // Submit Button Listener
        btnControlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input from UI
                String stationName = tvControlStationInput.getText().toString();
                // Get the latest control station list from the firestone database
                mainActivity.retrieveFromDatabase(new MainActivity.FirestoreCallback() {
                    @Override
                    public void onCallback(List<String> currentControlStationsDB) {
                        //Log.d("MyActivity", "Call Back from Database: Done");
                        if(!currentControlStationsDB.contains(stationName)){ // not yet in list
                            Log.d(TAG, "Station not yet in database, adding to Database: " + stationName);
                            mainActivity.addToDatabase(currentControlStationDoc, stationName);
                        }
                        else{
                            Log.d(TAG, "Station already in database: " + stationName);
                        }
                        tvControlStationInput.setText("");
                    }
                });
            }
        });

    }


    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 <  x2){
                    Intent i = new Intent(ReportControlActivity.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    // Create the drop down list from the stations in the CSV file
    public void createAutoCompleteStations(){
        InputStream inputStream = getResources().openRawResource(R.raw.stops_data);
        BufferedReader lineReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );
        String line = "";
        int i = 0;
        try {
            while ((line = lineReader.readLine()) != null){
                // Skip header
                if(i == 0){
                    i++;
                    continue;
                }
                // Split by ';'
                String[] tokens = line.split(",");
                // Read the data
                autoCompleteStationsList.add(tokens[2]);
            }
        } catch (IOException e) {
            Log.wtf(TAG, "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    private void getStationStringList(){
        for (int i = 0; i < autoCompleteStationsList.size(); i++){
            autoCompleteStationsArray[i] = autoCompleteStationsList.get(i);
        }
    }

    private void printStringList(List<String> stationList){
        Log.d(TAG, "Printing String List... ");
        for (int i = 0; i < stationList.size(); i++){
            Log.d(TAG, "Station in List: " + stationList.get(i));
        }
    }


}