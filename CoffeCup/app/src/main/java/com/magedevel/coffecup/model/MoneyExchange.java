package com.magedevel.coffecup.model;

import android.content.SharedPreferences;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.magedevel.coffecup.AppController;
import com.magedevel.coffecup.json.CoffeJsonObjectRequest;
import com.magedevel.coffecup.json.IOnCompleteJsonRequest;
import com.magedevel.coffecup.yahoo.AbstractApi;

import org.json.JSONObject;

/**
 * Created by spco0d6 on 29/01/2015.
 */
public class MoneyExchange {
    private SharedPreferences settings;
    private String rate="";
    private String date="";
    private String time="";
    private String ask="";
    private String bid="";
    private final String  EURRUB="EURRUB";
    private JsonArrayRequest req=null;
    private AbstractApi api=null;
    private IOnCompleteJsonRequest message=null;

    public MoneyExchange(AbstractApi Api,IOnCompleteJsonRequest Message){
        this.api=Api;
        this.message=Message;

        this.loadData();
    }
    public void RefreshData(){

    }
    public String getRate() {
        return rate;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAsk() {
        return ask;
    }

    public String getBid() {
        return bid;
    }
    private void loadData(){
        // we can user shared preferences to get data
        // check the timestamp
        /*
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", "");
        editor.commit();
        */
        CoffeJsonObjectRequest theReq = new CoffeJsonObjectRequest(this.api.GetExchangeData(EURRUB),null,
                new Response.Listener<JSONObject>()  {
            @Override
            public void onResponse(JSONObject response) {
                populateItems(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(theReq);
    }

    private  void populateItems(JSONObject row)
    {

            try{
                JSONObject query=row.getJSONObject("query");
                JSONObject result=query.getJSONObject("results");
                JSONObject rate=result.getJSONObject("rate");
                this.rate=rate.getString("Rate");
                this.bid=rate.getString("Bid");
                this.date=rate.getString("Date")+ " " + rate.getString("Time");
                this.ask=rate.getString("Ask");
                StringBuilder out=new StringBuilder();

                out.append("Rate:");
                out.append(this.rate);
                out.append(System.getProperty("line.separator"));

                out.append("Bid:");
                out.append(this.bid);
                out.append(System.getProperty("line.separator"));

                out.append("Ask:");
                out.append(this.ask);
                out.append(System.getProperty("line.separator"));

                out.append("Date:");
                out.append(this.date);
                out.append(System.getProperty("line.separator"));

                this.message.OnComplete(out.toString());
            }catch (Exception e)
            {
                // do not load object
            }



    }

}

