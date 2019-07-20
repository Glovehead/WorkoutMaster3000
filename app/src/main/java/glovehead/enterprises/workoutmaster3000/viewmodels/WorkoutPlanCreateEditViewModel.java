package glovehead.enterprises.workoutmaster3000.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.DataRepository;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;

public class WorkoutPlanCreateEditViewModel extends AndroidViewModel {

    DataRepository repository;
    LiveData<List<WorkoutSessionElement>> workoutSessionElements;

    public WorkoutPlanCreateEditViewModel(@NonNull Application application) {
        super(application);
    }
}
