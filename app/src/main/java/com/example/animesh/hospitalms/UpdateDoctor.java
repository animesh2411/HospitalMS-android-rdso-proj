package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class UpdateDoctor extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner sp;
    Button btnupdate;
    HospitalManager hm;
    SQLiteDatabase sb;
    DoctorBean s;
    int did, id;
    String name;
    ArrayAdapter<DoctorBean> ad;
    ArrayList<DoctorBean> DoctorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(this);
        sp = (Spinner) findViewById(R.id.sp);
        hm = new HospitalManager(this);
        sb = hm.openDB();
        DoctorList = new ArrayList<>();
        fillList();
        ad = new ArrayAdapter<DoctorBean>(this, android.R.layout.simple_list_item_1, DoctorList);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
    }

    private void fillList() {
        Cursor c = sb.query(HospitalConstant.TBL_DNAME, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(HospitalConstant.COL_DNAME));
                did = c.getInt(c.getColumnIndex(HospitalConstant.COL_DID));
                s = new DoctorBean();
                s.setName(name);
                s.setID(did);
                DoctorList.add(s);
            } while (c.moveToNext());
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, UpdateDocFields.class);
        i.putExtra("sid", ((DoctorBean)sp.getSelectedItem()).getID());
        i.putExtra("sname", ((DoctorBean)sp.getSelectedItem()).getName());
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    protected void onDestroy(){
        super.onDestroy();

        if (hm!=null){
            hm.closeDB();
        }
    }
}
