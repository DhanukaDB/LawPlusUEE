package com.ads.lawplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PublishArticle extends AppCompatActivity {
    Spinner category;
    EditText title;
    EditText body;
    EditText file;
    Button button2,button3;
    Spinner spinner;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisharticle);

        category = (Spinner) findViewById(R.id.spinner);
        title = (EditText) findViewById(R.id.editTextTextPersonName3);
        body = (EditText) findViewById(R.id.editTextTextPersonName5);
        file = (EditText) findViewById(R.id.editTextTextPersonName6);
//        btnAdd = (Button) findViewById(R.id.btnAdd);
        button2 = (Button) findViewById(R.id.buttonFeedback);
        button3 = (Button) findViewById(R.id.button3);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.question_area, android.R.layout.simple_spinner_item);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        map.put("category", category.getSelectedItem().toString());
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
                        startActivity(new Intent(PublishArticle.this, ArticleList.class));
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
//        if (category.length() == 0) {
//            category.setError("This field is required");
//            return false;
//        }
        if (title.length() == 0) {
            title.setError("This field is required");
            return false;
        }
        if (body.length() == 0) {
            body.setError("This field is required");
            return false;
        }
//        if (file.length() == 0) {
//            file.setError("This field is required");
//            return false;
//        }

        // after all validation return true.
        return true;
    }
    private void clearAll() {

        title.setText("");
        body.setText("");
        file.setText("");

    }

}
