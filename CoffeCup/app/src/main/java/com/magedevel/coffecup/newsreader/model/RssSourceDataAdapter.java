package com.magedevel.coffecup.newsreader.model;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.magedevel.coffecup.R;
import com.magedevel.coffecup.database.RssSource;

import java.util.List;

/**
 * Created by spco0d6 on 31/01/2015.
 */
public class RssSourceDataAdapter extends BaseAdapter{
    private Activity activity;
    private Fragment fragment;

    private static LayoutInflater inflater = null;
    private List<RssSource> data;
    private RssSource item = null;
    int i = 0;

    public List<RssSource> getData() {
        return data;
    }

    /**
     * **********  CustomAdapter Constructor ****************
     */
    public RssSourceDataAdapter(Activity a, Integer categoryId) {

        /********** Take passed values **********/
        activity = a;
        // load data from database
        data=  RssSource.find(RssSource.class, "");
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

    /**
     * ****** Create a holder to contain inflated xml file elements **********
     */
    public static class ViewHolder {

        public TextView txtTitle;
        public TextView txtPk;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.txtTitle = (TextView) vi.findViewById(R.id.txtRssTitle);
            holder.txtPk=(TextView)vi.findViewById(R.id.txtRssPk);

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
            holder.txtTitle.setText(item .getTitle());
            holder.txtPk.setText(item.getId().toString());

        }
        return vi;
    }
}
