package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ViewPatient extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Spinner sp;
    Button btnshow;
    HospitalManager hm;
    SQLiteDatabase sb;
    ReceptionBean s;
    int pid;
    String name;
    ArrayAdapter<ReceptionBean> ad;
    ArrayList<ReceptionBean> RList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        btnshow = (Button) findViewById(R.id.btnshow);
        btnshow.setOnClickListener(this);
        sp = (Spinner) findViewById(R.id.sp);
        hm = new HospitalManager(this);
        sb = hm.openDB();
        RList = new ArrayList<>();
        fillList();
        ad = new ArrayAdapter<ReceptionBean>(this, android.R.layout.simple_list_item_1, RList);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
    }

    private void fillList() {
        Cursor c = sb.query(HospitalConstant.TBL_PNAME, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(HospitalConstant.COL_PNAME));
                pid = c.getInt(c.getColumnIndex(HospitalConstant.COL_PID));
                s = new ReceptionBean();
                s.setName(name);
                s.setID(pid);
                RList.add(s);
            } while (c.moveToNext());
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, ViewPatInfo.class);
        i.putExtra("sid", ((ReceptionBean)sp.getSelectedItem()).getID());
        startActivity(i);

    }
    protected void onDestroy(){
        super.onDestroy();

        if (hm!=null){
            hm.closeDB();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
