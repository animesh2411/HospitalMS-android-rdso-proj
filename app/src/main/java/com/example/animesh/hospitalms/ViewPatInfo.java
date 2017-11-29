package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ViewPatInfo extends AppCompatActivity {
    TextView txtv;
    HospitalManager sm;
    SQLiteDatabase sb;
    long phone, adhar;
    int age, id;
    String name, gender, marital, email, address, DOB, regdoc ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pat_info);
        txtv = (TextView) findViewById(R.id.txtv);

        Intent i = getIntent();
        id = i.getIntExtra("sid", 0);
        String args[] = {String.valueOf(id)};

        sm = new HospitalManager(this);
        sb = sm.openDB();

        Cursor c = sb.query(HospitalConstant.TBL_PNAME, null, HospitalConstant.COL_PID + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            name = c.getString(c.getColumnIndex(HospitalConstant.COL_PNAME));
            phone = c.getLong(c.getColumnIndex(HospitalConstant.COL_PPHONE));
            DOB = c.getString(c.getColumnIndex(HospitalConstant.COL_PDOB));
            age = c.getInt(c.getColumnIndex(HospitalConstant.COL_PAGE));
            gender = c.getString(c.getColumnIndex(HospitalConstant.COL_PGENDER));
            marital = c.getString(c.getColumnIndex(HospitalConstant.COL_PMARITAL));
            email = c.getString(c.getColumnIndex(HospitalConstant.COL_PEMAIL));
            address = c.getString(c.getColumnIndex(HospitalConstant.COL_PADD));
            adhar = c.getLong(c.getColumnIndex(HospitalConstant.COL_PADHAR));
            regdoc = c.getString(c.getColumnIndex(HospitalConstant.COL_PDOCNAME));
            c.close();
        }
        txtv.setText(String.format("ID: %s\n\nNAME: %s\n\nPHONE: %s\n\nDOB: %s\n\nAGE: %s\n\nGENDER: %s\n\nMARITAL STATUS: %s\n\nEMAIL: %s\n\nADDRESS: %s\n\nAADHAR NO: %s\n\nDOCTOR NAME: %s",
                id, name, phone, DOB, age, gender, marital, email, address, adhar, regdoc));
    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDB();
        }
    }
}
