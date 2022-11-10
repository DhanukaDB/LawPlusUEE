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

    //declare the all the UI elements
     EditText etVolunteerName,etVolunteerEmail,etmRequestBody;
     Button btn_requestClientDetails;


    //create a reference for the  Request Client Details class
    RequestClientDetails requestClientDetailsObj;

    //define database reference
    DatabaseReference dbRef;
    FirebaseDatabase root;

    //declare variable for auto increment
    //long max_id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestclientdetails);

        //find all the UI elements from the interface

        etVolunteerName = findViewById(R.id.et_volunteerName);
        etVolunteerEmail = findViewById(R.id.et_volunteerEmail);
        etmRequestBody = findViewById(R.id.etm_requestBody);


        btn_requestClientDetails = findViewById(R.id.btn_requestClientDetails);



        //Create an object
        requestClientDetailsObj = new  RequestClientDetails();

    }

    // Method to clear all user inputs
    public void ClearControls() {
        etVolunteerName.setText("");
        etVolunteerEmail.setText("");
        etmRequestBody.setText("");

    }

   /* //Set on click listener to the Continue button and start coding to insert
    public void onClick(View view) {
        //define a table name using a child method
        root = FirebaseDatabase.getInstance();
        dbRef = root.getReference().child("RequestClientDetails");


        //Check some validation whether the fields are empty or not
        if (TextUtils.isEmpty(etVolunteerName.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etVolunteerEmail.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please enter an Email Address", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(etmRequestBody.getText().toString()))
            Toast.makeText(getApplicationContext(), "Please Type Message", Toast.LENGTH_SHORT).show();
        else {

            //Take inputs from the user and assign these values to the request Client Details object
            requestClientDetailsObj.setVolunteerName(etVolunteerName.getText().toString().trim());
            requestClientDetailsObj.setVolunteerEmail(etVolunteerEmail.getText().toString().trim());
            requestClientDetailsObj.setRequestBody(etmRequestBody.getText().toString().trim());

            String V_Name = etVolunteerName.getText().toString().trim();
            String V_Email = etVolunteerEmail.getText().toString().trim();
            String V_Request = etmRequestBody.getText().toString().trim();


            //pass the values to the database
            dbRef.child(V_Email).setValue(requestClientDetailsObj);

            //Feedback to the user via Toast message
            Toast.makeText(getApplicationContext(), "Address saved Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),Home.class);

            intent.putExtra("volunteerName", V_Name);
            intent.putExtra("volunteerEmail",V_Email);
            intent.putExtra("requestBody",V_Request);

            Toast.makeText(getApplicationContext()," Successfully",Toast.LENGTH_LONG).show();

            startActivity(intent);


            ClearControls();
        }



    }*/

    public void RequestClient(View view) {

        root = FirebaseDatabase.getInstance();
        dbRef = root.getReference("RequestClientDetails");
       // rootNode = FirebaseDatabase.getInstance();
       // db = rootNode.getReference("User");


        try {
            if(TextUtils.isEmpty(etVolunteerName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Name",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(etVolunteerEmail.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Email",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(etmRequestBody.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Type your Request",Toast.LENGTH_LONG).show();
            } else {
                requestClientDetailsObj.setVolunteerName(etVolunteerName.getText().toString().trim());
                requestClientDetailsObj.setVolunteerEmail(etVolunteerEmail.getText().toString().trim());
                requestClientDetailsObj.setRequestBody(etmRequestBody.getText().toString().trim());

                String volunteerEmail = etVolunteerEmail.getText().toString().trim();

                dbRef.child(volunteerEmail ).setValue(requestClientDetailsObj);

                Toast.makeText(getApplicationContext(),"Request Send Successfully",Toast.LENGTH_LONG).show();

                startActivity(new Intent(RequestClient.this, Home.class));

                ClearControls();

            }


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
        }
    }

}
