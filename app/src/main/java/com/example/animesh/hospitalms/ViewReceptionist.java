package com.example.animesh.hospitalms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class ViewReceptionist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    HospitalManager sm;
    SQLiteDatabase sb;
    ReceptionBean s, bean;
    int did;
    String name;
    ArrayList<ReceptionBean> RList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receptionist);
        lv = (ListView) findViewById(R.id.lv);
        sm = new HospitalManager(this);
        sb = sm.openDB();
        RList = new ArrayList<>();
        fillList();
        ArrayAdapter<ReceptionBean> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, RList);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
    }

    private void fillList() {
        Cursor c = sb.query(HospitalConstant.TBL_RNAME, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(HospitalConstant.COL_RNAME));
                did = c.getInt(c.getColumnIndex(HospitalConstant.COL_RID));
                s = new ReceptionBean();
                s.setName(name);
                s.setID(did);
                RList.add(s);
            } while (c.moveToNext());
            c.close();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bean = RList.get(position);
        int idintent = bean.getID();
        Intent i = new Intent(this, ViewRecInfo.class);
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
