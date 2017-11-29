package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ViewRecInfo extends AppCompatActivity {
    TextView txtv;
    HospitalManager sm;
    SQLiteDatabase sb;
    long phone;
    String name, shift, rpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rec_info);
        txtv = (TextView) findViewById(R.id.txtv);

        Intent i = getIntent();
        int id = i.getIntExtra("sid", 0);
        String args[] = {String.valueOf(id)};

        sm = new HospitalManager(this);
        sb = sm.openDB();

        Cursor c = sb.query(HospitalConstant.TBL_RNAME, null, HospitalConstant.COL_RID + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            name = c.getString(c.getColumnIndex(HospitalConstant.COL_DNAME));
            rpass = c.getString(c.getColumnIndex(HospitalConstant.COL_RPASS));
            phone = c.getLong(c.getColumnIndex(HospitalConstant.COL_RPHONE));
            shift = c.getString(c.getColumnIndex(HospitalConstant.COL_RSHIFT));
            c.close();
        }
        txtv.setText(String.format("NAME: %s\n\nID: %s\n\nPASSWORD: %s\n\nPHONE: %s\n\nSHIFT: %s", name, id, rpass, phone, shift));
    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDB();
        }
    }
}
