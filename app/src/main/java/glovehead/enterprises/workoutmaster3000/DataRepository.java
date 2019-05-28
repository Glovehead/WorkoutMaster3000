package glovehead.enterprises.workoutmaster3000;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.db.AppDatabase;
import glovehead.enterprises.workoutmaster3000.db.dao.ExerciseTypeDao;
import glovehead.enterprises.workoutmaster3000.db.dao.WorkoutSessionElementDao;
import glovehead.enterprises.workoutmaster3000.db.dao.WorkoutSessionPlanDao;
import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;
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

    // WorkoutSessionPlans

    public LiveData<List<WorkoutSessionPlan>> getAllWorkoutSessionPlans() {
        return allWorkoutSessionPlans;
    }

    public void insertWorkoutSessionPlan(WorkoutSessionPlan plan) {
        new insertWorkoutSessionPlanAsyncTask(workoutSessionPlanDao).execute(plan);
    }

    public void updateWorkoutSessionPlan(WorkoutSessionPlan plan) {
        new updateWorkoutSessionPlanAsyncTask(workoutSessionPlanDao).execute(plan);
    }

    public void deleteWorkoutSessionPlan(WorkoutSessionPlan plan) {
        new deleteWorkoutSessionPlanAsyncTask(workoutSessionPlanDao).execute(plan);
    }

    // ExerciseTypes

    public List<ExerciseType> getAllExerciseTypes() {
        return exerciseTypeDao.getAllExerciseTypes();
    }

    public ExerciseType getExerciseType(int id) {
        return exerciseTypeDao.getExerciseType(id);
    }

    // WorkoutSessionElements

    public List<WorkoutSessionElement> getAllWorkoutSessionElements(int workoutSessionID) {
        return workoutSessionElementDao.getAllSessionElements(workoutSessionID);
    }

    public void insertWorkoutSessionElement(WorkoutSessionElement element) {
        new insertWorkoutSessionElementAsyncTask(workoutSessionElementDao).execute(element);
    }

    public void deleteWorkoutSessionElement(WorkoutSessionElement element) {
        new deleteWorkoutSessionElementAsyncTask(workoutSessionElementDao).execute(element);
    }

    public void deleteAllWorkoutSessionElements(int workoutSessionID) {
        new deleteAllWorkoutSessionElementsAsyncTask(workoutSessionElementDao).execute(workoutSessionID);
    }

    // AsyncTasks

    private static class insertWorkoutSessionPlanAsyncTask extends AsyncTask<WorkoutSessionPlan, Void, Void> {

        private WorkoutSessionPlanDao dao;

        insertWorkoutSessionPlanAsyncTask(WorkoutSessionPlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutSessionPlan... workoutSessionPlans) {
            dao.insert(workoutSessionPlans[0]);
            return null;
        }
    }

    private static class updateWorkoutSessionPlanAsyncTask extends AsyncTask<WorkoutSessionPlan, Void, Void> {

        WorkoutSessionPlanDao dao;

        updateWorkoutSessionPlanAsyncTask(WorkoutSessionPlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutSessionPlan... workoutSessionPlans) {
            dao.update(workoutSessionPlans[0]);

            return null;
        }
    }

    private static class deleteWorkoutSessionPlanAsyncTask extends AsyncTask<WorkoutSessionPlan, Void, Void> {

        private WorkoutSessionPlanDao dao;

        deleteWorkoutSessionPlanAsyncTask(WorkoutSessionPlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutSessionPlan... workoutSessionPlans) {
            dao.delete(workoutSessionPlans[0]);
            return null;
        }
    }

    private static class insertWorkoutSessionElementAsyncTask extends AsyncTask<WorkoutSessionElement, Void, Void> {

        private WorkoutSessionElementDao dao;

        insertWorkoutSessionElementAsyncTask(WorkoutSessionElementDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutSessionElement... workoutSessionElements) {
            dao.insert(workoutSessionElements[0]);
            return null;
        }
    }

    private static class deleteWorkoutSessionElementAsyncTask extends AsyncTask<WorkoutSessionElement, Void, Void> {

        private WorkoutSessionElementDao dao;

        deleteWorkoutSessionElementAsyncTask(WorkoutSessionElementDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(WorkoutSessionElement... workoutSessionElements) {
            dao.delete(workoutSessionElements[0]);
            return null;
        }
    }

    private static class deleteAllWorkoutSessionElementsAsyncTask extends AsyncTask<Integer, Void, Void> {

        private WorkoutSessionElementDao dao;

        deleteAllWorkoutSessionElementsAsyncTask(WorkoutSessionElementDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            dao.deleteAllWorkoutSessionElements(integers[0]);
            return null;
        }
    }
}
