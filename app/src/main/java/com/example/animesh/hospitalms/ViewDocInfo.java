package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ViewDocInfo extends AppCompatActivity {
    TextView txtv;
    HospitalManager sm;
    SQLiteDatabase sb;
    long phone;
    String name, gender, Qualification, speciality, days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc_info);
        txtv = (TextView) findViewById(R.id.txtv);

        Intent i = getIntent();
        int id = i.getIntExtra("sid", 0);
        String args[] = {String.valueOf(id)};

        sm = new HospitalManager(this);
        sb = sm.openDB();

        Cursor c = sb.query(HospitalConstant.TBL_DNAME, null, HospitalConstant.COL_DID + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            name = c.getString(c.getColumnIndex(HospitalConstant.COL_DNAME));
            gender = c.getString(c.getColumnIndex(HospitalConstant.COL_DGENDER));
            phone = c.getLong(c.getColumnIndex(HospitalConstant.COL_DPHONE));
            Qualification = c.getString(c.getColumnIndex(HospitalConstant.COL_DQUALIFICATION));
            speciality = c.getString(c.getColumnIndex(HospitalConstant.COL_DSPECS));
            days = c.getString(c.getColumnIndex(HospitalConstant.COL_DAVAIL));
            c.close();
        }
        txtv.setText(String.format("NAME: %s\n\nGENDER: %s\n\nPHONE: %s\n\nQAULIFICATION: %s\n\nSPECIALITY: %s\n\nAVAILABLE ON DAYS: %s", name, gender, phone, Qualification, speciality, days));
    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDB();
        }
    }
}
