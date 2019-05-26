package glovehead.enterprises.workoutmaster3000.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_session_element",
        foreignKeys = {
            @ForeignKey(entity = WorkoutSessionPlan.class,
                        parentColumns = "id",
                        childColumns = "workoutSessionID"),
            @ForeignKey(entity = ExerciseType.class,
                        parentColumns = "id",
                        childColumns = "exerciseTypeID")
        })
public class WorkoutSessionElement {

    @PrimaryKey(autoGenerate = true)
    private int elementID;

    private int workoutSessionID;

    private int exerciseTypeID;

    private int position;

    public WorkoutSessionElement(int workoutSessionID, int exerciseTypeID, int position) {
        this.workoutSessionID = workoutSessionID;
        this.exerciseTypeID = exerciseTypeID;
        this.position = position;
    }

    public void setElementID(int elementID) {
        this.elementID = elementID;
    }

    public int getElementID() {
        return elementID;
    }

    public int getWorkoutSessionID() {
        return workoutSessionID;
    }

    public int getExerciseTypeID() {
        return exerciseTypeID;
    }

    public int getPosition() {
        return position;
    }
}
