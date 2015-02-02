package com.magedevel.coffecup.newsreader.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.magedevel.coffecup.R;
import com.magedevel.coffecup.database.Category;


import java.util.List;

/**
 * Created by spco0d6 on 31/01/2015.
 */
public class CategoryDataAdapter extends BaseAdapter {
    private Activity activity;

    private static LayoutInflater inflater = null;
    private List<Category> data;
    private Category item = null;
    int i = 0;

    public List<Category> getData() {
        return data;
    }

    /**
     * **********  CustomAdapter Constructor ****************
     */
    public CategoryDataAdapter(Activity a) {

        /********** Take passed values **********/
        activity = a;
        // load data from database
        data=  Category.find(Category.class, "");
        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder {

        public TextView textCategoryValue;
        public TextView textCategoryName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.spinner_category_item, null);

            holder = new ViewHolder();
            holder.textCategoryValue = (TextView) vi.findViewById(R.id.textCategoryValue);
            holder.textCategoryName=(TextView)vi.findViewById(R.id.textCategoryName);

            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        if (data.size() <= 0) {
            Toast.makeText(activity, activity.getResources().getString(R.string.message_no_rss_found), Toast.LENGTH_LONG).show();
        } else {
            /***** Get each Model object from Arraylist ********/
            item = null;
            item = data.get(position);
            /************  Set Model values in Holder elements ***********/
            holder.textCategoryValue.setText(item.getId().toString());
            holder.textCategoryName.setText(item .getTitle());
        }
        return vi;
    }
}
