package glovehead.enterprises.workoutmaster3000.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import glovehead.enterprises.workoutmaster3000.R;
import glovehead.enterprises.workoutmaster3000.db.entity.WorkoutSessionPlan;

public class WorkoutPlanSelectorAdapter extends RecyclerView.Adapter<WorkoutPlanSelectorAdapter.WorkoutPlanHolder> {
    private List<WorkoutSessionPlan> workoutSessionPlans = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public WorkoutPlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_workout_session_plan, parent, false);
        return new WorkoutPlanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanHolder holder, int position) {
        WorkoutSessionPlan currentPlan = workoutSessionPlans.get(position);
        if (currentPlan != null) {
            holder.workoutTotalTimeTV.setText(currentPlan.getDurationString());
            holder.workoutTypeTV.setText(currentPlan.getType());
            holder.workoutTitleTV.setText(currentPlan.getName());
        }
        else {
            holder.workoutTitleTV.setText("-");
            holder.workoutTypeTV.setText("-");
            holder.workoutTotalTimeTV.setText("--:--:--");
        }
    }

    @Override
    public int getItemCount() {
        return workoutSessionPlans.size();
    }

    public void setWorkoutSessionPlans(List<WorkoutSessionPlan> workoutSessionPlans) {
        this.workoutSessionPlans = workoutSessionPlans;
        notifyDataSetChanged();
    }

    class WorkoutPlanHolder extends RecyclerView.ViewHolder {
        private TextView workoutTypeTV;
        private TextView workoutTitleTV;
        private TextView workoutTotalTimeTV;

        public WorkoutPlanHolder(@NonNull View itemView) {
            super(itemView);
            workoutTitleTV = itemView.findViewById(R.id.card_workout_plan_title);
            workoutTypeTV = itemView.findViewById(R.id.card_workout_plan_type);
            workoutTotalTimeTV = itemView.findViewById(R.id.card_plan_duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(workoutSessionPlans.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(WorkoutSessionPlan workoutSessionPlan);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
