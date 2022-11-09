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

    private final FeedbackRecyclerViewInterface feedbackRecyclerViewInterface;

    Context context;
    ArrayList<Feedbacks> list;

    public FeedbackAdapter(Context context, ArrayList<Feedbacks> list, FeedbackRecyclerViewInterface feedbackRecyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.feedbackRecyclerViewInterface = feedbackRecyclerViewInterface;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedback_item, parent, false);
        return new FeedbackViewHolder(v, feedbackRecyclerViewInterface);
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

        public FeedbackViewHolder(@NonNull View itemView, FeedbackRecyclerViewInterface feedbackRecyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.fbName);
            email = itemView.findViewById(R.id.fbEmail);
            feedback = itemView.findViewById(R.id.fb);

            //Attach onClick listener to feedback item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(feedbackRecyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            feedbackRecyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
