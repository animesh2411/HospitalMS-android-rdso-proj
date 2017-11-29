package com.example.animesh.hospitalms;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class DeletePatient extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner sp;
    Button btnshow;
    HospitalManager sm;
    SQLiteDatabase sb;
    ReceptionBean s;
    int did=0, rw, pid=0;
    String name=null, pname=null, std, args[];
    ArrayList<ReceptionBean> RList;
    ArrayAdapter<ReceptionBean> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_patient);
        btnshow = (Button) findViewById(R.id.btndelete);
        btnshow.setOnClickListener(this);
        sp = (Spinner) findViewById(R.id.sp);
        sm = new HospitalManager(this);
        sb = sm.openDB();
        RList = new ArrayList<>();
        fillList();
        ad = new ArrayAdapter<ReceptionBean>(this, android.R.layout.simple_list_item_1, RList);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
    }

    public void fillList(){
        Cursor c = sb.query(HospitalConstant.TBL_PNAME, null, null, null, null, null, null);
        if(c!=null && c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(HospitalConstant.COL_PNAME));
                did = c.getInt(c.getColumnIndex(HospitalConstant.COL_PID));
                s = new ReceptionBean();
                s.setName(name);
                s.setID(did);
                RList.add(s);
            } while (c.moveToNext());
            c.close();
        }
    }

    public void onClick(View v) {
        pid = ((ReceptionBean)sp.getSelectedItem()).getID();
        pname = ((ReceptionBean)sp.getSelectedItem()).getName();
        args = new String[]{String.valueOf(pid), pname};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Delete Patient");
        adb.setMessage(R.string.delmsg);
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rw = sb.delete(HospitalConstant.TBL_PNAME, HospitalConstant.COL_PID + " =? and " + HospitalConstant.COL_PNAME + " =?", args);
                if (rw > 0) {
                    Toast.makeText(DeletePatient.this, "Patient Details Deleted", Toast.LENGTH_LONG).show();
                    sp.setAdapter(null);
                    RList.clear();
                    fillList();
                    ad.notifyDataSetChanged();
                    sp.setAdapter(ad);
                }
            }
        });
        adb.setNegativeButton("No", null);
        adb.setIcon(R.drawable.dna);
        AlertDialog a = adb.create();
        a.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    protected void onDestroy(){
        super.onDestroy();

        if (sm!=null){
            sm.closeDB();
        }
    }
}
