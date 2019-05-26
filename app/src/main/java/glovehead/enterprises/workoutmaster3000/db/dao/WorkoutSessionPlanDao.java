package glovehead.enterprises.workoutmaster3000.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

@Dao
public interface WorkoutSessionPlanDao {

    @Insert
    void insert(WorkoutSessionPlan plan);

    @Update
    void update(WorkoutSessionPlan plan);

    @Delete
    void delete(WorkoutSessionPlan plan);

    @Query("SELECT * FROM workout_session_plan")
    LiveData<List<WorkoutSessionPlan>> getAllWorkoutSessionPlans();
}
