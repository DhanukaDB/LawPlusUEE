package com.ads.lawplus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Questions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText title,bodyText;
    Spinner spinner3,spinner1;
    Button btn_pr_bk;
    Questiondetails qdet;
    TextView editTextDate,editTextTime;
    CheckBox anonymously;

    DatabaseReference database;
    FirebaseDatabase root;

    User userob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        database = FirebaseDatabase.getInstance().getReference("User");

        userob = new User();

        spinner3 = findViewById(R.id.spinner3);
        spinner1 = findViewById(R.id.spinner1);
        title = findViewById(R.id.title);
        anonymously = findViewById(R.id.anonymously);
        bodyText = findViewById(R.id.bodyText);



        btn_pr_bk = findViewById(R.id.btn_pr_bk);

        qdet = new Questiondetails();


        //dropdown buttons

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.question_area, android.R.layout.simple_spinner_item);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner3.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.contact_option, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Proceed(View view) {

        database = FirebaseDatabase.getInstance().getReference().child("Questions");

        try {
            if(TextUtils.isEmpty(spinner3.getSelectedItem().toString().trim())){
                Toast.makeText(getApplicationContext(),"Select Type",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(title.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Mobile Number",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(bodyText.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(spinner1.getSelectedItem().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter Your Email",Toast.LENGTH_LONG).show();
           }
//            else if (TextUtils.isEmpty(anonymously.getText().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
//            }
            else {


                qdet.setArea(spinner3.getSelectedItem().toString().trim());
                qdet.setTitle(title.getText().toString().trim());
                qdet.setBody(bodyText.getText().toString().trim());
                qdet.setContactOption(spinner1.getSelectedItem().toString().trim());
                String Title = title.getText().toString().trim();
                qdet.setAnswer("Not Answered");
                qdet.setUsername("");


//                String Area = spinner3.getSelectedItem().toString().trim();
//                String Title = title.getText().toString().trim();
//                String Body = bodyText.getText().toString().trim();
//                String ContactOption = spinner1.getSelectedItem().toString().trim();
//


                database.child(Title).setValue(qdet);

                Toast.makeText(getApplicationContext(),"Question Added",Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(getApplicationContext(), Home.class);
//
//                intent.putExtra("spinner3",Area);
//                intent.putExtra("title",Title);
//                intent.putExtra("bodyText",Body);
//                intent.putExtra("spinner1",ContactOption);
//
//
//                startActivity(intent);

                startActivity(new Intent(Questions.this, ManageQuestion.class));

                ClearControls();
            }


        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
        }



    }
    public void ClearControls() {
        bodyText.setText("");
        title.setText("");


    }

}