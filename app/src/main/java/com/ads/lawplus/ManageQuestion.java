package com.ads.lawplus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageQuestion extends AppCompatActivity {

    Button button;
    ListView listView;
    List<Questiondetails> user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_questions);

        button = (Button)findViewById(R.id.addDetails);
        listView = (ListView)findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageQuestion.this, Questions.class);
                startActivity(intent);
            }
        });

        user = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("Questions");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    Questiondetails Questiondetails = studentDatasnap.getValue(Questiondetails.class);
                    user.add(Questiondetails);
                }

                MyAdapter adapter = new MyAdapter(ManageQuestion.this, R.layout.custom_question_details, (ArrayList<Questiondetails>) user);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    static class ViewHolder {

        TextView COL1;
        TextView COL4;
        Button button1;
        Button button2;
    }

    class MyAdapter extends ArrayAdapter<Questiondetails> {
        LayoutInflater inflater;
        Context myContext;
        List<Questiondetails> user;


        public MyAdapter(Context context, int resource, ArrayList<Questiondetails> objects) {
            super(context, resource, objects);
            myContext = context;
            user = objects;
            inflater = LayoutInflater.from(context);
            int y;
            String barcode;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.custom_question_details, null);

                holder.COL1 = (TextView) view.findViewById(R.id.title);
                holder.COL4 = (TextView) view.findViewById(R.id.bodyText);
                holder.button1 = (Button) view.findViewById(R.id.deledit);
                holder.button2 = (Button) view.findViewById(R.id.deldelete);


                view.setTag(holder);
            } else {

                holder = (ViewHolder) view.getTag();
            }

            holder.COL1.setText("Title: "+user.get(position).getTitle());
            holder.COL4.setText("Body:- "+user.get(position).getBody());
            System.out.println(holder);

            holder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("Do you want to delete question?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    final String idd = user.get(position).getTitle();
                                    FirebaseDatabase.getInstance().getReference("Questions").child(idd).removeValue();
                                    //remove function not written
                                    Toast.makeText(myContext, "Question deleted successfully", Toast.LENGTH_SHORT).show();

                                }
                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
            });

            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    View view1 = inflater.inflate(R.layout.custom_update_question_details, null);
                    dialogBuilder.setView(view1);

                    final EditText editText1 = (EditText) view1.findViewById(R.id.title);
                    final EditText editText2 = (EditText) view1.findViewById(R.id.bodyText);
//                    final EditText editText3 = (EditText) view1.findViewById(R.id.TTEndLocation);
//                    final EditText editText4 = (EditText) view1.findViewById(R.id.TTtime);
//                    final EditText editText5 = (EditText) view1.findViewById(R.id.TTdriver);
                    final Button buttonupdate = (Button) view1.findViewById(R.id.update);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    final String idd = user.get(position).getTitle();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TimeDetails").child(idd);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String title = (String) snapshot.child("title").getValue();
                            String body = (String) snapshot.child("body").getValue();
                           String answer = (String) snapshot.child("answer").getValue();
//                            String time = (String) snapshot.child("time").getValue();
//                            String driver = (String) snapshot.child("driverName").getValue();

                            editText1.setText(title);
                            editText2.setText(body);
//                            editText3.setText(end);
//                            editText4.setText(time);
//                            editText5.setText(driver);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    buttonupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String title = editText1.getText().toString();
                            String body = editText2.getText().toString();
//                            String to = editText3.getText().toString();
//                            String time = editText4.getText().toString();
//                            String driver = editText5.getText().toString();

                           if (title.isEmpty()) {
                                editText1.setError("Bus no is required");
                            }else if (body.isEmpty()) {
                                editText2.setError("Start location is required");
//                            }else if (to.isEmpty()) {
//                                editText3.setError("End location is required");
//                            }else if (time.isEmpty()) {
//                                editText4.setError("Time is required");
//                            }else if (driver.isEmpty()) {
//                                editText5.setError("Driver name is required");
                            }else {

                                HashMap map = new HashMap();
                                map.put("title", title);
                                map.put("body", body);
//                                map.put("goTo", to);
//                               map.put("time", time);
//                               map.put("driverName", driver);
//                                reference.updateChildren(map);

                                Toast.makeText(ManageQuestion.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                                alertDialog.dismiss();
                            }
                        }
                    });
                }
            });

            return view;

        }
    }
}
