package glovehead.enterprises.workoutmaster3000.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "workout_session_plan")
public class WorkoutSessionPlan {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private long duration;

    public WorkoutSessionPlan(String name, long duration) {
        this.name = name;
        this.duration = duration;
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

    public long getDuration() {
        return duration;
    }
}
