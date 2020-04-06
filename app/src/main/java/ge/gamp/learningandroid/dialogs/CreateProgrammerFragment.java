package ge.gamp.learningandroid.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ge.gamp.learningandroid.R;
import ge.gamp.learningandroid.data.model.Programmer;


public class CreateProgrammerFragment extends DialogFragment {
    public interface CreateProgrammerDialogListener {
        public void onCreateProgrammerClicked(Programmer programmer);
    }

    CreateProgrammerDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.create_programmer_dialog, null);
        EditText nameInput = view.findViewById(R.id.input_name);
        EditText occupationInput = view.findViewById(R.id.input_occupation);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = nameInput.getText().toString();
                        String occupation = nameInput.getText().toString();
                        Programmer newProgrammer = new Programmer();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CreateProgrammerFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
