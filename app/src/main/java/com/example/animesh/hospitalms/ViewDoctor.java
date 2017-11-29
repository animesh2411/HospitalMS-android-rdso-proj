package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ViewDoctor extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lv;
    HospitalManager sm;
    SQLiteDatabase sb;
    DoctorBean s, bean;
    int did;
    String name;
    ArrayList<DoctorBean> DoctorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);
        lv = (ListView) findViewById(R.id.lv);
        sm = new HospitalManager(this);
        sb = sm.openDB();
        DoctorList = new ArrayList<>();
        fillList();
        ArrayAdapter<DoctorBean> ad = new ArrayAdapter<DoctorBean>(this, android.R.layout.simple_list_item_1, DoctorList);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bean = DoctorList.get(position);
        int idintent = bean.getID();
        Intent i = new Intent(this, ViewDocInfo.class);
        i.putExtra("sid", idintent);
        startActivity(i);
    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDB();
        }
    }
}
