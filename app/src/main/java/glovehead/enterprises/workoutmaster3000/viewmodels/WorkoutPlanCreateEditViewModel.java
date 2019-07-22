package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;

public class WorkoutPlanCreateEditViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<WorkoutSessionElement>> workoutSessionElements;
    private int workoutSessionID;

    public WorkoutPlanCreateEditViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
    }

    public LiveData<List<WorkoutSessionElement>> getWorkoutSessionElements() {
        return workoutSessionElements;
    }

    public void setWorkoutSessionID(int workoutSessionID) {
        this.workoutSessionID = workoutSessionID;
    }

    public void saveWorkout() {
    }
}
