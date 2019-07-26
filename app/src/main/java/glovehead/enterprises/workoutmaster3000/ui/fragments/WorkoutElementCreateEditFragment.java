package glovehead.enterprises.workoutmaster3000.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.Constants;
import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;
import glovehead.enterprises.workoutmaster3000.viewmodels.WorkoutPlanCreateEditViewModel;

public class WorkoutElementCreateEditFragment extends DialogFragment {

    private static final String TAG = "CreateEditNotif";

    private WorkoutPlanCreateEditViewModel viewModel;
    private Spinner workoutTypeSpinner;
    private NumberPicker minutePicker;
    private NumberPicker secondPicker;
    private boolean editing;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(WorkoutPlanCreateEditViewModel.class);

        String title = "Create";
        int selectionPosition = 0;
        int minuteValue = 0;
        int secondValue = 0;

        Bundle bundle = getArguments();
        if (bundle != null) {
            editing = true;
            title = "Edit";
            selectionPosition = bundle.getInt(Constants.EXTRA_EXERCISE_TYPE_POSITION);
            minuteValue = bundle.getInt(Constants.EXTRA_ELEMENT_DURATION_MINUTES);
            secondValue = bundle.getInt(Constants.EXTRA_ELEMENT_DURATION_SECONDS);
        } else {
            editing = false;
        }

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_edit_workout_element, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        int finalSelectionPosition = selectionPosition;
        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String type = workoutTypeSpinner.getSelectedItem().toString();
                        int minutes = minutePicker.getValue();
                        int seconds = secondPicker.getValue();

                        if (minutes == 0 && seconds == 0) {
                            Toast.makeText(getActivity(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (editing) {
                            viewModel.editWorkoutElement(finalSelectionPosition, type, minutes, seconds);
                        } else {
                            viewModel.addWorkoutElement(type, minutes, seconds);
                        }
                    }
                });

        workoutTypeSpinner = view.findViewById(R.id.dialog_workout_element_type_spinner);
        minutePicker = view.findViewById(R.id.dialog_workout_element_minute_picker);
        secondPicker = view.findViewById(R.id.dialog_workout_element_second_picker);

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        workoutTypeSpinner.setAdapter(spinnerAdapter);

        viewModel.getExerciseTypes().observe(this, exerciseTypes -> {
            for (ExerciseType type : exerciseTypes) {
                Log.d("CreateEditDialog", "onCreateDialog: " + type.getName());
            }
        });

        viewModel.getExerciseTypeStrings().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                for (String string : strings) {
                    spinnerAdapter.add(string);
                }
                spinnerAdapter.notifyDataSetChanged();
                workoutTypeSpinner.setSelection(finalSelectionPosition);
            }
        });

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(minuteValue);

        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);
        secondPicker.setValue(secondValue);

        return builder.create();
    }
}
