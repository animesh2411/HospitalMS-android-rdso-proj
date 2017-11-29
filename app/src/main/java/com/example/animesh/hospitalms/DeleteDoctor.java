package com.example.animesh.hospitalms;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class DeleteDoctor extends AppCompatActivity implements View.OnClickListener {

    Spinner sp;
    Button btnshow;
    HospitalManager sm;
    SQLiteDatabase sb;
    DoctorBean s;
    int did=0, rw;
    String name=null, args[];
    ArrayList<DoctorBean> DoctorList;
    ArrayAdapter<DoctorBean> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_doctor);
        btnshow = (Button) findViewById(R.id.btndelete);
        btnshow.setOnClickListener(this);
        sp = (Spinner) findViewById(R.id.sp);
        sm = new HospitalManager(this);
        sb = sm.openDB();
        DoctorList = new ArrayList<>();
        fillList();
        ad = new ArrayAdapter<DoctorBean>(this, android.R.layout.simple_list_item_1, DoctorList);
        sp.setAdapter(ad);
    }

    public void fillList(){
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

    public void onClick(View v) {
        args = new String[]{String.valueOf(((DoctorBean) sp.getSelectedItem()).getID()), ((DoctorBean) sp.getSelectedItem()).getName()};
//        args = new String[]{std, name};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Delete Doctor");
        adb.setMessage(R.string.delmsg);
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rw = sb.delete(HospitalConstant.TBL_DNAME, HospitalConstant.COL_DID + " =? and " + HospitalConstant.COL_DNAME + " =?", args);
                if (rw > 0) {
                    Toast.makeText(DeleteDoctor.this, "Doctor Details Deleted", Toast.LENGTH_LONG).show();
                    sp.setAdapter(null);
                    DoctorList.clear();
                    fillList();
                    ad.notifyDataSetChanged();
                    sp.setAdapter(ad);
                }

            }
        });
        adb.setNegativeButton("No", null);
        adb.setIcon(R.drawable.doc);
        AlertDialog a = adb.create();
        a.show();
    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDB();
        }
    }
}
