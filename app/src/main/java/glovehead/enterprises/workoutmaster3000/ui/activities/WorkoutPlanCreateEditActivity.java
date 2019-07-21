package glovehead.enterprises.workoutmaster3000.ui.activities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;
import glovehead.enterprises.workoutmaster3000.ui.adapters.WorkoutPlanElementsAdapter;
import glovehead.enterprises.workoutmaster3000.viewmodels.WorkoutPlanCreateEditViewModel;

public class WorkoutPlanCreateEditActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText workoutSessionTitleET;
    private WorkoutPlanCreateEditViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_create_edit);

        workoutSessionTitleET = findViewById(R.id.create_edit_workout_title_et);
        recyclerView = findViewById(R.id.create_edit_workout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        WorkoutPlanElementsAdapter adapter = new WorkoutPlanElementsAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(WorkoutPlanCreateEditViewModel.class);
        viewModel.getWorkoutSessionElements().observe(this, new Observer<List<WorkoutSessionElement>>() {
            @Override
            public void onChanged(List<WorkoutSessionElement> workoutSessionElements) {

            }
        });
    }
}
