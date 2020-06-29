package com.example.tubesarb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //Deklarasi Variable
    Context context;
    ArrayList<Pasien> pasiens;

    //Membuat Konstruktor, untuk menerima input dari Database
    public MyAdapter(Context c,ArrayList<Pasien> p){
        context=c;
        pasiens=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        holder.nama.setText(pasiens.get(position).getNama());
        holder.alamatp.setText(pasiens.get(position).getAlamat());
        holder.tanggall.setText(pasiens.get(position).getTanggalLahir());
        holder.tanggalm.setText(pasiens.get(position).getTanggalMasuk());
        holder.desk.setText(pasiens.get(position).getDeskripsi());

    }

    @Override
    public int getItemCount() {
        return pasiens.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,alamatp,tanggall,tanggalm,desk;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           nama=(TextView) itemView.findViewById(R.id.nama);
           alamatp=(TextView) itemView.findViewById(R.id.alamatp);
           tanggall=(TextView) itemView.findViewById(R.id.tangall);
           tanggalm=(TextView) itemView.findViewById(R.id.tanggalm);
           desk=(TextView) itemView.findViewById(R.id.desk);


       }

   }

}
