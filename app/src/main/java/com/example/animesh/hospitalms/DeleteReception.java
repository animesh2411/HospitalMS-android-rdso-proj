package com.example.animesh.hospitalms;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class DeleteReception extends AppCompatActivity implements View.OnClickListener {

    Spinner sp;
    Button btnshow;
    HospitalManager sm;
    SQLiteDatabase sb;
    ReceptionBean s;
    int did=0, rw;
    String name=null, std, args[];
    ArrayList<ReceptionBean> RList;
    ArrayAdapter<ReceptionBean> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_reception);
        btnshow = (Button) findViewById(R.id.btndelete);
        btnshow.setOnClickListener(this);
        sp = (Spinner) findViewById(R.id.sp);
        sm = new HospitalManager(this);
        sb = sm.openDB();
        RList = new ArrayList<>();
        fillList();
        ad = new ArrayAdapter<ReceptionBean>(this, android.R.layout.simple_list_item_1, RList);
        sp.setAdapter(ad);
    }

    public void fillList(){
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

    public void onClick(View v) {
//        std = String.valueOf(did);
//        args = new String[]{std, name};
        args = new String[]{String.valueOf(((ReceptionBean) sp.getSelectedItem()).getID()), ((ReceptionBean) sp.getSelectedItem()).getName()};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Delete Receptionist");
        adb.setMessage(R.string.delmsg);
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rw = sb.delete(HospitalConstant.TBL_RNAME, HospitalConstant.COL_RID + " =? and " + HospitalConstant.COL_RNAME + " =?", args);
                if (rw > 0) {
                    Toast.makeText(DeleteReception.this, "Receptionist Details Deleted", Toast.LENGTH_LONG).show();
                    sp.setAdapter(null);
                    RList.clear();
                    fillList();
                    ad.notifyDataSetChanged();
                    sp.setAdapter(ad);
                }

            }
        });
        adb.setNegativeButton("No", null);
        adb.setIcon(R.drawable.reception);
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
