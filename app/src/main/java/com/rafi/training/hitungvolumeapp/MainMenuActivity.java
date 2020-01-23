package com.rafi.training.hitungvolumeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenuActivity extends AppCompatActivity {
    private Button btn_prisma,btn_contact,btn_menu;
    private TextView tvnama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        btn_prisma=findViewById(R.id.btn_prisma_segitiga);
        btn_contact=findViewById(R.id.btn_contact_us);
        btn_menu=findViewById(R.id.btn_web_browser);
        tvnama=findViewById(R.id.tv_nama);

        if (getIntent().getExtras() != null) {
            Bundle namap = getIntent().getExtras();
            tvnama.setText(namap.getString("Nama :"));
            tvnama.setText(getIntent().getStringExtra("Nama :"));
        }

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomorHp = "081283288739";
                Uri tel = Uri.parse("tel:"+nomorHp);
                Intent contact = new Intent(Intent.ACTION_DIAL,tel);
                //check if there is activity /apps that can handle this intent
                if (contact.resolveActivity(getPackageManager()) != null){
                startActivity(contact);
            }else {
                    Toast.makeText(getApplicationContext(),"tidak ada aplikasi yang dapat mengahndle intent ini", Toast.LENGTH_SHORT).show();
                }
        }});

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String web = "www.Techpolitan.com";
                Uri domain = Uri.parse("http://"+web);
                Intent openweb = new Intent(Intent.ACTION_VIEW,domain);
                Intent chooser = Intent.createChooser(openweb,"Mau dibuka pakai app apa:");
                if (openweb.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
            }
        });
    }

    public void Pindah(View view) {
        Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
