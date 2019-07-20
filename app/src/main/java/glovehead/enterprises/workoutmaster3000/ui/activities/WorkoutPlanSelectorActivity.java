package glovehead.enterprises.workoutmaster3000.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.viewmodels.WorkoutPlanSelectorViewModel;

public class WorkoutPlanSelectorActivity extends AppCompatActivity {

    private WorkoutPlanSelectorViewModel viewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_selector);

        initViews();

        viewModel = ViewModelProviders.of(this).get(WorkoutPlanSelectorViewModel.class);
    }

    private void initViews() {

        recyclerView = findViewById(R.id.workout_selector_activity_recycler_view);


        fab = findViewById(R.id.fab_add_workout_session);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutPlanSelectorActivity.this,
                        WorkoutPlanCreateEditActivity.class);
                startActivity(intent);
            }
        });

    }
}
