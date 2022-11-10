package com.ads.lawplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestClient extends AppCompatActivity {

    EditText et_volunteerName, et_volunteerEmail, etM_requestBody;
    Button btn_requestClientDetails;
    RequestClientDetails requestClientDetails;

    DatabaseReference database;
    FirebaseDatabase root;
    //User userob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestclientdetails);
        database = FirebaseDatabase.getInstance().getReference("User");

       // userob = new User();
        requestClientDetails = new  RequestClientDetails();

        et_volunteerName = findViewById(R.id.et_volunteerName);
        et_volunteerEmail = findViewById(R.id.et_volunteerEmail);
        etM_requestBody = findViewById(R.id.etm_requestBody);


        //button direction
        btn_requestClientDetails = findViewById(R.id.btn_requestClientDetails);
        btn_requestClientDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                startActivity(new Intent(RequestClient.this, Home.class));

                ClearControls();
            }
        });
    }
    public void Signup(View view) {

        root = FirebaseDatabase.getInstance();
        database = root.getReference("User");


        try {
            if(TextUtils.isEmpty( et_volunteerName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Name",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(et_volunteerEmail.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Email",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(etM_requestBody.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Type Requested Details",Toast.LENGTH_LONG).show();

            } else {
                requestClientDetails.setVolunteerName(et_volunteerName.getText().toString().trim());
                requestClientDetails.setVolunteerEmail(et_volunteerEmail.getText().toString().trim());
                requestClientDetails.setRequestBody(etM_requestBody.getText().toString().trim());


                Toast.makeText(getApplicationContext(),"Send Request to Manager",Toast.LENGTH_LONG).show();

                startActivity(new Intent(RequestClient.this, MainActivity.class));

                ClearControls();

            }


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
        }
    }
    public void ClearControls() {
        et_volunteerName.setText("");
        et_volunteerEmail.setText("");
        etM_requestBody.setText("");


    }
}
