package contacts.demo.com.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import contacts.demo.com.teststorageapp.MonthName;
import contacts.demo.com.teststorageapp.R;

/**
 * Created by ECS-27 on 05-01-2018.
 */

public class ListAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    private List<MonthName> monthnameList = null;
    private ArrayList<MonthName> monthlist;

    public ListAdapter(Context mContext, List<MonthName> monthnameList) {
        this.mContext = mContext;
        this.monthnameList = monthnameList;
        inflater = LayoutInflater.from(mContext);
        this.monthlist = new ArrayList<MonthName>();
        this.monthlist.addAll(monthnameList);

    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return monthnameList.size();
    }

    @Override
    public Object getItem(int position) {
        return monthnameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(monthnameList.get(position).getMonthName());
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        monthnameList.clear();
        if (charText.length() == 0) {
            monthnameList.addAll(monthlist);
        } else {
            for (MonthName wp : monthlist) {
                if (wp.getMonthName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    monthnameList.add(wp);
                }
            }
        }
        notifyDataSetChanged();

    }

}
