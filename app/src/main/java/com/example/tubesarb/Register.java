package com.example.tubesarb;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;


public class Register extends AppCompatActivity {

    //Deklarasi Variable
    DatePickerDialog picker;
    EditText lahir;
    EditText masuk;
    EditText nama;
    EditText etalamat;
    EditText etdeskripsi;
    ImageView imageView2;

    Bitmap bitmap;
    Button barcode;
    DatabaseReference databaseReference;
    OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //inisialisasi ID EditText dan button
        nama = findViewById(R.id.nama_lengkap);
        etalamat = findViewById(R.id.alamat);
        etdeskripsi = findViewById(R.id.deskripsi);

        //inisialisasi picker tanggal lahir dan tanggal masuk
        lahir = findViewById(R.id.tanggal_lahir);
        //tanggal lahir
        lahir.setInputType(InputType.TYPE_NULL);
        lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                lahir.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        masuk = findViewById(R.id.tanggal_masuk);
        ///tanggal masuk
        masuk.setInputType(InputType.TYPE_NULL);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                masuk.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        imageView2 = findViewById(R.id.imageView2);
    }


    public void generate(View view) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(("Nama : "+nama.getText().toString()+ "\n"+"Tanggal Lahir: "+ lahir.getText().toString()+"\n"+"Alamat: "+etalamat.getText().toString()+"\n"+"Tanggal Masuk RS: "+masuk.getText().toString()+"\n"+"Deskripsi: "+etdeskripsi.getText().toString()), BarcodeFormat.QR_CODE, 120, 120);
            Bitmap bitmap = Bitmap.createBitmap(120,120,Bitmap.Config.RGB_565);

            for(int x = 0; x<120;x++){
                for (int y=0;y<120;y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                }
            }

            imageView2.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void simpan(View view) {

        Button tombol = (Button) findViewById(R.id.simpan);
        //mengambil referensi ke firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final String qrCode = nama.getText().toString() + masuk.getText().toString();

        tombol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!isEmpty(nama.getText().toString()) && !isEmpty(lahir.getText().toString()) && !isEmpty(etalamat.getText().toString()) && !isEmpty(masuk.getText().toString()) && !isEmpty(etdeskripsi.getText().toString()))
                    savePasien(new Pasien(nama.getText().toString(),lahir.getText().toString(),etalamat.getText().toString(),masuk.getText().toString(),etdeskripsi.getText().toString(),qrCode.getBytes().toString()));
                    else
                    Toast.makeText(Register.this, "Tolong Isi Data Anda!!", Toast.LENGTH_SHORT).show();
                }
        });

    }
    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void savePasien(Pasien pasien){
        databaseReference.child("pasien").push().setValue(pasien).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nama.setText(" ");
                lahir.setText(" ");
                etalamat.setText(" ");
                masuk.setText(" ");
                etdeskripsi.setText(" ");
                imageView2.setImageBitmap(bitmap);
                Snackbar.make(findViewById(R.id.simpan), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    public void barcode(View view) {
                    imageView2 = findViewById(R.id.imageView2);
                    barcode=findViewById(R.id.barcode);

                    barcode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BitmapDrawable drawable = (BitmapDrawable) imageView2.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();

                            File filepath = Environment.getExternalStorageDirectory();
                            File dir = new File(filepath.getAbsolutePath()+"/Demo/");
                            dir.mkdir();
                            File file=new File(dir,System.currentTimeMillis()+".jpg");
                            try {
                                outputStream = new FileOutputStream(file);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                            Toast.makeText(getApplicationContext(),"Image Save To Internal Device"
                                    ,Toast.LENGTH_SHORT).show();
                            try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });

    }
}