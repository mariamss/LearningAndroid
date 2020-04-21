package ge.gamp.learningandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


import ge.gamp.learningandroid.R;
import ge.gamp.learningandroid.data.model.Programmer;

// This class is responsible for "converting" Java class to list item.
public class ProgrammersListAdapter extends BaseAdapter {
    private List<Programmer> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private ProgrammersListAdapterListener listener;

    public ProgrammersListAdapter(Context context, List<Programmer> programmers){
        this.listData = programmers;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.listener = (ProgrammersListAdapterListener) context;

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
            holder.age = (TextView) v.findViewById(R.id.txt_programmer_age);
            holder.salary = (TextView) v.findViewById(R.id.txt_programmer_salary);
            holder.deleteButton  = (Button) v.findViewById(R.id.delete_btn);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        Programmer currentProgrammer = listData.get(position);
        int programmerId = currentProgrammer.getId();
        holder.name.setText(currentProgrammer.getName());
        holder.occupation.setText(currentProgrammer.getOccupation());
        holder.age.setText(String.valueOf(currentProgrammer.getAge()));
        holder.salary.setText(String.valueOf(currentProgrammer.getSalary()));
        holder.deleteButton.setOnClickListener((View clickedView) -> {
            listener.onDeleteProgrammerClicked(programmerId);
        });
        v.setOnClickListener((View clickedView) -> {
            listener.onNavigateToItem(programmerId);
        });
        return v;
    }

    static class ViewHolder {
        TextView name;
        TextView occupation;
        TextView age;
        TextView salary;
        Button deleteButton;

    }
    public interface ProgrammersListAdapterListener {
        void onDeleteProgrammerClicked(int programmerId);
        void onNavigateToItem(int programmerId);
    }

}
