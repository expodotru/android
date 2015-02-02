package com.magedevel.coffecup.newsreader.rss;

import android.os.AsyncTask;
import android.util.Log;

import com.magedevel.coffecup.database.News;
import com.magedevel.coffecup.database.RssSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by spco0d6 on 31/01/2015.
 */
public class Loader extends AsyncTask<RssSource,Void,List<News>> {
    @Override
    protected List<News> doInBackground(RssSource ... sources) {

        RssSource feed = sources[0];

        URL url = null;
        try {

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            url = new URL(feed.getLink());
            RssHandler rh = new RssHandler(feed);

            xr.setContentHandler(rh);
            xr.parse(new InputSource(url.openStream()));


            Log.e("ASYNC", "PARSING FINISHED");
            return rh.getArticleList();

        } catch (IOException e) {
            Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
        } catch (SAXException e) {
            Log.e("RSS Handler SAX", e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("RSS Handler Parser Config", e.toString());
        }

        return null;
    }



}
