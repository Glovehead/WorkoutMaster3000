package glovehead.enterprises.workoutmaster3000.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import glovehead.enterprises.workoutmaster3000.Constants;
import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;
import glovehead.enterprises.workoutmaster3000.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private Button startWorkoutBtn;
    private CardView activeWorkoutCV;
    private TextView activeWorkoutTitleTV;
    private TextView activeWorkoutTypeTV;
    private TextView activeWorkoutDurationTV;
    private CardView activeWorkoutOverlayCV;

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
                    activeWorkoutOverlayCV.setVisibility(View.VISIBLE);
                } else {
                    activeWorkoutOverlayCV.setVisibility(View.INVISIBLE);

                    activeWorkoutTitleTV.setText(viewModel.getActiveWorkoutSessionPlan().getValue().getName());
                    activeWorkoutTypeTV.setText(viewModel.getActiveWorkoutSessionPlan().getValue().getType());
                    activeWorkoutDurationTV.setText(viewModel.getActiveWorkoutSessionPlan().getValue().getDurationString());
                }
            }
        });
    }

    private void initViews() {
        startWorkoutBtn = findViewById(R.id.main_activity_start_workout_btn);
        activeWorkoutCV = findViewById(R.id.main_activity_active_workout_view);
        activeWorkoutOverlayCV = findViewById(R.id.main_activity_active_workout_overlay);
        activeWorkoutTitleTV = findViewById(R.id.card_workout_plan_title);
        activeWorkoutTypeTV = findViewById(R.id.card_workout_plan_type);
        activeWorkoutDurationTV = findViewById(R.id.card_plan_duration);

        activeWorkoutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkoutPlanSelectorActivity.class);
                startActivity(intent);
            }
        });

        startWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InProgressWorkoutActivity.class);
                intent.putExtra(Constants.EXTRA_WORKOUT_ID, viewModel.getActiveWorkoutSessionPlan().getValue().getId());

                startActivity(intent);
            }
        });
    }
}
