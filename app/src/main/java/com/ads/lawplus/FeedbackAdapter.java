package com.ads.lawplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder>{
    Context context;
    ArrayList<Feedback> list;

    public FeedbackAdapter(Context context, ArrayList<Feedback> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedbackentry, parent, false);
        return new ViewHolder();
    }

    @Override
    public void onBindViewHolder(@NonNull FbAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
