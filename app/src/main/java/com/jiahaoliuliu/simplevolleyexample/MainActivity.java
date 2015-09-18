package com.jiahaoliuliu.simplevolleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the partial implementation of the example found in this tutorial:
 * - http://code.tutsplus.com/tutorials/an-introduction-to-volley--cms-23800
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String SIMPLE_REQUEST_URL = "http://httpbin.org/html";
    private static final String JSON_REQUEST_URL = "http://httpbin.org/get?site=code&network=tutsplus";
    private static final String POST_REQUEST_URL = "http://httpbin.org/post";

    // View
    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link the views
        mContentTextView = (TextView) findViewById(R.id.content_text_view);

        //startSimpleVolleyRequest();

        //startJsonVolleyRequest();

        startVolleyPostRequest();
    }

    private void startSimpleVolleyRequest() {
        // Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SIMPLE_REQUEST_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.v(TAG, "Data received");
//                Log.v(TAG, response);
                mContentTextView.setText(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting data", error);
            }
        });

        //Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void startJsonVolleyRequest() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET, JSON_REQUEST_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Display the response
                mContentTextView.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting json response", error);
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }

    private void startVolleyPostRequest() {
        StringRequest stringPostRequest = new StringRequest(Request.Method.POST, POST_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v(TAG, "Data received ");
                        Log.v(TAG, response);
                        mContentTextView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error sending post request", error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // The post parameter
                params.put("site", "code");
                params.put("network", "tutsplus");
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringPostRequest);
    }
}
