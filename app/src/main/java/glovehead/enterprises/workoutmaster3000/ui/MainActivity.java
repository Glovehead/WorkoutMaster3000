package glovehead.enterprises.workoutmaster3000.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;
import glovehead.enterprises.workoutmaster3000.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final WorkoutPlanSelectorAdapter planSelectorAdapter = new WorkoutPlanSelectorAdapter();
        recyclerView.setAdapter(planSelectorAdapter);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getActiveWorkoutSessionPlan().observe(this, new Observer<WorkoutSessionPlan>() {
            @Override
            public void onChanged(WorkoutSessionPlan workoutSessionPlan) {
                // update recycler view
                List<WorkoutSessionPlan> activePlanList = new ArrayList<>();
                activePlanList.add(workoutSessionPlan);
                planSelectorAdapter.setWorkoutSessionPlans(activePlanList);
            }
        });
    }
}
