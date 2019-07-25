package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;

public class WorkoutPlanCreateEditViewModel extends AndroidViewModel {

    private static final String TAG = "WorkoutPlanCreateEditViewModel";

    private DataRepository repository;
    private MutableLiveData<List<WorkoutSessionElement>> workoutSessionElements = new MutableLiveData<>();
    LiveData<List<ExerciseType>> exerciseTypes;
    private List<String> exerciseTypeStrings;
    private int workoutSessionID;

    public WorkoutPlanCreateEditViewModel(@NonNull Application application, int workoutSessionID) {
        super(application);
        this.workoutSessionID = workoutSessionID;
        repository = new DataRepository(application);

        // Super hacky, fix later
        workoutSessionElements.postValue(repository.getAllWorkoutSessionElements(workoutSessionID).getValue());

        exerciseTypes = repository.getAllExerciseTypes();
        for (ExerciseType exerciseType : exerciseTypes.getValue()) {
            exerciseTypeStrings.add(exerciseType.getName());
        }
    }

    public LiveData<List<WorkoutSessionElement>> getWorkoutSessionElements() {
        return workoutSessionElements;
    }

    public void setWorkoutSessionID(int workoutSessionID) {
        this.workoutSessionID = workoutSessionID;
    }

    public void saveWorkout() {
    }

    public List<String> getElementTypeStrings() {
        return null;
    }
}
