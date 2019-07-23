package glovehead.enterprises.workoutmaster3000.viewmodels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import glovehead.enterprises.workoutmaster3000.viewmodels.WorkoutPlanCreateEditViewModel;

public class WorkoutPlanCreateEditModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private Application application;
    private int extra;

    public WorkoutPlanCreateEditModelFactory(@NonNull Application application, int extra) {
        super(application);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WorkoutPlanCreateEditViewModel(this.application, this.extra);
    }
}
