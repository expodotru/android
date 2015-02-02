package com.magedevel.coffecup.openweathermap;

import android.net.Uri;

/**
 * Created by spco0d6 on 29/01/2015.
 */
public class OwApi extends  AbstractApi {
    @Override
    public String GetCurrentWeather() {
        // http://api.openweathermap.org/data/2.5/weather?q=Izhevsk,ru&units=metric&lang=it

        Uri.Builder uri = Uri.parse("http://api.openweathermap.org/data/2.5/weather/").buildUpon();
        uri.appendQueryParameter("q",String.valueOf("Izhevsk,ru"));
        uri.appendQueryParameter("units",String.valueOf("metric"));
        uri.appendQueryParameter("lang",String.valueOf("it"));
        return uri.build().toString();
    }

    @Override
    public String GetForecastWeather() {
        return null;
    }
}
