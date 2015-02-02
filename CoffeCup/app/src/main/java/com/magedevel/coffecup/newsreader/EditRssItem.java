package com.magedevel.coffecup.newsreader;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.magedevel.coffecup.R;
import com.magedevel.coffecup.database.Category;
import com.magedevel.coffecup.database.RssSource;
import com.magedevel.coffecup.newsreader.model.CategoryDataAdapter;
import com.magedevel.coffecup.newsreader.rss.Loader;

public class EditRssItem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rss_item);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_rss_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private CategoryDataAdapter categoryDataAdapter=null;
        private Category category=null;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_edit_rss_item, container, false);
            return rootView;
        }
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Button btnSave=(Button)getActivity().findViewById(R.id.btnSaveRssItem);
            categoryDataAdapter=new CategoryDataAdapter(getActivity());
            Spinner categoryList=(Spinner)getActivity().findViewById(R.id.spnCategory);
            categoryList.setAdapter(categoryDataAdapter);
            categoryList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category=categoryDataAdapter.getData().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txtTitle=(TextView)getActivity().findViewById(R.id.txtSetRssTitle);
                    TextView txtLink=(TextView)getActivity().findViewById(R.id.txtSetRssLink);
                    RssSource rssSource=new RssSource(txtLink.getText().toString(),txtTitle.getText().toString(),category);
                    rssSource.save();
                    Loader loader=new Loader();
                    loader.execute(rssSource);
                    getActivity().finish();
                }
            });
        }
    }
}
