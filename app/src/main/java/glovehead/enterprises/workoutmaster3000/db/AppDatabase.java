package glovehead.enterprises.workoutmaster3000.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import glovehead.enterprises.workoutmaster3000.db.dao.ExerciseTypeDao;
import glovehead.enterprises.workoutmaster3000.db.dao.WorkoutSessionElementDao;
import glovehead.enterprises.workoutmaster3000.db.dao.WorkoutSessionPlanDao;
import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

@Database(entities = {ExerciseType.class, WorkoutSessionElement.class, WorkoutSessionPlan.class},
            version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";

    private static AppDatabase instance;

    public abstract ExerciseTypeDao exerciseTypeDao();

    public abstract WorkoutSessionElementDao workoutSessionElementDao();

    public abstract WorkoutSessionPlanDao workoutSessionPlanDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "getInstance: Instance creating");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "AppDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
            Log.d(TAG, "getInstance: Instance created");
        }

        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DataGenerator(instance).execute();
        }
    };

    private static class DataGenerator extends AsyncTask<Void, Void, Void> {

        private ExerciseTypeDao exerciseTypeDao;
        private static final String TAG = "DataGenerator";

        public DataGenerator(AppDatabase db) {
            exerciseTypeDao = db.exerciseTypeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: Data about to insert");
            // rest
            exerciseTypeDao.insert(new ExerciseType("Rest", 0, 0, 0));

            // walk
            // lo = 4kmh, mid = 4.8kmh, hi = 5.6kmh
            exerciseTypeDao.insert(new ExerciseType("Walk", 0.0667, 0.0889, 0.1 ));

            // run
            // lo: 1km in 8 min = 87 cal (0.18125 cal/s)
            // mid: 1km in 6.5 min = 77 cal (0.19745 cal/s)
            // hi: 1km in 5 min = 75 cal (0.25 cal/s)
            exerciseTypeDao.insert(new ExerciseType("Run", 0.18125, 0.19745, 0.25));

            // weights
            // lo: 90 cal per 30 minutes (0.05 cal/s)
            // mid: 110 cal per 30 minutes (0.061111 cal/s)
            // hi: 130 cal per 30 minutes (0.072222 cal/s)
            exerciseTypeDao.insert(new ExerciseType("Weights", 0.05, 0.061111, 0.061111));

            return null;
        }
    }
}
