package glovehead.enterprises.workoutmaster3000.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionElement;

public class WorkoutPlanElementsAdapter extends RecyclerView.Adapter<WorkoutPlanElementsAdapter.SessionElementHolder> {
    private List<WorkoutSessionElement> workoutSessionElements = new ArrayList<>();

    @NonNull
    @Override
    public SessionElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_workout_plan_element, parent, false);
        return new SessionElementHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionElementHolder holder, int position) {
        WorkoutSessionElement currentElement = workoutSessionElements.get(position);
        if (currentElement != null) {
            holder.elementType.setText(currentElement.getExerciseType());
            holder.elementDuration.setText(currentElement.getDurationString());
        } else {
            holder.elementType.setText("-");
            holder.elementDuration.setText("--:--");
        }

    }

    @Override
    public int getItemCount() {
        return workoutSessionElements != null ? workoutSessionElements.size() : 0;
    }

    public void setWorkoutSessionElements(List<WorkoutSessionElement> elements) {
        this.workoutSessionElements = elements;
        notifyDataSetChanged();
    }

    public void setWorkoutElements(List<WorkoutSessionElement> elements) {
        this.workoutSessionElements = elements;
        notifyDataSetChanged();
    }

    class SessionElementHolder extends RecyclerView.ViewHolder {

        private TextView elementType;
        private TextView elementDuration;

        public SessionElementHolder(@NonNull View itemView) {
            super(itemView);
            elementType = itemView.findViewById(R.id.card_workout_element_type);
            elementDuration = itemView.findViewById(R.id.card_workout_element_duration);
        }
    }
}
