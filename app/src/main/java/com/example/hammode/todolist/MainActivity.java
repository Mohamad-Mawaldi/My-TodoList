package com.example.hammode.todolist;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.google.firebase.firestore.FirebaseFirestore;
import static android.view.View.*;

public class MainActivity extends dbhandler {
    EditText titlein ;
    EditText descriptionin ;
    ListView listo ;
    FloatingActionButton fab ;
    CustomAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         titlein = (EditText) findViewById(R.id.titlein);
         adapter = new CustomAdapter(MainActivity.this);
         descriptionin = (EditText)findViewById(R.id.descriptionin);
         listo = (ListView)findViewById(R.id.listview);
         fab= (FloatingActionButton)findViewById(R.id.fab);
          db = FirebaseFirestore.getInstance();
          Loader();
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add(titlein.getText().toString() ,descriptionin.getText().toString());
                Loader();
                titlein.setText("");
                descriptionin.setText("");
                adapter.notifyDataSetChanged ();
            }});

    }
    @Override
    protected void Loader() {
        super.Loader();
        adapter = new CustomAdapter(MainActivity.this);
        listo.setAdapter(adapter);
    }

}


