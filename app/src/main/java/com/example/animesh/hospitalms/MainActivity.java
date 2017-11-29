package com.example.animesh.hospitalms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sp;
    SharedPreferences.Editor se;

    Button btnreceptionist, btnadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadmin = (Button) findViewById(R.id.btnadmin);
        btnreceptionist = (Button) findViewById(R.id.btnreceptionist);
        btnadmin.setOnClickListener(this);
        btnreceptionist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnadmin:
                sp = getSharedPreferences("LoginWelcome", MODE_PRIVATE);
                se = sp.edit();
                se.putString("userid", "admin");
                se.putString("pass", "admin");
                se.commit();

                Intent ia = new Intent(this, AdminLogin.class);
                startActivity(ia);
                break;
            case R.id.btnreceptionist:
                Intent ir = new Intent(this, ReceptionistLogin.class);
                startActivity(ir);
                break;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mf = getMenuInflater();
        mf.inflate(R.menu.mymenu, menu);
        return true;
    }
}
