package com.example.tubesarb;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatPasien extends AppCompatActivity {
    //Deklarasi Variable
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Pasien> list;
    MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatpasien);

        //inisialisasi ID
        recyclerView=(RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Pasien>();

        //Mendapatkan Referensi Database
        reference= FirebaseDatabase.getInstance().getReference().child("pasien");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Pasien p = dataSnapshot1.getValue(Pasien.class);
                    list.add(p);
                }
                //Inisialisasi Adapter dan data Pasien dalam bentuk Array
                adapter=new MyAdapter(LihatPasien.this,list);

                //Memasang Adapter pada RecyclerView
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                /*
                Kode ini akan dijalankan ketika ada error dan
                pengambilan data error tersebut lalu memprint error nya
                ke LogCat
               */
                Toast.makeText(LihatPasien.this,"Sepertinya ada yang salah...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}