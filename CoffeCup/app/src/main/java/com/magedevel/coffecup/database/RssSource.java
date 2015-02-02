package com.magedevel.coffecup.database;

import com.orm.SugarRecord;

/**
 * Created by spco0d6 on 30/01/2015.
 */
public class RssSource extends SugarRecord<RssSource> {
    String link;
    String title;
    Category category;

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public RssSource(){
        this.link="";
        this.title="";
        this.category=null;

    }
    public RssSource(String link, String title, Category category){
        this.link=link;
        this.title=title;
        this.category=category;
    }
}
