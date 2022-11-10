package com.ads.lawplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FeedbackList extends AppCompatActivity implements FeedbackRecyclerViewInterface{

    RecyclerView recyclerView;
    DatabaseReference database;
    FeedbackAdapter feedbackAdapter;
    ArrayList<Feedbacks> feedbackList;
    FloatingActionButton feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        recyclerView = findViewById(R.id.feedbackList);
        database = FirebaseDatabase.getInstance().getReference("Feedbacks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedbackList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(this, feedbackList, this);
        recyclerView.setAdapter(feedbackAdapter);

        database.addValueEventListener(new ValueEventListener(){
           @Override
           public void  onDataChange(@NonNull DataSnapshot snapshot){
               for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   Feedbacks feedbacks = dataSnapshot.getValue(Feedbacks.class);
                   feedbackList.add(feedbacks);
               }
               feedbackAdapter.notifyDataSetChanged();
           }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Add feedback button navigation
        feedback = findViewById(R.id.floatingActionButton);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FeedbackList.this, AddFeedback.class));
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(FeedbackList.this, Feedback.class);

        intent.putExtra("NAME", feedbackList.get(position).getName());
        intent.putExtra("EMAIL", feedbackList.get(position).getEmail());
        intent.putExtra("FEEDBACK", feedbackList.get(position).getFeedback());

        startActivity(intent);
    }
}