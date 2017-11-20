package com.example.hammode.todolist;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Collections;
import static android.view.View.*;

public class MainActivity extends dbhandler {

   private ListView listo ;
   private FloatingActionButton fab ;
   private CustomAdapter adapter ;
   private EditText title,description;
   private Button done,cancel,sort;
   private TextView head;
   private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listo = (ListView)findViewById(R.id.listview);
        fab= (FloatingActionButton)findViewById(R.id.fab);
        adapter = new CustomAdapter(MainActivity.this);
        listo.setAdapter (adapter);
        db = FirebaseFirestore.getInstance();
        Loader();
        //Trying to creat a search bar
        searchView = (SearchView)findViewById (R.id.search );
        searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Loader ();
                adapter.getFilter ().filter ( newText );
                return true;
            }
        } );
        registerForContextMenu ( listo );
        //The button can generate a "Dialog" in order to add a new task
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
                        dialog.dismiss ();
                    }

                } );
                cancel.setOnClickListener ( new OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss ();
                    }
                } );}});
         sort = (Button) findViewById ( R.id.sort );
         sort.setOnClickListener ( new OnClickListener () {
             @Override
             public void onClick(View v) {
                 SortByTitle ();

             }
         } );


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu ( menu, v, menuInfo );
        menu.add ( "Update" );
        menu.add ( "Done" );
    }

    @Override
    //Defining the functionality for every "Context" option
    public boolean onContextItemSelected(final MenuItem item) {
        super.onContextItemSelected ( item );
        if (item.getTitle ().equals ( "Done" ))
        Delete ( item.getOrder () );
        else {
            //A Dialog to update the task
            final Dialog dialog = new Dialog ( MainActivity.this );
            dialog.setContentView ( R.layout.dialog );
            dialog.show ();
            title = ( EditText ) dialog.findViewById ( R.id.title );
            description = ( EditText ) dialog.findViewById ( R.id.description );
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


/*
* Override Loader in order to notify the adapter of the changes.
* Override other methods to use loader every time the user add or change something.
* */
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
    protected void SortByTitle (){
        Loader ();
        Collections.sort( SharedList.list, TitleComparator);
        adapter.notifyDataSetChanged ();


    }

}


