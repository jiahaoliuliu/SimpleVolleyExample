package com.jiahaoliuliu.simplevolleyexample;

import android.preference.PreferenceActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Special method used to set the cookies and priorities.
 * Created by jiahaoliuliu on 15/9/18.
 */
public class CustomVolleyRequest extends JsonObjectRequest {

    private Map<String, String> headers = new HashMap<String, String>();
    private Priority mPriority;

    // Use the default constructor
    public CustomVolleyRequest(int method, String url, JSONObject jsonObject,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, jsonObject, listener, errorListener);
    }

    public void setCookies(List<String> cookies) {
        StringBuilder sb = new StringBuilder();
        for (String cookie : cookies) {
            sb.append(cookie).append("; ");
        }

        headers.put("Cookie", sb.toString());
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    public void setPriority(Priority priority) {
        this.mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        // If the priority was not set, use the normal one
        return mPriority != null ? mPriority : Priority.NORMAL;
    }
}
