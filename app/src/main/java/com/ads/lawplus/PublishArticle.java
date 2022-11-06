package com.ads.lawplus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PublishArticle extends AppCompatActivity {
    EditText category,title,body,file;
    Button button2,button3;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisharticle);

        category = (EditText) findViewById(R.id.editTextTextPersonName2);
        title = (EditText) findViewById(R.id.editTextTextPersonName3);
        body = (EditText) findViewById(R.id.editTextTextPersonName5);
        file = (EditText) findViewById(R.id.editTextTextPersonName6);
//        btnAdd = (Button) findViewById(R.id.btnAdd);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    insertData();
                    clearAll();
                }
            }
        });
       button3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                finish();
           }
       });
    }
    //insert data to database
    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("category", category.getText().toString());
        map.put("title", title.getText().toString());
        map.put("body", body.getText().toString());
        map.put("file", file.getText().toString());

        //Integer.parseInt(qty.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Articles").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PublishArticle.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(PublishArticle.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private boolean CheckAllFields() {
        if (category.length() == 0) {
            category.setError("This field is required");
            return false;
        }
        if (title.length() == 0) {
            title.setError("This field is required");
            return false;
        }
        if (body.length() == 0) {
            body.setError("This field is required");
            return false;
        }
        if (file.length() == 0) {
            file.setError("This field is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
    private void clearAll() {
        category.setText("");
        title.setText("");
        body.setText("");
        file.setText("");

    }

}
