package com.example.servesh.tourfare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Button btnRegister;
    EditText edtRUserName,edtRMobile,edtREmail,edtRPassword,edtRCity;

    String registerUserName,registerMobileNo,registerEmailId,registerPassword,registerCity;
    String url = "http://www.rsservicejaipur.com/tour/insertData.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        edtRUserName = (EditText)findViewById(R.id.edtRUserName);
        edtRMobile = (EditText)findViewById(R.id.edtRMobile);
        edtREmail = (EditText)findViewById(R.id.edtREmail);
        edtRPassword = (EditText)findViewById(R.id.edtRPassword);
        edtRCity = (EditText)findViewById(R.id.edtRCity);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((edtRUserName.length()==0)||(edtRMobile.length()==0) || (edtRMobile.length()==0) || (edtREmail.length()==0) || (edtRPassword.length()==0) || (edtRCity.length() == 0) || (edtRMobile.length()<10) || (!isValidEmail(edtREmail.getText().toString()))){

                    if (edtRUserName.length()==0){
                        edtRUserName.setError("Enter your Name");
                        edtRUserName.requestFocus();
                    }
                    if (edtRMobile.length()==0){
                        edtRMobile.setError("Enter your Mobile No");
                        edtRMobile.requestFocus();
                    }
                    if (edtRMobile.length()<10){
                        edtRMobile.setError("Enter Valid Mobile No");
                        edtRMobile.requestFocus();
                    }
                    if (edtREmail.length()==0){
                        edtREmail.setError("Enter your Email Id");
                        edtREmail.requestFocus();
                    }
                    if (!isValidEmail(edtREmail.getText().toString())){
                        edtREmail.setError(" Please Enter valid Email Id");
                        edtREmail.requestFocus();
                    }
                    if (edtRPassword.length()==0){
                        edtRPassword.setError("Enter your password");
                        edtRPassword.requestFocus();
                    }
                    if (edtRCity.length()==0){
                        edtRCity.setError("Enter your City");
                        edtRCity.requestFocus();
                    }

                }
                else {
                    register();

                }

                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void register() {

        registerUserName = edtRUserName.getText().toString();
        registerMobileNo = edtRMobile.getText().toString();
        registerEmailId = edtREmail.getText().toString();
        registerPassword = edtRPassword.getText().toString();
        registerCity = edtRCity.getText().toString();

        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                loading.dismiss();

                Toast.makeText(Register.this, "You are successfuly Registered .", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();

                Toast.makeText(Register.this, " Please try again "+error, Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parameters = new HashMap<String, String>();

                parameters.put("registerUserName",registerUserName);
                parameters.put("registerMobileNo",registerMobileNo);
                parameters.put("registerEmailId",registerEmailId);
                parameters.put("registerPassword",registerPassword);
                parameters.put("registerCity",registerCity);




                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(Register.this);
        rQueue.add(request);


    }

    private boolean isValidEmail(String email)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
