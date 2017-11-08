package com.example.hammode.todolist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.UUID;

public class dbhandler extends AppCompatActivity {
    FirebaseFirestore db;
    protected void Loader() {
        com.google.android.gms.tasks.Task<QuerySnapshot> querySnapshotTask = db.collection("Todo").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc : task.getResult()) {
                            Toast.makeText(dbhandler.this ,"This is my Toast message!",
                                    Toast.LENGTH_LONG).show();

                            Task todo = new Task(doc.getString("Id"),
                                                 doc.getString("Title"),
                                                 doc.getString("description"));
                            SharedList.list.add(todo);
                        }
                    }
                });

    }
    protected void add (String title , String description){
        String Id = "1";
        HashMap<String,Object> todo = new HashMap<>();
        todo.put("Id",Id);
        todo.put("title" ,title);
        todo.put("description" ,description);
        db.collection("Todo").document(Id).set(todo);
    }

    }
