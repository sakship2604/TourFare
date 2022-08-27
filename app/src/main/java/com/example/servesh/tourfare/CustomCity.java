package com.example.servesh.tourfare;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomCity extends BaseAdapter {

    ArrayList<String> placeName = new ArrayList<>();
    ArrayList<String> placeFees = new ArrayList<>();
    LayoutInflater inflater;
    static String fees;
    static int placeFees1 = 0;
    Context context;
    public CustomCity(Context applicationContext, ArrayList<String> placeName, ArrayList<String> placeFees) {
        this.placeName = placeName;
        this.placeFees = placeFees;
        context = applicationContext;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return placeName.size();
    }

    @Override
    public Object getItem(int i) {
        return  placeName.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.activity_custom_city,null);

        final CheckBox chk = (CheckBox)view.findViewById(R.id.chk);
        chk.setText(placeName.get(i));
        TextView txtPlaceFees = (TextView)view.findViewById(R.id.txtPlaceFees);
        txtPlaceFees.setText(placeFees.get(i));

       chk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (chk.isChecked() == true){
                    fees = placeFees.get(i);
                    placeFees1 = placeFees1+Integer.valueOf(fees);

                 //  Toast.makeText(context, ""+placeFees1, Toast.LENGTH_SHORT).show();
               }
           }
       });

        return view;    }
}
