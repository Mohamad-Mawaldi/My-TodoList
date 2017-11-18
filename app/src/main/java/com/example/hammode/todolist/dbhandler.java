package com.example.hammode.todolist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.UUID;

public class dbhandler extends AppCompatActivity {
    FirebaseFirestore db;
    protected void Loader() {
        SharedList.list.clear();
        com.google.android.gms.tasks.Task<QuerySnapshot> querySnapshotTask = db.collection("Todo").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc : task.getResult()) {
                            Task todo = new Task(doc.getString("Id"),
                                                 doc.getString("Title"),
                                                 doc.getString("description"));
                            SharedList.list.add(todo);
                        }
                        Toast.makeText(dbhandler.this ,"Loading!",
                                Toast.LENGTH_LONG).show();
                    }
                });

    }
    protected void add (String Title , String description){
        String Id = UUID.randomUUID ().toString ();
        HashMap<String,Object> todo = new HashMap<>();
        todo.put("Id",Id);
        todo.put("Title" ,Title);
        todo.put("description" ,description);
        db.collection("Todo").document(Id).set(todo);
    }
    protected void Delete (int order){
        db.collection ( "Todo" ).document (SharedList.list.get ( order ).getId ()).
                delete ();
            }






    }


