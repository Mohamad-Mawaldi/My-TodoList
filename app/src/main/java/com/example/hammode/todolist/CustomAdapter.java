package com.example.hammode.todolist;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<Task> implements Filterable {
    private Context con;
    private CustomFilter filter;
    private ArrayList <Task> filterlist;

    private TextView Stitle,Sdescriptionin;
     CustomAdapter(Context context){
        super( context,R.layout.onetask, SharedList.list);
        con = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             if (convertView == null){
             convertView = inflater.inflate(R.layout.onetask,parent,false);}
             Stitle = (TextView)convertView.findViewById(R.id.Stitlein);
             Stitle.setText(SharedList.list.get(position).getTitle ());
             Sdescriptionin = (TextView)convertView.findViewById(R.id.Sdescriptionin);
             Sdescriptionin.setText(SharedList.list.get(position).getDescription ());
            return convertView;
    }
    public Filter getFilter(){
         if (filter==null){

             filter=new CustomFilter ();
         }

         return filter;
    }
    //inner class
    class CustomFilter extends Filter{


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults ();
            if (constraint != null && constraint.length () > 0) {
                constraint = constraint.toString ().toUpperCase ();
                ArrayList<Task> filter = new ArrayList<> ();
                for (int i = 0; i < SharedList.list.size (); i++) {
                    if(SharedList.list.get ( i ).getTitle ().contains ( constraint )) {
                        Task t = new Task ( SharedList.list.get ( i ).getId (),
                                SharedList.list.get ( i ).getTitle ()
                                ,SharedList.list.get ( i ).getDescription () );
                        filter.add ( t );
                    }
                    results.count=filter.size ();
                    results.values=filter;
                }
            }else {
                results.count=filterlist.size ();
                results.values=filterlist;

            }

            return results;
            }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            SharedList.list= (ArrayList<Task>) results.values;
            notifyDataSetChanged ();

            }
        }


    }



