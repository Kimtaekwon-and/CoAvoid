package com.taekwondev.coavoid.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.taekwondev.coavoid.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchKoreaData();


    }

    public void fetchKoreaData(){

        db = FirebaseFirestore.getInstance();
        db.collection("국내확진데이터")
                .orderBy("state_datetime", Query.Direction.DESCENDING)
                .limit(2)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                long decideCnt = (long)(document.get("decide_cnt"));
                                String decideCntStr = Long.toString(decideCnt);
                               Log.d("aaa", document.getId() + " => " + decideCntStr);

                            }
                        } else {
                            Log.d("aaa", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
}