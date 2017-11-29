package com.example.animesh.hospitalms;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class UpdateDocFields extends AppCompatActivity implements View.OnClickListener {

    EditText txtdid, txtname, txtphone, txtage, txtmarital, txtemail, txtaddress, txtqualification, txtspec, txtavail;
    String name, email, address, specs, qualification, maritalstatus, days;
    long phone;
    int age;
    Button btnsubmit;
    String args[];

    int id;
    HospitalManager hm;
    SQLiteDatabase sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doc_fields);
        Intent i = getIntent();
        hm = new HospitalManager(this);
        sb = hm.openDB();
        id = i.getIntExtra("sid", 0);
        name = i.getStringExtra("sname");

        txtdid = (EditText) findViewById(R.id.txtdid);
        txtname = (EditText) findViewById(R.id.txtname);
        txtdid.setText(String.valueOf(id));
        txtname.setText(name);
        args = new String[]{String.valueOf(id)};

        txtphone = (EditText) findViewById(R.id.txtphone);
        txtage = (EditText) findViewById(R.id.txtage);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtmarital = (EditText) findViewById(R.id.txtmarital);
        txtaddress = (EditText) findViewById(R.id.txtaddress);
        txtspec = (EditText) findViewById(R.id.txtspec);
        txtqualification = (EditText) findViewById(R.id.txtqualification);
        txtavail = (EditText) findViewById(R.id.txtavail);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);

        Cursor c = sb.query(HospitalConstant.TBL_DNAME, null, HospitalConstant.COL_DID + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            phone = c.getLong(c.getColumnIndex(HospitalConstant.COL_DPHONE));
            age = c.getInt(c.getColumnIndex(HospitalConstant.COL_DAGE));
            maritalstatus = c.getString(c.getColumnIndex(HospitalConstant.COL_DMARITAL));
            email = c.getString(c.getColumnIndex(HospitalConstant.COL_DEMAIL));
            address = c.getString(c.getColumnIndex(HospitalConstant.COL_DADD));
            qualification = c.getString(c.getColumnIndex(HospitalConstant.COL_DQUALIFICATION));
            specs = c.getString(c.getColumnIndex(HospitalConstant.COL_DSPECS));
            days = c.getString(c.getColumnIndex(HospitalConstant.COL_DAVAIL));
            c.close();
        }

        txtphone.setText(String.valueOf(phone));
        txtage.setText(String.valueOf(age));
        txtmarital.setText(maritalstatus);
        txtemail.setText(email);
        txtaddress.setText(address);
        txtqualification.setText(qualification);
        txtspec.setText(specs);
        txtavail.setText(days);

    }
    @Override
    public void onClick(View v) {
        phone = Long.parseLong(txtphone.getText().toString());
        age = Integer.parseInt(txtage.getText().toString());
        maritalstatus = txtmarital.getText().toString();
        email = txtemail.getText().toString();
        address = txtaddress.getText().toString();
        qualification = txtqualification.getText().toString();
        specs = txtspec.getText().toString();
        days = txtavail.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(HospitalConstant.COL_DPHONE, phone);
        cv.put(HospitalConstant.COL_DAGE, age);
        cv.put(HospitalConstant.COL_DMARITAL, maritalstatus);
        cv.put(HospitalConstant.COL_DEMAIL, email);
        cv.put(HospitalConstant.COL_DADD, address);
        cv.put(HospitalConstant.COL_DQUALIFICATION, qualification);
        cv.put(HospitalConstant.COL_DSPECS, specs);
        cv.put(HospitalConstant.COL_DAVAIL, days);

        long rw = sb.update(HospitalConstant.TBL_DNAME, cv, HospitalConstant.COL_DID+" =?", args);
        if (rw>0){
            Toast.makeText(this, "Doctor Records updated", Toast.LENGTH_LONG).show();
            UpdateDocFields.this.finish();
        }
    }
    protected void onDestroy(){
        super.onDestroy();

        if (hm!=null){
            hm.closeDB();
        }
    }
}
