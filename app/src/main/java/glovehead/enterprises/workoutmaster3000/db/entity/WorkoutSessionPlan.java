package glovehead.enterprises.workoutmaster3000.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_session_plan")
public class WorkoutSessionPlan {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String type;

    private long duration;

    private String durationString;

    private Boolean isActive;

    public WorkoutSessionPlan(String name, String type, long duration, String durationString, boolean isActive) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.durationString = durationString;
        this.isActive = isActive;
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

    public Boolean getActive() {
        return isActive;
    }

    public String getDurationString() {
        return durationString;
    }

    public String getType() {
        return type;
    }
}
