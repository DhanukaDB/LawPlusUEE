package com.ads.lawplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>{

    Context context;
    ArrayList<Feedbacks> list;

    public FeedbackAdapter(Context context, ArrayList<Feedbacks> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        return new FeedbackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        Feedbacks feedbacks = list.get(position);
        holder.name.setText(feedbacks.getName());
        holder.email.setText(feedbacks.getEmail());
        holder.feedback.setText(feedbacks.getFeedback());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder{

        TextView name, email, feedback;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fbName);
            email = itemView.findViewById(R.id.fbEmail);
            feedback = itemView.findViewById(R.id.fb);
        }
    }
}
