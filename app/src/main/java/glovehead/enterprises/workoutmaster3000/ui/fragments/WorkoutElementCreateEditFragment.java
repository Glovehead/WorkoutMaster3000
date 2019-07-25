package glovehead.enterprises.workoutmaster3000.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import glovehead.enterprises.workoutmaster3000.Constants;
import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.viewmodels.WorkoutPlanCreateEditViewModel;

public class WorkoutElementCreateEditFragment extends DialogFragment {

    private WorkoutPlanCreateEditViewModel viewModel;
    private Spinner workoutTypeSpinner;
    private NumberPicker minutePicker;
    private NumberPicker secondPicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(WorkoutPlanCreateEditViewModel.class);

        Bundle bundle = getArguments();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_edit_workout_element, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle(bundle.getBoolean(Constants.EXTRA_ELEMENT_EDIT, false)? "Edit" : "Create")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        workoutTypeSpinner = view.findViewById(R.id.dialog_workout_element_type_spinner);
        minutePicker = view.findViewById(R.id.dialog_workout_element_minute_picker);
        secondPicker = view.findViewById(R.id.dialog_workout_element_second_picker);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, viewModel.getElementTypeStrings());
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        workoutTypeSpinner.setAdapter(spinnerAdapter);
        workoutTypeSpinner.setSelection(bundle.getInt(Constants.EXTRA_EXERCISE_TYPE_POSITION, 0));

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(bundle.getInt(Constants.EXTRA_ELEMENT_DURATION_MINUTES, 0));

        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);
        secondPicker.setValue(bundle.getInt(Constants.EXTRA_ELEMENT_DURATION_MINUTES, 0));

        return builder.create();
    }
}
