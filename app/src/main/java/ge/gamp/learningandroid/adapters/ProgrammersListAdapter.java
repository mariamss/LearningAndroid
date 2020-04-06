package ge.gamp.learningandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ge.gamp.learningandroid.R;
import ge.gamp.learningandroid.data.model.Programmer;


// This class is responsible for "converting" Java class to list item.
public class ProgrammersListAdapter extends BaseAdapter {
    private List<Programmer> listData;
    private LayoutInflater layoutInflater;

    public ProgrammersListAdapter(Context context, List<Programmer> programmers){
        this.listData = programmers;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listData.get(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.programmers_list_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(R.id.txt_programmer_name);
            holder.occupation = (TextView) v.findViewById(R.id.txt_programmer_occupation);
            // Set newly added items from xml here

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(listData.get(position).getName());
        holder.occupation.setText(listData.get(position).getOccupation());
        // Only set value here

        return v;
    }


    // When you declare item in xml also add it here
    static class ViewHolder {
        TextView name;
        TextView occupation;
    }
}
