package glovehead.enterprises.workoutmaster3000.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_session_plan")
public class WorkoutSessionPlan {

    @PrimaryKey
    private int id;

    private String name;

    private String type;

    private long duration;

    private String durationString;

    private Boolean isActive;

    public WorkoutSessionPlan(int id, String name, String type, long duration, String durationString, boolean isActive) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.durationString = durationString;
        this.isActive = isActive;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
