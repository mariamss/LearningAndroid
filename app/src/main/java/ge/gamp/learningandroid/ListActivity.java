package ge.gamp.learningandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ge.gamp.learningandroid.adapters.ProgrammersListAdapter;
import ge.gamp.learningandroid.data.model.Programmer;
import ge.gamp.learningandroid.data.repository.CombinedRepository;
import ge.gamp.learningandroid.data.repository.Repository;
import ge.gamp.learningandroid.dialogs.CreateProgrammerFragment;

public class ListActivity extends AppCompatActivity implements CreateProgrammerFragment.CreateProgrammerDialogListener, ProgrammersListAdapter.ProgrammersListAdapterListener {
    private Repository repository;

    private final List<Programmer> programmers = new ArrayList<>(Arrays.asList());

    private ListView listView;
    private ProgressBar progressBar;

    private ProgrammersListAdapter programmersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        repository = CombinedRepository.getInstance();
        // It's important not to change reference of the list provided here. That's why it's final!
        programmersListAdapter = new ProgrammersListAdapter(this, programmers);
        listView.setAdapter(programmersListAdapter);
        refreshProgrammers();
    }


    void refreshProgrammers(){
        progressBar.setVisibility(View.VISIBLE);
        this.programmers.clear();
        // Without notifyDataSetChanged - Listview won't know that there's been update to it's data
        this.programmersListAdapter.notifyDataSetChanged();
        repository.getProgrammers(programmers -> {
            this.programmers.addAll(programmers);
            this.programmersListAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });
    }


    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void loadProgrammers(View view){
        refreshProgrammers();
    }

    public void createProgrammer(View view){
        FragmentManager fm = getSupportFragmentManager();
        CreateProgrammerFragment dialog = new CreateProgrammerFragment();
        dialog.show(fm, null);

    }

    @Override
    public void onCreateProgrammerClicked(Programmer newProgrammer) {
        repository.createProgrammer(newProgrammer, (createdProgrammer)->{
            this.programmers.add(newProgrammer);
            this.programmersListAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onDeleteProgrammerClicked(int programmerId) {
        progressBar.setVisibility(View.VISIBLE);
        repository.deleteProgrammer(programmerId, (result)->{
            this.programmers.removeIf(programmer -> programmer.getId() == programmerId);
            this.programmersListAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void onNavigateToItem(int programmerId) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("PROGRAMMER_ID", programmerId);
        startActivity(intent);
    }
}
