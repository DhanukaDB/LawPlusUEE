package com.ads.lawplus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedback extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name,email, feedback;
    Button btn_feedback;
    Feedbacks fb;

    DatabaseReference database;
    FirebaseDatabase root;

    Feedbacks feedbackObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        database = FirebaseDatabase.getInstance().getReference("Feedbacks");

        feedbackObj = new Feedbacks();

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        feedback = findViewById(R.id.editTextFeedback);
        btn_feedback = findViewById(R.id.buttonFeedback);

        fb = new Feedbacks();

    }

    public void AddFeedback(View view) {

        database = FirebaseDatabase.getInstance().getReference().child("Feedbacks");

        try {
            if(TextUtils.isEmpty(name.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Select Name",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(email.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Email",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(feedback.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Type your Feedback",Toast.LENGTH_LONG).show();
            }
            else {

                fb.setName(name.getText().toString().trim());
                fb.setEmail(email.getText().toString().trim());
                fb.setFeedback(feedback.getText().toString().trim());
                String Name = name.getText().toString().trim();

                database.child(Name).setValue(fb);

                Toast.makeText(getApplicationContext(),"Feedback Added",Toast.LENGTH_LONG).show();

                startActivity(new Intent(AddFeedback.this, FeedbackSuccess.class));

                ClearControls();
            }


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
        }

    }
    public void ClearControls() {
        name.setText("");
        email.setText("");
        feedback.setText("");

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}