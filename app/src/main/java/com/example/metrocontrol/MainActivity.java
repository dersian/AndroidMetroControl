package com.example.metrocontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.controlmetro.R;

public class MainActivity extends AppCompatActivity {
    String[] lijst = {"Montgomery", "Robiano", "Meiser", "George Henri", "Louiza", "Tour & Taxi", "Fort-Jaco", "Hermann-Debroux"};
    int[] vehicleImages = {R.drawable.bus, R.drawable.tram, R.drawable.metro};

    ListView listView;

    float x1,y1,x2,y2;


    private Button callActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.NearbyList);
        baseAdapter adapterkke = new baseAdapter(getApplicationContext(), lijst, vehicleImages);  //generates the customizable listview
        listView.setAdapter(adapterkke);

        getSupportFragmentManager().beginTransaction().add(R.id.mapsContainer, new mapsFragment()).commit();

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
                    Intent i = new Intent(MainActivity.this, AllControlsActivity.class);
                    startActivity(i);
                }else if(x1 >  x2){
                    Intent i = new Intent(MainActivity.this, ReportControlActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}

