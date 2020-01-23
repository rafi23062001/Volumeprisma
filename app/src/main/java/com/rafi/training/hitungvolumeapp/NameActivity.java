package com.rafi.training.hitungvolumeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {
    private Button btn_next;;
    private EditText etData1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_name);
            etData1=findViewById(R.id.nama_orang);
            btn_next=findViewById(R.id.btn_next);
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Nama :", etData1.getText().toString());
                    Intent pindahkan = new Intent(NameActivity.this, MainMenuActivity.class);
                    pindahkan.putExtras(bundle);
                    startActivity(pindahkan);
                }
            });
    }

    public void Next(View view) {
        Intent Lanjutkan = new Intent(NameActivity.this,MainMenuActivity.class);
        startActivity(Lanjutkan);
    }
}
