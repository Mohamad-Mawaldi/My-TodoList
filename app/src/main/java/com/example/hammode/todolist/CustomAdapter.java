package com.example.hammode.todolist;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class CustomAdapter extends ArrayAdapter<Task> {
    private Context con;
     CustomAdapter(Context context){
        super( context,R.layout.onetask ,SharedList.list);
        con = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.onetask,parent,false);
            TextView Stitle = (TextView)row.findViewById(R.id.Stitlein);
            Stitle.setText(SharedList.list.get(position).getTitle ());
            TextView Sdescriptionin = (TextView)row.findViewById(R.id.Sdescriptionin);
            Sdescriptionin.setText(SharedList.list.get(position).getDescription ());
            return row;

    }
}
