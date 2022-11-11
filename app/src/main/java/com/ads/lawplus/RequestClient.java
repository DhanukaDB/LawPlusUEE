package com.ads.lawplus;

/*
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
    EditText etVolunteerName, etVolunteerEmail, etmRequestBody;
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
        requestClientDetailsObj = new RequestClientDetails();

    }

    // Method to clear all user inputs
    public void ClearControls() {
        etVolunteerName.setText("");
        etVolunteerEmail.setText("");
        etmRequestBody.setText("");

    }


}

*/


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestClient extends AppCompatActivity {

    EditText etVolunteerName, etVolunteerEmail, etmRequestBody;
    Button btn_requestClientDetails;

    //create a reference for the  Request Client Details class
    RequestClientDetails requestClientDetailsObj;

    //define database reference

    FirebaseDatabase rootNode;
    DatabaseReference db;

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
        requestClientDetailsObj = new RequestClientDetails();






    }

    public void RequestClientDetails(View view) {

        rootNode = FirebaseDatabase.getInstance();
        db = rootNode.getReference("RequestClientDetails");


        try {
            if(TextUtils.isEmpty(etVolunteerName.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Volunteer Name",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(etVolunteerEmail.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Volunteer Email",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(etmRequestBody.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Type Request Message",Toast.LENGTH_LONG).show();
            } else {
                requestClientDetailsObj.setVolunteerName(etVolunteerName.getText().toString().trim());
                requestClientDetailsObj.setVolunteerEmail(etVolunteerEmail.getText().toString().trim());
                requestClientDetailsObj.setRequestBody(etmRequestBody.getText().toString().trim());

                String Name = etVolunteerName.getText().toString().trim();

                db.child(Name).setValue(requestClientDetailsObj);

                Toast.makeText(getApplicationContext(),"Message Send Successfully",Toast.LENGTH_LONG).show();

                startActivity(new Intent(RequestClient.this, VolunteerRequestsList.class));

                ClearControls();

            }


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
        }
    }

    public void ClearControls() {
        etVolunteerName.setText("");
        etVolunteerEmail.setText("");
        etmRequestBody.setText("");

    }

}


