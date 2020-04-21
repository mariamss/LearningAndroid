package ge.gamp.learningandroid.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ge.gamp.learningandroid.R;
import ge.gamp.learningandroid.data.model.Programmer;
import ge.gamp.learningandroid.data.model.Validator;
import ge.gamp.learningandroid.utils.Utils;


public class CreateProgrammerFragment extends DialogFragment {

    private CreateProgrammerDialogListener listener ;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.create_programmer_dialog, null);
        EditText nameInput = view.findViewById(R.id.input_name);
        EditText occupationInput = view.findViewById(R.id.input_occupation);
        EditText ageInput =  view.findViewById(R.id.input_age);
        EditText salaryInput  = view.findViewById(R.id.input_salary);
        EditText bioInput =  view.findViewById(R.id.input_bio);
        // Inflate and set the layout for the dialog
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int uniqueId =Utils.generateRandomInt(10,10000);
                        String name = nameInput.getText().toString();
                        String occupation = occupationInput.getText().toString();
                        int age = Utils.parseStringToInt(ageInput.getText().toString());
                        int salary = Utils.parseStringToInt(salaryInput.getText().toString());
                        String bio = bioInput.getText().toString();
                        String icon = "https://vignette.wikia.nocookie.net/simpsons/images/0/02/Homer_Simpson_2006.png/revision/latest/top-crop/width/360/height/360?cb=20190409004803";
                        Validator validator = new Validator(name, occupation, age, salary);
                        Boolean isDataValid = validator.isProgrammerDataValid();
                        if(isDataValid){
                            Programmer newProgrammer = new Programmer(uniqueId, name, occupation, age, salary,bio, icon);
                            listener.onCreateProgrammerClicked(newProgrammer);
                        } else{
                            Toast.makeText(getActivity(), "Enter valid Data ", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CreateProgrammerFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public interface CreateProgrammerDialogListener {
        void onCreateProgrammerClicked(Programmer programmer);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CreateProgrammerDialogListener) context;
        } catch (ClassCastException e) {
            throw e;
        }
    }

}
