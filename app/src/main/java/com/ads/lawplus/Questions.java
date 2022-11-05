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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Questions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText Username;
    Spinner spinner3,spinner2,spinner1,spinner;
    Button btn_pr_bk,getdateBtn,getTime;
//    Bookdetails bkdob;
    TextView editTextDate,editTextTime;

//    DatabaseReference database;
//    FirebaseDatabase root;

    User userob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

//        database = FirebaseDatabase.getInstance().getReference("User");

//        userob = new User();

        spinner3 = findViewById(R.id.spinner3);
        spinner1 = findViewById(R.id.spinner1);
        Username = findViewById(R.id.Username);
        spinner = findViewById(R.id.spinner1);

        btn_pr_bk = findViewById(R.id.btn_pr_bk);

//        bkdob = new Bookdetails();


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


//        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
//
//        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
//                R.array.to_array, android.R.layout.simple_spinner_item);
//
//        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner2.setAdapter(adapter4);
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.qty_array, android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



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

//    public void Proceed(View view) {
//
//        database = FirebaseDatabase.getInstance().getReference().child("Bookings");
//
//        try {
//            if(TextUtils.isEmpty(spinner3.getSelectedItem().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter Your Name",Toast.LENGTH_LONG).show();
//            } else if (TextUtils.isEmpty(spinner2.getSelectedItem().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter Your Email",Toast.LENGTH_LONG).show();
//            } else if (TextUtils.isEmpty(editTextDate.getText().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter Your Mobile Number",Toast.LENGTH_LONG).show();
//            } else if (TextUtils.isEmpty(editTextTime.getText().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
//            } else if (TextUtils.isEmpty(spinner1.getSelectedItem().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
//            }else if (TextUtils.isEmpty(Username.getText().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter Your Username",Toast.LENGTH_LONG).show();
//            }else if (TextUtils.isEmpty(spinner.getSelectedItem().toString().trim())){
//                Toast.makeText(getApplicationContext(),"Enter a Password",Toast.LENGTH_LONG).show();
//            } else {
//
//
//                bkdob.setFrom(spinner3.getSelectedItem().toString().trim());
//                bkdob.setTo(spinner2.getSelectedItem().toString().trim());
//                bkdob.setDate(editTextDate.getText().toString().trim());
//                bkdob.setTime(editTextTime.getText().toString().trim());
//                bkdob.setCategory(spinner1.getSelectedItem().toString().trim());
//                bkdob.setEmail(Username.getText().toString().trim());
//                bkdob.setQty(spinner.getSelectedItem().toString().trim());
//
//                String Email = Username.getText().toString().trim();
//                String From = spinner3.getSelectedItem().toString().trim();
//                String Date = editTextDate.getText().toString().trim();
//                String Time = editTextTime.getText().toString().trim();
//                String To = spinner2.getSelectedItem().toString().trim();
//                String Category = spinner1.getSelectedItem().toString().trim();
//                String Qty = spinner.getSelectedItem().toString().trim();
//
//                database.child(Email).setValue(bkdob);
//
//                Toast.makeText(getApplicationContext(),"Booking Success",Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(getApplicationContext(), BookingSuccess.class);
//
//                intent.putExtra("email",Email);
//                intent.putExtra("from",From);
//                intent.putExtra("to",Date);
//                intent.putExtra("date",Time);
//                intent.putExtra("time",To);
//                intent.putExtra("category",Category);
//                intent.putExtra("qty",Qty);
//
//
//                startActivity(intent);
//
//                ClearControls();
//            }
//
//
//        } catch (NumberFormatException e) {
//            Toast.makeText(getApplicationContext(), "Number Format Exception", Toast.LENGTH_LONG).show();
//        }
//
//
//
//    }
    public void ClearControls() {
        editTextDate.setText("");
        editTextTime.setText("");
        Username.setText("");


    }

}