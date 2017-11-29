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

public class UpdateReception extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner sp;
    Button btnupdate;
    HospitalManager hm;
    SQLiteDatabase sb;
    ReceptionBean s;
    int rid, id;
    String name;
    ArrayAdapter<ReceptionBean> ad;
    ArrayList<ReceptionBean> RList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reception);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(this);
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
        Cursor c = sb.query(HospitalConstant.TBL_RNAME, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(HospitalConstant.COL_RNAME));
                rid = c.getInt(c.getColumnIndex(HospitalConstant.COL_RID));
                s = new ReceptionBean();
                s.setName(name);
                s.setID(rid);
                RList.add(s);
            } while (c.moveToNext());
            c.close();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, UpdateRecFields.class);
        i.putExtra("sid", ((ReceptionBean)sp.getSelectedItem()).getID());
        i.putExtra("sname", ((ReceptionBean)sp.getSelectedItem()).getName());
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

