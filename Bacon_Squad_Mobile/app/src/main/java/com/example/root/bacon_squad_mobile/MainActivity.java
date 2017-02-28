package com.example.root.bacon_squad_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button next = (Button) findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Log.i("Komunikat", "udalo sie dostac");
                EditText name = (EditText)findViewById(R.id.editText);
                EditText password = (EditText)findViewById(R.id.editText2);
                Log.i("Name: ", name.getText().toString());
                Log.i("Password: ", password.getText().toString());
                checkUserLogin(name.getText().toString(), password.getText().toString());
//                getQuestion();
            }
//                Intent myIntent = new Intent(view.getContext(), Activity2.class);
//                startActivityForResult(myIntent, 0);

            });
    }


    void checkUserLogin(final String name, final String password) {
//        final TextView mTextView = (TextView) findViewById(R.id.request);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.170.20.177:8080/login_mobile";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
//                        Log.d("Response", response);
                        Log.i("CHUJ","KURWA!!!");
                        Log.i("Respone: ", response);
                        if (response.equals("1")){
                            Log.i("Odp", response);
                            getQuestion();
                        }else{
                            Toast.makeText(MainActivity.this, "Invalid name or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "...");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }

    void getQuestion(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.170.20.177:8080/mobile_question";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 20 characters of the response string.
                        Log.i("Respone: ", response);
                        Log.i("jestem", "tutaj");
                        String[] list_responses = Activity2.splitString(response);
                        Log.i("Dlugosc", String.valueOf(list_responses.length));
                        if (String.valueOf(list_responses.length).equals("9")){
//                            BEACON
                            Log.i("BEACONS", response);
                            String beacon_id = list_responses[1];
                            String hint = list_responses[7];
                            String ans1 = list_responses[2];
                            String ans2 = list_responses[3];
                            String ans3 = list_responses[4];
                            String ans4 = list_responses[5];

                            Intent myIntent = new Intent(getApplicationContext(), Questions.class);
                            startActivityForResult(myIntent, 0);

                        }else{
//                            QUESTION
                            Log.i("Questions", response);
//                            String[] list_responses = Activity2.splitString(response);
                            String question = list_responses[1];
                            String ans1 = list_responses[2];
                            String ans2 = list_responses[3];
                            String ans3 = list_responses[4];
                            String ans4 = list_responses[5];

                            Intent myIntent = new Intent(getApplicationContext(), Activity2.class);
                            startActivityForResult(myIntent, 0);
                        }

//                        Log.i("Respone: ", jakis[0]);
//                        Log.i("Respone: ", jakis[1]);

//                        int response_len = response.length();
//                        mTextView.setText("Response is: "+ response.substring(0, response_len));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

}