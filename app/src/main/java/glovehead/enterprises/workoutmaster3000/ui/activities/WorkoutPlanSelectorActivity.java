package glovehead.enterprises.workoutmaster3000.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.Constants;
import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;
import glovehead.enterprises.workoutmaster3000.ui.adapters.WorkoutPlanSelectorAdapter;
import glovehead.enterprises.workoutmaster3000.viewmodels.WorkoutPlanSelectorViewModel;

public class WorkoutPlanSelectorActivity extends AppCompatActivity {

    private WorkoutPlanSelectorViewModel viewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_selector);

        fab = findViewById(R.id.fab_add_workout_session);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutPlanSelectorActivity.this,
                        WorkoutPlanCreateEditActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.workout_selector_activity_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final WorkoutPlanSelectorAdapter adapter = new WorkoutPlanSelectorAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(WorkoutPlanSelectorViewModel.class);
        viewModel.getWorkoutSessionPlans().observe(this, new Observer<List<WorkoutSessionPlan>>() {
            @Override
            public void onChanged(List<WorkoutSessionPlan> workoutSessionPlans) {
                adapter.setWorkoutSessionPlans(workoutSessionPlans);
            }
        });

        adapter.setOnItemClickListener(new WorkoutPlanSelectorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WorkoutSessionPlan workoutSessionPlan) {
                Intent intent = new Intent(WorkoutPlanSelectorActivity.this, WorkoutPlanCreateEditActivity.class);
                intent.putExtra(Constants.EXTRA_WORKOUT_ID, workoutSessionPlan.getId());
                startActivity(intent);
            }
        });
    }

}
