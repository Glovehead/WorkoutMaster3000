package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

public class WorkoutPlanCreateEditViewModel extends AndroidViewModel {

    private static final String TAG = "CreateEditViewModel";

    private DataRepository repository;
    private WorkoutSessionPlan currentWorkoutSessionPlan;
    private MutableLiveData<List<WorkoutSessionElement>> workoutSessionElements = new MutableLiveData<>();
    private LiveData<List<ExerciseType>> exerciseTypes;
    private LiveData<List<String>> exerciseTypeStrings;
    private int workoutSessionID;

    public WorkoutPlanCreateEditViewModel(@NonNull Application application, int workoutSessionID) {
        super(application);
        this.workoutSessionID = workoutSessionID;
        repository = new DataRepository(application);

        this.workoutSessionID = workoutSessionID;
        Log.d(TAG, "WorkoutPlanCreateEditViewModel: Initial ID value: " + this.workoutSessionID);

        if (this.workoutSessionID == -1) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    int potentialID;
                    do {
                        potentialID = random.nextInt(32768);
                    } while (repository.getAllWorkoutSessionElements(potentialID).getValue() != null);
                    WorkoutPlanCreateEditViewModel.this.workoutSessionID = potentialID;
                }
            });
            workoutSessionElements.setValue(new ArrayList<>());
        } else {
            // Super hacky, fix later
            workoutSessionElements.postValue(repository.getAllWorkoutSessionElements(workoutSessionID).getValue());
            currentWorkoutSessionPlan = repository.getWorkoutSessionPlan(this.workoutSessionID).getValue();
        }

        exerciseTypes = repository.getAllExerciseTypes();
        exerciseTypeStrings = Transformations.map(exerciseTypes, (types) -> {
            List<String> exerciseTypeStrings = new ArrayList<>();
            for (ExerciseType type : types) {
                exerciseTypeStrings.add(type.getName());
            }
            return exerciseTypeStrings;
        });

    }

    public LiveData<List<WorkoutSessionElement>> getWorkoutSessionElements() {
        return workoutSessionElements;
    }

    public void setWorkoutSessionID(int workoutSessionID) {
        this.workoutSessionID = workoutSessionID;
    }

    public void addWorkoutElement(String type, int minutes, int seconds) {
        int typeID = getWorkoutTypeID(type);
        int position;
        if (workoutSessionElements.getValue() != null) {
            position = workoutSessionElements.getValue().size();
        } else {
            position = 0;
        }

        int duration = calculateElementDuration(minutes, seconds);
        String durationString = createElementDurationString(minutes, seconds);

        WorkoutSessionElement element = new WorkoutSessionElement(this.workoutSessionID, typeID, type, position, minutes, seconds, duration, durationString);
        List<WorkoutSessionElement> updatedValues = workoutSessionElements.getValue();
        updatedValues.add(element);
        workoutSessionElements.setValue(updatedValues);
    }

    public void editWorkoutElement(int selectionPosition, String type, int minutes, int seconds) {
        int typeID = getWorkoutTypeID(type);

        int duration = calculateElementDuration(minutes, seconds);
        String durationString = createElementDurationString(minutes, seconds);

        List<WorkoutSessionElement> updatedValues = workoutSessionElements.getValue();
        updatedValues.get(selectionPosition).setExerciseType(type);
        updatedValues.get(selectionPosition).setExerciseTypeID(typeID);
        updatedValues.get(selectionPosition).setDuration(duration);
        updatedValues.get(selectionPosition).setDurationString(durationString);
        updatedValues.get(selectionPosition).setMinutes(minutes);
        updatedValues.get(selectionPosition).setSeconds(seconds);

        workoutSessionElements.setValue(updatedValues);
    }

    public void saveWorkout(String title) {
        int duration = calculateWorkoutDuration();
        String durationString = calculateWorkoutDurationString(duration);
        if (currentWorkoutSessionPlan == null) {
            WorkoutSessionPlan workoutSessionPlan = new WorkoutSessionPlan(this.workoutSessionID, title, "Workout", duration, durationString, false);
            repository.insertWorkoutSessionPlan(workoutSessionPlan);
        } else {
            currentWorkoutSessionPlan.setDuration(duration);
            currentWorkoutSessionPlan.setDurationString(durationString);
            currentWorkoutSessionPlan.setName(title);
            repository.updateWorkoutSessionPlan(currentWorkoutSessionPlan);
        }
    }

    private int calculateElementDuration(int minutes, int seconds) {
        return (minutes * 60 * 1000) + (seconds * 1000);
    }

    private String createElementDurationString(int minutes, int seconds) {
        return minutes + ":" + seconds;
    }

    private int calculateWorkoutDuration() {
        int duration = 0;
        if (workoutSessionElements.getValue() != null) {
            for (WorkoutSessionElement element : workoutSessionElements.getValue()) {
                duration += element.getDuration();
            }
        }
        return duration;
    }

    private String calculateWorkoutDurationString(int duration) {
        int minutes = duration / 60000;
        int seconds = duration % 60000;
        return String.valueOf(minutes) + ":" + String.valueOf(seconds);
    }

    private int getWorkoutTypeID(String name) {
        if (exerciseTypes.getValue() == null) {
            return -1;
        }

        for (ExerciseType type : exerciseTypes.getValue()) {
            if (type.getName().equals(name)){
                return type.getId();
            }
        }

        return -1;
    }

    public LiveData<List<ExerciseType>> getExerciseTypes() {
        return exerciseTypes;
    }

    public LiveData<List<String>> getExerciseTypeStrings() {
        return exerciseTypeStrings;
    }

    public int getWorkoutSessionID() {
        return this.workoutSessionID;
    }
}
