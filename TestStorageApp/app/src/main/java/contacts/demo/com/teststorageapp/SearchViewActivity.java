package contacts.demo.com.teststorageapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import java.util.ArrayList;

import contacts.demo.com.adapter.ListAdapter;

public class SearchViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView list;
    ListAdapter adapter;
    SearchView editsearch;
    String[] monthnameList;
    ArrayList<MonthName> monthlist = new ArrayList<MonthName>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_view);

        monthnameList = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        list = (ListView)findViewById(R.id.list_view);
        for (int i = 0; i < monthnameList.length; i++) {
            MonthName monthNames = new MonthName(monthnameList[i]);
            monthlist.add(monthNames);
        }
        adapter = new ListAdapter(this,monthlist);
        list.setAdapter(adapter);
        editsearch = (SearchView)findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String text = s;
        adapter.filter(text);
        return false;
    }
}
