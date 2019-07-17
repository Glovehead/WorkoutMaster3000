package glovehead.enterprises.workoutmaster3000.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;
import glovehead.enterprises.workoutmaster3000.ui.adapters.WorkoutPlanSelectorAdapter;
import glovehead.enterprises.workoutmaster3000.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private Button startWorkoutBtn;
    private CardView activeWorkoutCV;
    private CardView activeWorkoutOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getActiveWorkoutSessionPlan().observe(this, new Observer<WorkoutSessionPlan>() {
            @Override
            public void onChanged(WorkoutSessionPlan workoutSessionPlan) {
                // if no active workout show overlay, otherwise show workout
                if (workoutSessionPlan == null) {
                    activeWorkoutOverlay.setVisibility(View.VISIBLE);
                } else {
                    activeWorkoutOverlay.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initViews() {
        startWorkoutBtn = findViewById(R.id.main_activity_start_workout_btn);
        activeWorkoutCV = findViewById(R.id.main_activity_active_workout_view);
        activeWorkoutOverlay = findViewById(R.id.main_activity_active_workout_overlay);

        activeWorkoutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkoutPlanSelectorActivity.class);
                startActivity(intent);
            }
        });
    }
}
