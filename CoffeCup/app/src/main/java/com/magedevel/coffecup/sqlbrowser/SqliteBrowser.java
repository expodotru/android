package com.magedevel.coffecup.sqlbrowser;



import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.magedevel.coffecup.R;
import com.magedevel.coffecup.database.Category;
import com.magedevel.coffecup.database.News;
import com.magedevel.coffecup.database.RssSource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SqliteBrowser#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SqliteBrowser extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SqliteBrowser.
     */
    // TODO: Rename and change types and number of parameters
    public static SqliteBrowser newInstance(String param1, String param2) {
        SqliteBrowser fragment = new SqliteBrowser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public SqliteBrowser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView txtSqlOutPut=(TextView) getActivity().findViewById(R.id.txtOutSql);
        txtSqlOutPut.setMovementMethod(new ScrollingMovementMethod());

        Button btnCategorie=(Button)getActivity().findViewById(R.id.btnCategorie);
        btnCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // execute the query
                StringBuilder out = new StringBuilder();
                out.append("Category");
                out.append(System.getProperty("line.separator"));
                List<Category> categories= Category.find(Category.class,"");
                for (Category category : categories){
                    out.append(category.getId());
                    out.append(" | ");
                    out.append(category.getTitle());
                    out.append(System.getProperty("line.separator"));
                }
                TextView txtOutSql=(TextView)getActivity().findViewById(R.id.txtOutSql);
                txtOutSql.setText(out.toString());
            }
        });
        Button btnRssSource=(Button)getActivity().findViewById(R.id.btnFonti);
        btnRssSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder out = new StringBuilder();
                out.append("Rss Source");
                out.append(System.getProperty("line.separator"));
                List<RssSource> rssSources= RssSource.find(RssSource.class, "");
                for (RssSource rssSource: rssSources){
                    out.append(rssSource.getId());
                    out.append(" | ");
                    out.append(rssSource.getTitle());
                    out.append(" | ");
                    out.append(rssSource.getLink());
                    out.append(" | ");
                    out.append(rssSource.getCategory().getTitle());
                    out.append(System.getProperty("line.separator"));
                }
                TextView txtOutSql=(TextView)getActivity().findViewById(R.id.txtOutSql);
                txtOutSql.setText(out.toString());
            }
        });
        // btnNews
        Button btnNews=(Button)getActivity().findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder out = new StringBuilder();
                List<News> news=News.find(News.class, "");
                out.append("news");
                out.append(System.getProperty("line.separator"));
                for(News newsitem : news){


                    out.append(newsitem.getId());
                    out.append(" | ");
                    out.append(newsitem.getTitle());
                    out.append(" | ");
                    out.append(newsitem.getLink());
                    out.append(" | ");
                    out.append(newsitem.getPhoto());
                    out.append(" | ");
                    out.append(newsitem.getRssSource().getTitle());
                    out.append(" | ");
                    out.append(newsitem.getRssSource().getCategory().getTitle());

                    out.append(System.getProperty("line.separator"));
                }
                TextView txtOutSql=(TextView)getActivity().findViewById(R.id.txtOutSql);
                txtOutSql.setText(out.toString());
            }
        });

        // clear all
        Button btnClearAll=(Button)getActivity().findViewById(R.id.btnClearAll);
        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                News.deleteAll(News.class);
                RssSource.deleteAll(RssSource.class);
                Category.deleteAll(Category.class);
                TextView txtOutSql=(TextView)getActivity().findViewById(R.id.txtOutSql);
                txtOutSql.setText("");
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sqlite_browser, container, false);
    }


}
