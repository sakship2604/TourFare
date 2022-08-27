package com.example.servesh.tourfare;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Places extends AppCompatActivity {

    ListView lstPlaces;
    Button btnGetFare;
    ArrayList<String> placeName = new ArrayList<>();
    ArrayList<String> placeFees = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        lstPlaces = (ListView)findViewById(R.id.lstPlaces);
        btnGetFare = (Button)findViewById(R.id.btnGetFare);

        this.placeName.addAll(City.placeName);
        this.placeFees.addAll(City.placeFees);

        CustomCity customCity = new CustomCity(getApplicationContext(),placeName,placeFees);
        lstPlaces.setAdapter(customCity);

        btnGetFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertMthd();
            }
        });
    }

    private void alertMthd() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Tour Fare");
        b.setMessage("Your Total Amount will be: ₹"+CustomCity.placeFees1);
        b.setIcon(R.drawable.icon);

        b.setCancelable(false);

        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // Toast.makeText(Places.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });

        b.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(Places.this, "No, I'm not a Student", Toast.LENGTH_SHORT).show();
            }
        });

        b.show();
    }
}
