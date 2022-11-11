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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.lawplus.ManageQuestion.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VolunteerRequestsList extends AppCompatActivity {
    Button button;
    ListView listView;
    List<RequestClientDetails> user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);


        listView = (ListView) findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VolunteerRequestsList.this, RequestClient.class);
                startActivity(intent);
            }
        });

        user = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("RequestClientDetails");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    RequestClientDetails RequestClientDetails = studentDatasnap.getValue(RequestClientDetails.class);
                    user.add(RequestClientDetails);
                }

                //VolunteerRequestsList.MyRequestAdapter adapter = new VolunteerRequestsList().MyRequestAdapter(VolunteerRequestsList.this, R.layout.activity_volunteer_request, (ArrayList<RequestClientDetails>) user);
                //listView.setAdapter(adapter);
                MyAdapter adapter = new MyAdapter(VolunteerRequestsList.this, R.layout.activity_volunteer_request, (ArrayList<RequestClientDetails>) user);
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
//        TextView COL5;
        Button button1;
        Button button2;
    }


    class MyAdapter extends ArrayAdapter<RequestClientDetails> {
        LayoutInflater inflater;
        Context myContext;
        List<RequestClientDetails> user;

        public MyAdapter(Context context, int resource, ArrayList<RequestClientDetails> objects) {
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
            final VolunteerRequestsList.ViewHolder holder;
            if (view == null) {
                holder = new VolunteerRequestsList.ViewHolder();
                view = inflater.inflate(R.layout.activity_volunteer_requests_list, null);

                holder.COL1 = (TextView) view.findViewById(R.id.tv_volunteerName);
                holder.COL4 = (TextView) view.findViewById(R.id.tv_volunteerEmail);
//                holder.COL5 = (TextView) view.findViewById(R.id.tv_requestBody);
                holder.button1 = (Button) view.findViewById(R.id.btnReply);
                holder.button2 = (Button) view.findViewById(R.id.btnReject);


                view.setTag(holder);
            } else {

                holder = (VolunteerRequestsList.ViewHolder) view.getTag();
            }

            holder.COL1.setText("Title: "+user.get(position).getVolunteerName());
            holder.COL4.setText("Body:- "+user.get(position).getVolunteerEmail());
//            holder.COL5.setText("Body:- "+user.get(position).getRequestBody());
            System.out.println(holder);

            holder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("Do you want to Reject Request?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    final String idd = user.get(position).getVolunteerName();
                                    FirebaseDatabase.getInstance().getReference("RequestClientDetails").child(idd).removeValue();
                                    //remove function not written
                                    Toast.makeText(myContext, "RequestRejected", Toast.LENGTH_SHORT).show();

                                }

                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }

            });


            return view;


        }
    }

}