package com.example.hammode.todolist;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
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
         registerForContextMenu ( listo );
         fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add(titlein.getText().toString() ,descriptionin.getText().toString());
                Loader();
                titlein.setText("");
                descriptionin.setText("");
                }});
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu ( menu, v, menuInfo );
        menu.add ( "Update" );
        menu.add ( "Done" );
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
         super.onContextItemSelected ( item );
         if (item.getTitle ().equals ("Done"))
          Delete (item.getOrder ());
         else {

         }
        return true;
    }

    @Override
    protected void Loader() {
        super.Loader();
        adapter = new CustomAdapter(MainActivity.this);
        listo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    protected void Delete(int order){
        super.Delete ( order );
        Loader ();
    }

}


