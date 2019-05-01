package com.example.mp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mp5.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.view.inputmethod.InputMethodManager;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONStringer;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.text.method.ScrollingMovementMethod;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String result;
    private static RequestQueue requestQueue;
    private int index = 0;

    private Button main;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.mainButton);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        textResult = findViewById(R.id.textResult);

        main.setVisibility(View.VISIBLE);
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        textResult.setVisibility(View.GONE);


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Have a good day!!", Toast.LENGTH_SHORT).show();
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Have a good day!!", Toast.LENGTH_SHORT).show();
                textResult.setVisibility(View.VISIBLE);
                getDadJoke();
            }
        });

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Have a good day!!", Toast.LENGTH_SHORT).show();
//                //textResult.setVisibility(View.VISIBLE);
//                getCatPic();
//            }
//        });


        requestQueue = Volley.newRequestQueue(this);
        //TextView forNow = findViewById(R.id.textView);
        //getDadJoke();
    }
    //564c54f36d8a4b42b394193d68795fc8 News API

    void getDadJoke() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                    Request.Method.GET, "https://newsapi.org/v2/top-headlines?country=us&" +
                    "apiKey=564c54f36d8a4b42b394193d68795fc8", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            setDadJoke(response);
                            System.out.println("Success");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("failure");
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("api failed");
        }
    }

    void setDadJoke(JSONObject input) {
        try {
            JSONArray articles = input.getJSONArray("articles");
            JSONObject first = articles.getJSONObject(index);
            String headline = first.get("title").toString();
            String url = first.get("url").toString();
            textResult.setText(headline + "\n" + url);
            index++;
        } catch (Exception e) {
            System.out.println("rekt");
        }
    }

//    //d13f9667-81fe-4ece-b1aa-cf8319f26c3b CAT API
//    void getCatPic() {
//        try {
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//
//                    Request.Method.GET, "https://api.thecatapi.com/v1/images/search?" +
//                    "apikey-d13f9667-81fe-4ece-b1aa-cf8319f26c3b", null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            setCatPic(response);
//                            System.out.println("Success");
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    System.out.println("failure");
//                }
//            });
//            jsonObjectRequest.setShouldCache(false);
//            requestQueue.add(jsonObjectRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("api failed");
//        }
//    }
//
//    void setCatPic(JSONObject input) {
//        try {
//            JSONArray cats = input.getJSONArray("");
//            JSONObject newCat = cats.getJSONObject(0);
//            System.out.println(newCat.get(".url").toString());
//            //add image here
//        } catch (Exception e) {
//            System.out.println("rekt");
//        }
//    }
}
