package com.example.metrocontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.controlmetro.R;

public class MainActivity extends AppCompatActivity {
    String lijst[] = {"Montgomery", "Robiano", "Meiser", "George Henri", "Simonis" };
    int vehicleImages[] = {R.drawable.bus, R.drawable.tram, R.drawable.metro};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.NearbyList);
        baseAdapter adapterkke = new baseAdapter(getApplicationContext(), lijst, vehicleImages);
        listView.setAdapter(adapterkke);
    }
}