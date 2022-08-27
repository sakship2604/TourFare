package com.example.servesh.tourfare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edtLPhone,edtLPassword;
    Button btnLLogin,btnLRegister;
    String registerMobileNo,registerPassword;
    String url = "http://www.rsservicejaipur.com/tour/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); //Hiding the titleBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//To hide the notification bar

        setContentView(R.layout.activity_login);

        edtLPhone = (EditText)findViewById(R.id.edtLPhone);
        edtLPassword = (EditText)findViewById(R.id.edtLPassword);
        btnLLogin = (Button)findViewById(R.id.btnLLogin);
        btnLRegister = (Button)findViewById(R.id.btnLRegister);

        btnLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edtLPhone.length()==0) || (edtLPhone.length()<10) || (edtLPassword.length()==0)){

                    if (edtLPhone.length()==0){
                        edtLPhone.setError("Please Enter your Mobile No");
                        edtLPhone.requestFocus();
                    }
                    if (edtLPhone.length()<10){
                        edtLPhone.setError("Please Enter valid Mobile No");
                        edtLPhone.requestFocus();
                    }

                    if (edtLPassword.length()==0){
                        edtLPassword.setError("Please Enter Password");
                        edtLPassword.requestFocus();
                    }
                }
                else {

                    login();
//                    Intent i = new Intent(Login.this,Home.class);
//                    startActivity(i);
//                    finish();

                }
            }
        });

        btnLRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });
    }

    private void login() {

        registerMobileNo = edtLPhone.getText().toString();
        registerPassword = edtLPassword.getText().toString();

        final ProgressDialog loading = ProgressDialog.show(this, "Checking...", "Please wait...", false, false);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                loading.dismiss();

                String s = response;
                Toast.makeText(Login.this, ""+s, Toast.LENGTH_LONG).show();
                loginS(s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();

                Toast.makeText(Login.this, "Please try again....!!!", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                //String image = getStringImage(bitmap);


                parameters.put("registerMobileNo",registerMobileNo);
                parameters.put("registerPassword",registerPassword);


                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(Login.this);
        rQueue.add(request);



    }

    private void loginS(String s) {

        try {

            JSONObject j = new JSONObject(s);
            JSONArray js = j.getJSONArray("data");


            String sts= j.getString("status");

            if(sts.equals("1")){


                Intent i = new Intent(Login.this,Home.class);
                startActivity(i);
                finish();


            }

            else{
                Toast.makeText(Login.this, "Please try again..?", Toast.LENGTH_LONG).show();

            }
            //Toast.makeText(MainActivity.this, "" + sts, Toast.LENGTH_LONG).show();
        }catch (Exception g){

            Toast.makeText(Login.this, ""+g, Toast.LENGTH_SHORT).show();
        }

    }

}
