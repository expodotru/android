package com.magedevel.coffecup.database;

import com.orm.SugarRecord;

/**
 * Created by spco0d6 on 30/01/2015.
 */
public class News extends SugarRecord<News> {
    String photo;
    String title;
    String summary;
    String link;
    RssSource rssSource;




    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setRssSource(RssSource rssSource) {
        this.rssSource = this.rssSource;
    }

    public String getPhoto() {

        return photo;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }

    public RssSource getRssSource() {
        return rssSource;
    }

    public News(){
        this.photo="";
        this.title="";
        this.summary="";
        this.link="";
        this.rssSource=null;

    }
    public News(String title,String photo,  String summary, String link,RssSource rssSource){
        this.title=title;
        this.photo=photo;
        this.summary=summary;
        this.link=link;
        this.rssSource=rssSource;

     }
}
