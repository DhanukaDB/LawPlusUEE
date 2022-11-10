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

public class ArticleList extends AppCompatActivity {

    Button button;
    ListView listView;
    List<Article> user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_articles);

        button = (Button)findViewById(R.id.addDetails);
        listView = (ListView)findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleList.this, PublishArticle.class);
                startActivity(intent);
            }
        });

        user = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("Articles");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    Article Article = studentDatasnap.getValue(Article.class);
                    user.add(Article);
                }

                MyAdapter adapter = new MyAdapter(ArticleList.this, R.layout.article_details, (ArrayList<Article>) user);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    static class ViewHolder {

        TextView COL1;
        TextView COL2;
        TextView COL3;
       TextView COL4;
        Button deletebtn;
        Button button1;
    }

    class MyAdapter extends ArrayAdapter<Article> {
        LayoutInflater inflater;
        Context myContext;
        List<Article> user;


        public MyAdapter(Context context, int resource, ArrayList<Article> objects) {
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
                view = inflater.inflate(R.layout.article_details, null);

                holder.COL1 = (TextView) view.findViewById(R.id.category);
                holder.COL2 = (TextView) view.findViewById(R.id.title);
                holder.COL3 = (TextView) view.findViewById(R.id.body);
              holder.COL4 = (TextView) view.findViewById(R.id.bustime);
               holder.button1 = (Button) view.findViewById(R.id.editbtn);
               holder.deletebtn = (Button) view.findViewById(R.id.deletebtn);


                view.setTag(holder);
            } else {

                holder = (ViewHolder) view.getTag();
            }

            holder.COL1.setText("Category:   "+user.get(position).getCategory());
            holder.COL2.setText("Title:   "+user.get(position).getTitle());
            holder.COL3.setText(""+user.get(position).getBody());
            holder.COL4.setText("Resources: "+user.get(position).getFile());
            System.out.println(holder);



            holder.deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("Do you want to delete this article?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    final String idd = user.get(position).getTitle();
                                    FirebaseDatabase.getInstance().getReference("Articles").child(idd).removeValue();
                                    //remove function not written
                                    Toast.makeText(myContext, "Article deleted successfully", Toast.LENGTH_SHORT).show();

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

//            //delete an exsiting product
//            holder.deletebtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.COL2.getContext());
//                    builder.setTitle("Are you sure?");
//                    builder.setMessage("Deleted data can't be undo");
//                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            FirebaseDatabase.getInstance().getReference().child("Articles")
//                                    .child(ref.getRef().getKey()).removeValue();
//                        }
//                    });
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(holder.COL2.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.show();
//                }
//            });
//

            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    View view1 = inflater.inflate(R.layout.update_article, null);
                    dialogBuilder.setView(view1);

                    final EditText editText1 = (EditText) view1.findViewById(R.id.product_name);
                    final EditText editText2 = (EditText) view1.findViewById(R.id.product_price);
                    final EditText editText3 = (EditText) view1.findViewById(R.id.product_brand);
                    final EditText editText4 = (EditText) view1.findViewById(R.id.product_category);

                    final Button buttonupdate = (Button) view1.findViewById(R.id.btnEdit1);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    final String idd = user.get(position).getId();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Articles");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String category = (String) snapshot.child("category").getValue();
                            String title = (String) snapshot.child("title").getValue();
                            String body = (String) snapshot.child("body").getValue();
                            String file = (String) snapshot.child("file").getValue();


                            editText1.setText(category);
                            editText2.setText(title);
                            editText3.setText(body);
                            editText4.setText(file);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    buttonupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String category = editText1.getText().toString();
                            String title = editText2.getText().toString();
                            String body = editText3.getText().toString();
                            String file = editText4.getText().toString();


                           if (category.isEmpty()) {
                                editText1.setError("Bus no is required");
                            }else if (title.isEmpty()) {
                                editText2.setError("Start location is required");
                            }else if (body.isEmpty()) {
                                editText3.setError("End location is required");
                            }else if (file.isEmpty()) {
                                editText4.setError("Time is required");
                            }else {

                                HashMap map = new HashMap();
                                map.put("category", category);
                                map.put("title", title);
                                map.put("body", body);
                               map.put("file", file);

                                reference.updateChildren(map);

                                Toast.makeText(ArticleList.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(ArticleList.this, Home.class));
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
