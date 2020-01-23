package com.rafi.training.hitungvolumeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_prisma;
    private TextView tvResult;
    private EditText edtLength, edtWidth, edtHeight;
    private Button btnCount,btnReset;
    private ActionBar actionBar;
    double volume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreference",Context.MODE_PRIVATE);
        String cookie1 = sharedPreferences.getString("panjang",null);
        String cookie2 = sharedPreferences.getString("lebar",null);
        String cookie3 = sharedPreferences.getString("tinggi",null);
        volume = sharedPreferences.getFloat("volume",0.0F);





        btn_prisma=findViewById(R.id.btn_prisma);

        initialViewComponent();
        edtLength.setText(cookie1);
        edtWidth.setText(cookie2);
        edtHeight.setText(cookie3);
        tvResult.setText(String.valueOf(volume));
        btnCount.setOnClickListener(this);
        btnReset.setOnClickListener(this);



    }

    private void initialViewComponent(){
        tvResult = findViewById(R.id.tv_result);
        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btnCount = findViewById(R.id.btn_count);
        btnReset = findViewById(R.id.btn_reset);
        actionBar = getSupportActionBar();
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_title));
    }


    private void hitungVolume(String paramLength, String paramWidth, String paramHeight){
        boolean isEmptyInput = false;
        boolean isValidDouble = true;
        if(TextUtils.isEmpty(paramLength)){
            isEmptyInput = true;
            edtLength.setError("Field panjang tidak boleh kosong");
            Toast.makeText(this,"Field panjang tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(paramWidth)){
            isEmptyInput = true;
            edtWidth.setError("Field lebar tidak boleh kosong");
            Toast.makeText(this,"Field lebar tidak boleh kosong", Toast.LENGTH_LONG).show();
        }

        if(TextUtils.isEmpty(paramHeight)){
            isEmptyInput = true;
            edtHeight.setError("Field height tidak boleh kosong");
            Toast.makeText(this,"Field tinggi tidak boleh kosong", Toast.LENGTH_LONG).show();
        }

        //proses konversi
        Double length = convertToDouble(paramLength); // null
        Double width = convertToDouble(paramWidth);
        Double height = convertToDouble(paramHeight);

        if(length == null){
            isValidDouble = false;
        }

        if(width == null){
            isValidDouble = false;
        }

        if(height == null){
            isValidDouble = false;
        }

        if(!isEmptyInput && isValidDouble){
            volume = (length * width /2) * height;
            tvResult.setText(String.valueOf(volume));
        }
    }

    private Double convertToDouble(String data){
        try {
            return Double.valueOf(data);
        }
        catch (Exception e){
            Log.e("Convert Error : ",e.getMessage());
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_count:
                //do something when btn_count is clicked
                String length = edtLength.getText().toString().trim();
                String width = edtWidth.getText().toString().trim();
                String height = edtHeight.getText().toString().trim();

                hitungVolume(length,width,height);
                break;

        }
    }

    public void Back(View view) {
        Intent pindahKePrisma = new Intent(MainActivity.this,MainMenuActivity.class);
        startActivity(pindahKePrisma);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("panjang", edtLength.getText().toString());
        editor.putString("lebar", edtWidth.getText().toString());
        editor.putString("tinggi", edtHeight.getText().toString());
        editor.putFloat("volume", (float) volume);
        editor.commit();
        Toast.makeText(this,String.valueOf(volume),Toast.LENGTH_LONG).show();

    }

}
