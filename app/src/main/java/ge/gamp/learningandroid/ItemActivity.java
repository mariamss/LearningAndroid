package ge.gamp.learningandroid;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import ge.gamp.learningandroid.data.model.Programmer;
import ge.gamp.learningandroid.data.repository.CombinedRepository;
import ge.gamp.learningandroid.data.repository.Repository;

public class ItemActivity extends AppCompatActivity {
    private Repository repository;
    private  Programmer programmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        repository = CombinedRepository.getInstance();
        Intent intent = getIntent();
        int programmerId = intent.getIntExtra("PROGRAMMER_ID", 0);
        repository.getProgrammer(programmerId, (currentProgrammer)->{
            this.programmer = currentProgrammer;
            updateProgrammerView();
        });

    }

    private void updateProgrammerView() {
        TextView name = findViewById(R.id.programmer_name);
        TextView occupation = findViewById(R.id.programmer_occupation);
        TextView age =  findViewById(R.id.programmer_age);
        TextView salary = findViewById(R.id.programmer_salary);
        TextView bio  = findViewById(R.id.programmer_bio);
        ImageView programmerIcon = (ImageView) findViewById(R.id.programmer_icon);
        String url = programmer.getIcon();;
        Glide.with(this).load(url)
           .override(300, 300).centerCrop().into(programmerIcon);
        name.setText(programmer.getName());
        occupation.setText(programmer.getOccupation());
        age.setText(String.valueOf(programmer.getAge()));
        salary.setText(String.valueOf(programmer.getSalary()));
        bio.setText(programmer.getBio());
    }

}
