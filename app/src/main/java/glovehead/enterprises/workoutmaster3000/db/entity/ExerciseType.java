package glovehead.enterprises.workoutmaster3000.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_type")
public class ExerciseType {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private double loCaloriesBurned;

    private double midCaloriesBurned;

    private double hiCaloriesBurned;

    public ExerciseType(String name, double loCaloriesBurned, double midCaloriesBurned, double hiCaloriesBurned) {
        this.name = name;
        this.loCaloriesBurned = loCaloriesBurned;
        this.midCaloriesBurned = midCaloriesBurned;
        this.hiCaloriesBurned = hiCaloriesBurned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLoCaloriesBurned() {
        return loCaloriesBurned;
    }

    public double getMidCaloriesBurned() {
        return midCaloriesBurned;
    }

    public double getHiCaloriesBurned() {
        return hiCaloriesBurned;
    }
}
