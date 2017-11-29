package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ReceptionistLogin extends AppCompatActivity implements View.OnClickListener {

    Button btnsubmit;
    EditText txtuserid, txtpass;
    String userid, userpass, args[];
    HospitalManager hm;
    SQLiteDatabase sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_login);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        txtuserid = (EditText) findViewById(R.id.txtuserid);
        txtpass = (EditText) findViewById(R.id.txtpass);
        hm = new HospitalManager(this);
        sb = hm.openDB();
    }

        public boolean login(String uid, String upass){
            args= new String[]{uid, upass};
            Cursor c = sb.query(HospitalConstant.TBL_RNAME, null, HospitalConstant.COL_RNAME+" =? and "+HospitalConstant.COL_RPASS+" =?", args, null, null, null, null);
            if(c!=null){
                if(c.getCount()>0){
                    return true;
                }
                c.close();
            }
            return false;
        }

    @Override
    public void onClick(View v) {
        userid = txtuserid.getText().toString();
        userpass = txtpass.getText().toString();
        if(login(userid, userpass)) {
            Toast.makeText(this, "Receptionist Login Successful", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, RTask.class);
            startActivity(i);
            this.finish();
        }
        else {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Problem with Login");
            adb.setMessage("Invalid Username/ID or Password!");
            adb.setPositiveButton("OK", null);
            adb.setIcon(R.drawable.reception);
            AlertDialog ad = adb.create();
            ad.show();
        }    }
    protected void onDestroy(){
        super.onDestroy();

        if (hm!=null){
            hm.closeDB();
        }
    }
}
