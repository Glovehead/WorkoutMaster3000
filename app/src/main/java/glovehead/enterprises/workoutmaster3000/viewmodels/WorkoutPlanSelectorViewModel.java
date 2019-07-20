package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

public class WorkoutPlanSelectorViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<WorkoutSessionPlan>> workoutSessionPlans;

    public WorkoutPlanSelectorViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        workoutSessionPlans = repository.getAllWorkoutSessionPlans();
    }

    public LiveData<List<WorkoutSessionPlan>> getWorkoutSessionPlans() {
        return workoutSessionPlans;
    }
}
