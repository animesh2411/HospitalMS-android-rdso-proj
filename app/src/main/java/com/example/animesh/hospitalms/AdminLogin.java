package com.example.animesh.hospitalms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sp;
    EditText txtuserid, txtpass;
    Button btnsubmit;
    String uid, upass, u_id, u_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        txtpass = (EditText) findViewById(R.id.txtpass);
        txtuserid = (EditText) findViewById(R.id.txtuserid);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        sp = getSharedPreferences("LoginWelcome", MODE_PRIVATE);
        u_id = sp.getString("userid", null);
        u_pass = sp.getString("pass", null);
    }

    @Override
    public void onClick(View v) {
        uid = txtuserid.getText().toString();
        upass = txtpass.getText().toString();

        if(uid.equals(u_id) && upass.equals(u_pass)) {
            Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, AdminTask.class);
            i.putExtra("msg", "Hello! Admin of RDSO HMS.\nYou have the following tasks.");
            startActivity(i);
            this.finish();
        }
        else {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Problem with Login");
            adb.setMessage("Invalid Username/ID or Password!");
            adb.setPositiveButton("OK", null);
            adb.setIcon(R.drawable.hlogo);
            AlertDialog ad = adb.create();
            ad.show();
        }
    }
}
