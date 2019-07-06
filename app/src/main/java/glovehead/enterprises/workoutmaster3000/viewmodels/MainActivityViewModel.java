package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

public class MainActivityViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<WorkoutSessionPlan> activeWorkoutSessionPlan;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        activeWorkoutSessionPlan = repository.getActiveWorkoutSessionPlan();
    }

    public LiveData<WorkoutSessionPlan> getActiveWorkoutSessionPlan() {
        return activeWorkoutSessionPlan;
    }
}
