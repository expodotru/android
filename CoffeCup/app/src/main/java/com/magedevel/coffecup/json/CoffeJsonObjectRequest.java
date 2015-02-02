package com.magedevel.coffecup.json;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by spco0d6 on 29/01/2015.
 */
public class CoffeJsonObjectRequest extends JsonObjectRequest {
    public CoffeJsonObjectRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

}
