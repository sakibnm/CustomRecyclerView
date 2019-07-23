package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "demo";
    private UserAdapter listAdapter;
    private ArrayList<User> userList = new ArrayList<>();
    private RecyclerView rv_Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        rv_Users = findViewById(R.id.rv_listUsers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_Users.setLayoutManager(linearLayoutManager);
        listAdapter = new UserAdapter(userList, this);
        rv_Users.setAdapter(listAdapter);

        CollectionReference collectionReference = db.collection("testData");

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){

                        Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());

                        userList.add(new User(documentSnapshot.get("name").toString(), documentSnapshot.get("image").toString()));
                        listAdapter.notifyDataSetChanged();
                    }
                }else{
                    Log.d(TAG, String.valueOf(task.getException()));
                }
            }
        });


    }
}
