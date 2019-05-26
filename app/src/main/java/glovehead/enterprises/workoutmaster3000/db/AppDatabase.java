package glovehead.enterprises.workoutmaster3000.db;

import android.content.Context;

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

    private static AppDatabase instance;

    public abstract ExerciseTypeDao exerciseTypeDao();

    public abstract WorkoutSessionElementDao workoutSessionElementDao();

    public abstract WorkoutSessionPlanDao workoutSessionPlanDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "AppDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DataGenerator(instance).execute();
        }
    };
}
