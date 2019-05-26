package glovehead.enterprises.workoutmaster3000.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;

@Dao
public interface WorkoutSessionElementDao {

    @Insert
    void insert(WorkoutSessionElement element);

    @Delete
    void delete(WorkoutSessionElement element);

    @Query("SELECT * FROM workout_session_element WHERE workoutSessionID = :workoutSessionID ORDER BY position ASC")
    LiveData<List<WorkoutSessionElement>> getAllSessionElements(int workoutSessionID);
}
