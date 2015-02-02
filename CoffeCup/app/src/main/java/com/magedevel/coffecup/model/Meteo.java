package com.magedevel.coffecup.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.magedevel.coffecup.AppController;
import com.magedevel.coffecup.json.CoffeJsonObjectRequest;
import com.magedevel.coffecup.json.IOnCompleteJsonRequest;
import com.magedevel.coffecup.json.IOnCompleteWeatherRequest;
import com.magedevel.coffecup.openweathermap.AbstractApi;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by spco0d6 on 29/01/2015.
 */
public class Meteo {
    private AbstractApi api=null;
    private IOnCompleteWeatherRequest onCompleteJsonRequest=null;
    private String tempMin="";
    private String tempMax="";
    private String meteoDescr="";
    private String umidity="";
    private String pressure="";
    private String windSpeed="";
    private String windDeg="";
    public Meteo(AbstractApi Api,IOnCompleteWeatherRequest OnCompleteJsonRequest){
        this.api=Api;
        this.onCompleteJsonRequest=OnCompleteJsonRequest;
        this.loadData();
    }

    private void loadData(){
        // we can user shared preferences to get data
        // check the timestamp
        /*
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", "");
        editor.commit();
        */
        CoffeJsonObjectRequest theReq = new CoffeJsonObjectRequest(this.api.GetCurrentWeather(),null,
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

            JSONArray result=row.getJSONArray("weather");
            for (int i = 0; i < result.length(); i++) {
                JSONObject weather = result.optJSONObject(i);
                this.meteoDescr=weather.getString("description");
                break;

            }
            JSONObject wind=row.getJSONObject("wind");
            this.windSpeed=wind.getString("speed");
            this.windDeg=wind.getString("deg");

            JSONObject main=row.getJSONObject("main");
            this.tempMin=main.getString("temp_min");
            this.tempMax=main.getString("temp_max");
            this.pressure=main.getString("pressure");
            this.umidity=main.getString("humidity");


            StringBuilder out =new StringBuilder();
            out.append("Meteo:");
            out.append(this.meteoDescr);
            out.append(System.getProperty("line.separator"));

            out.append("Temp Min:");
            out.append(this.tempMin);
            out.append(System.getProperty("line.separator"));

            out.append("Temp Max:");
            out.append(this.tempMax);
            out.append(System.getProperty("line.separator"));

            out.append("Pressione:");
            out.append(this.pressure);
            out.append(System.getProperty("line.separator"));

            out.append("Umidità:");
            out.append(this.umidity);
            out.append(" %");
            out.append(System.getProperty("line.separator"));

            out.append("Vento:");
            out.append(this.windSpeed);
            out.append(System.getProperty("line.separator"));

            out.append("Angolo vento:");
            out.append(this.windDeg);
            out.append(System.getProperty("line.separator"));


            this.onCompleteJsonRequest.OnCompleteWeatherRequest(out.toString());
        }catch (Exception e)
        {
            // do not load object
        }



    }

}
