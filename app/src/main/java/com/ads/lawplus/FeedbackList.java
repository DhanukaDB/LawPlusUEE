package com.ads.lawplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedbackList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    FeedbackAdapter feedbackAdapter;
    ArrayList<Feedbacks> feedbackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        recyclerView = findViewById(R.id.feedbackList);
        database = FirebaseDatabase.getInstance().getReference("Feedbacks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedbackList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(this, feedbackList);
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

    }
}