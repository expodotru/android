package com.magedevel.coffecup.yahoo;

import android.net.Uri;

/**
 * Created by spco0d6 on 29/01/2015.
 */
public class Api extends   AbstractApi{
    @Override
    public String GetExchangeData(String pair) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from yahoo.finance.xchange where pair in (\"");
        sql.append(pair);
        sql.append("\")");

        //return "http://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.xchange where pair in (\""+pair+"\")&env=store://datatables.org/alltableswithkeys";
        Uri.Builder uri = Uri.parse("http://query.yahooapis.com/v1/public/yql/").buildUpon();
        uri.appendQueryParameter("q",String.valueOf(sql.toString()));
        uri.appendQueryParameter("format",String.valueOf("json"));
        uri.appendQueryParameter("env",String.valueOf("store://datatables.org/alltableswithkeys"));
        return uri.build().toString();
     }
}
