package glovehead.enterprises.workoutmaster3000.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import glovehead.enterprises.workoutmaster3000.db.entity.ExerciseType;

@Dao
public interface ExerciseTypeDao {

    @Insert
    void insert(ExerciseType exerciseType);

    @Update
    void update(ExerciseType exerciseType);

    @Delete
    void delete(ExerciseType exerciseType);

    @Query("SELECT * FROM exercise_type WHERE id = :exerciseID")
    ExerciseType getExerciseType(int exerciseID);

    @Query("SELECT * FROM exercise_type")
    LiveData<List<ExerciseType>> getAllExerciseTypes();
}
