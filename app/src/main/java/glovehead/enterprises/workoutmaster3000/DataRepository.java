package glovehead.enterprises.workoutmaster3000;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.db.AppDatabase;
import glovehead.enterprises.workoutmaster3000.db.dao.ExerciseTypeDao;
import glovehead.enterprises.workoutmaster3000.db.dao.WorkoutSessionElementDao;
import glovehead.enterprises.workoutmaster3000.db.dao.WorkoutSessionPlanDao;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

public class DataRepository {

    private ExerciseTypeDao exerciseTypeDao;
    private WorkoutSessionPlanDao workoutSessionPlanDao;
    private WorkoutSessionElementDao workoutSessionElementDao;

    private LiveData<List<WorkoutSessionPlan>> allWorkoutSessionPlans;

    public DataRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        exerciseTypeDao = db.exerciseTypeDao();
        workoutSessionPlanDao = db.workoutSessionPlanDao();
        workoutSessionElementDao = db.workoutSessionElementDao();

        allWorkoutSessionPlans = workoutSessionPlanDao.getAllWorkoutSessionPlans();
    }
}
