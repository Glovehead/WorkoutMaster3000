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

    private String exerciseType;

    private int position;

    private long duration;

    private String durationString;

    public WorkoutSessionElement(int workoutSessionID, int exerciseTypeID, String exerciseType, int position, long duration, String durationString) {
        this.workoutSessionID = workoutSessionID;
        this.exerciseTypeID = exerciseTypeID;
        this.exerciseType = exerciseType;
        this.position = position;
        this.duration = duration;
        this.durationString = durationString;
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

    public long getDuration() {
        return duration;
    }

    public String getDurationString() {
        return durationString;
    }

    public String getExerciseType() {
        return exerciseType;
    }
}
