package com.example.metrocontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.controlmetro.R;

public class MainActivity extends AppCompatActivity {
    String[] lijst = {"Montgomery", "Robiano", "Meiser", "George Henri", "Louiza", "Tour & Taxi", "Fort-Jaco", "Hermann-Debroux"};
    int[] vehicleImages = {R.drawable.bus, R.drawable.tram, R.drawable.metro};

    ListView listView;


    private Button callActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.NearbyList);
        baseAdapter adapterkke = new baseAdapter(getApplicationContext(), lijst, vehicleImages);  //generates the customizable listview
        listView.setAdapter(adapterkke);

        callActivity = (Button) findViewById(R.id.snitchButton);
        callActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReportControlActivity();
            }
        });


    }

    public void openReportControlActivity(){
        Intent intent = new Intent(this, ReportControlActivity.class);
        startActivity(intent);
    }
}

