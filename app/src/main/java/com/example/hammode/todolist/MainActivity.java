package com.example.hammode.todolist;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import static android.view.View.*;

public class MainActivity extends dbhandler {
    //EditText titlein ;
    //EditText descriptionin ;
   private ListView listo ;
   private FloatingActionButton fab ;
   private CustomAdapter adapter ;
   private EditText title,description;
   private Button done,cancel;
   private TextView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //titlein = (EditText) findViewById(R.id.titlein);
        //descriptionin = (EditText)findViewById(R.id.descriptionin);
         adapter = new CustomAdapter(MainActivity.this);
         listo = (ListView)findViewById(R.id.listview);
         fab= (FloatingActionButton)findViewById(R.id.fab);
          db = FirebaseFirestore.getInstance();
        adapter = new CustomAdapter(MainActivity.this);
        listo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Loader();
        listo.invalidateViews();
         registerForContextMenu ( listo );
         fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog ( MainActivity.this );
                dialog.setContentView ( R.layout.dialog );
                dialog.show ();
                title = (EditText)dialog.findViewById(R.id.title);
                description = (EditText)dialog.findViewById(R.id.description);
                head = (TextView )dialog.findViewById(R.id.head);
                head.setText ( "Add new task" );
                done = (Button )dialog.findViewById(R.id.done);
                cancel = (Button )dialog.findViewById(R.id.cancel);
                done.setOnClickListener ( new OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        String stringTitle = title.getText ().toString ();
                        String stringDescription = description.getText ().toString ();
                        add (stringTitle,stringDescription);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss ();
                    }

                } );
                cancel.setOnClickListener ( new OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss ();
                    }
                } );



                /*Dialog dialog = new Dialog ( MainActivity.this );
                dialog.setContentView ( R.layout.dialog );
                dialog.show ();
                title = (EditText)dialog.findViewById(R.id.title);
                description = (EditText)dialog.findViewById(R.id.description);
                head = (TextView )dialog.findViewById(R.id.head);
                done = (Button )dialog.findViewById(R.id.done);
                cancel = (Button )dialog.findViewById(R.id.cancel);*/

                //add(titlein.getText().toString() ,descriptionin.getText().toString());
                //Loader();
                //titlein.setText("");
                //descriptionin.setText("");
                }});
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu ( menu, v, menuInfo );
        menu.add ( "Update" );
        menu.add ( "Done" );
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        super.onContextItemSelected ( item );
        if (item.getTitle ().equals ( "Done" ))
        Delete ( item.getOrder () );
        else {
            final Dialog dialog = new Dialog ( MainActivity.this );
            dialog.setContentView ( R.layout.dialog );
            dialog.show ();
            title = ( EditText ) dialog.findViewById ( R.id.title );
            description = ( EditText ) dialog.findViewById ( R.id.description );
            title.setText ( SharedList.list.get ( item.getOrder () ).getTitle () );
            description.setText ( SharedList.list.get ( item.getOrder () ).getDescription () );
            head = ( TextView ) dialog.findViewById ( R.id.head );
            head.setText ( "Update!" );
            done = ( Button ) dialog.findViewById ( R.id.done );
            cancel = ( Button ) dialog.findViewById ( R.id.cancel );
            done.setOnClickListener ( new OnClickListener () {
                @Override
                public void onClick(View v) {
                    String stringTitle = title.getText ().toString ();
                    String stringdescription = description.getText ().toString ();
                    db.collection ( "Todo" ).document (SharedList.list.get ( item.getOrder () ).getId ())
                            .update ( "Title",stringTitle, "description",stringdescription );
                    Loader ();
                    title.setText ( null );
                    description.setText ( null );
                    dialog.dismiss ();
                }

            } );
            cancel.setOnClickListener ( new OnClickListener () {
                @Override
                public void onClick(View v) {
                    dialog.dismiss ();
                }
            } );

        }
        return true;}



    @Override
    protected void Loader() {
        super.Loader();
        adapter.notifyDataSetChanged ();
    }
    protected void Delete(int order){
        super.Delete ( order );
        Loader ();
    }
    @Override
    protected void add(String Title, String description) {
        super.add ( Title, description );
        Loader ();
    }
}


