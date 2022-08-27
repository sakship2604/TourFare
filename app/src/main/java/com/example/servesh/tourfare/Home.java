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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    Button btnAdd;
    ListView listView;
    //String cityId,cityName;
    static ArrayList<String> cityId = new ArrayList<>();
    static ArrayList<String> cityName = new ArrayList<>();

    String url = "http://www.rsservicejaipur.com/tour/getCityData.php";
    String city[] = {"Delhi","Jaipur","Chandigarh","Kota","Ajmer","Bikaner","Bundi","Noida","Udaipur","Mt. Abu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView)findViewById(R.id.lstPlaces);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        ArrayAdapter<String> ar = new ArrayAdapter<String>(Home.this,android.R.layout.simple_list_item_1,city);

        listView.setAdapter(ar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getMgtData();

            }
        });

    }

    private void getMgtData() {

        final ProgressDialog loading = ProgressDialog.show(this, "Loading...", "Please wait...", false, false);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                loading.dismiss();

                String s = response;
                //    Toast.makeText(Team.this, ""+s, Toast.LENGTH_LONG).show();
                abc(s);

                Intent i = new Intent(Home.this,City.class);
                startActivity(i);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();

                Toast.makeText(Home.this, "Please try again....!!!\n"+error, Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(Home.this);
        rQueue.add(request);

    }

    private void abc(String s) {
        try {

            JSONObject j = new JSONObject(s);
            JSONArray js = j.getJSONArray("data");


            String sts= j.getString("status");

            if(sts.equals("1")){

                for (int i = 0;i<js.length();i++) {
                    JSONObject jss = js.getJSONObject(i);

                    cityId.add(jss.getString("cityId"));
                    cityName.add(jss.getString("cityName"));

                }
                //  Toast.makeText(Team.this, ""+name, Toast.LENGTH_SHORT).show();


            }

            else{
                Toast.makeText(Home.this, "Please try again..?", Toast.LENGTH_LONG).show();

            }
            //Toast.makeText(MainActivity.this, "" + sts, Toast.LENGTH_LONG).show();
        }catch (Exception g){

            Toast.makeText(Home.this, ""+g, Toast.LENGTH_SHORT).show();
        }


    }

}
