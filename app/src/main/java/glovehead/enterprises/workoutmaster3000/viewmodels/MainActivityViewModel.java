package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

public class MainActivityViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<WorkoutSessionPlan> activeWorkoutSessionPlan;
    private LiveData<List<ExerciseType>> exerciseTypes;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        activeWorkoutSessionPlan = repository.getActiveWorkoutSessionPlan();
        exerciseTypes = repository.getAllExerciseTypes();
    }

    public LiveData<WorkoutSessionPlan> getActiveWorkoutSessionPlan() {
        return activeWorkoutSessionPlan;
    }

    public LiveData<List<ExerciseType>> getExerciseTypes() {
        return exerciseTypes;
    }
}
