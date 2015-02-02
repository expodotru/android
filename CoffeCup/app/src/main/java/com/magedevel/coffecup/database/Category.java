package com.magedevel.coffecup.database;

import com.orm.SugarRecord;

/**
 * Created by spco0d6 on 30/01/2015.
 */
public class Category extends SugarRecord <Category> {
    public String getTitle() {
        return title;
    }

    String title="";
    public Category(){
        this.title="";
    }
    public Category(String title){
        this.title=title;
    }
}
