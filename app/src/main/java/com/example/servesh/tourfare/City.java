package com.example.servesh.tourfare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class City extends AppCompatActivity {

    ListView lstCCity;
    String placeCityId;
    Button btnPlaces;
    String url = "http://www.rsservicejaipur.com/tour/getPlacesWithId.php";
    static ArrayList<String>placeName = new ArrayList<>();
    static ArrayList<String>placeFees = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        lstCCity = (ListView)findViewById(R.id.lstCCity);
        //btnPlaces = (Button) findViewById(R.id.btnPlaces);

        ArrayAdapter<String> ar = new ArrayAdapter<String>(City.this,android.R.layout.simple_list_item_1,Home.cityName);

        lstCCity.setAdapter(ar);

        lstCCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    switch (i){

                        case 0: placeCityId = "1";
                        break;
                        case 1: placeCityId = "2";
                        break;
                        case 2: placeCityId = "3";
                        break;
                        case 3: placeCityId = "4";
                        break;
                        case 4: placeCityId = "5";
                        break;
                        case 5: placeCityId = "6";
                        break;
                        case 6: placeCityId = "7";
                        break;
                        case 7: placeCityId = "8";
                        break;
                        case 8: placeCityId = "9";
                        break;
                        case 9: placeCityId = "10";
                        break;
                        default:break;
                    }

                getPlaces();
            }

        });

//        btnPlaces.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void getPlaces() {


        final ProgressDialog loading = ProgressDialog.show(this, "Checking...", "Please wait...", false, false);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                loading.dismiss();

                String s = response;
            //    Toast.makeText(City.this, ""+s, Toast.LENGTH_LONG).show();
                loginS(s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();

                Toast.makeText(City.this, "Please try again....!!!", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                //String image = getStringImage(bitmap);


                parameters.put("placeCityId",placeCityId);


                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(City.this);
        rQueue.add(request);



    }

    private void loginS(String s) {

        try {

            JSONObject j = new JSONObject(s);
            JSONArray js = j.getJSONArray("data");


            String sts= j.getString("status");

            if(sts.equals("1")){

                for (int i = 0;i<js.length();i++) {
                    JSONObject jss = js.getJSONObject(i);

                    placeName.add(jss.getString("placeName")) ;
                    placeFees.add(jss.getString("placeFees"));
                    //subjectId = jss.getString("teachSubjectId");
                }
               // Toast.makeText(Login.this, "Name = "+teachName+"\nSubject"+Subject+"\nEmail"+teachEmail+"\nMobile"+teachMobile, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(City.this,Places.class);
                startActivity(i);
                finish();


            }

            else{
                Toast.makeText(City.this, "Please try again..?", Toast.LENGTH_LONG).show();

            }
            //Toast.makeText(MainActivity.this, "" + sts, Toast.LENGTH_LONG).show();
        }catch (Exception g){

            Toast.makeText(City.this, ""+g, Toast.LENGTH_SHORT).show();
        }

    }

}


