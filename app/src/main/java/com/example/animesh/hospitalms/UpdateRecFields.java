package com.example.animesh.hospitalms;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class UpdateRecFields extends AppCompatActivity implements View.OnClickListener {

    EditText txtphone, txtshift, txtrpass;
    String name;
    String shift;
    long phone;
    String rpass;
    Button btnsubmit;
    TextView txtrid, txtname;
    String args[];

    int id;
    HospitalManager hm;
    SQLiteDatabase sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rec_fields);
        Intent i = getIntent();
        hm = new HospitalManager(this);
        sb = hm.openDB();
        id = i.getIntExtra("sid", 0);
        name = i.getStringExtra("sname");

        txtrid = (TextView) findViewById(R.id.txtrid);
        txtname = (TextView) findViewById(R.id.txtname);
        txtrid.setText(String.valueOf(id));
        txtname.setText(name);
        args = new String[]{String.valueOf(id)};

        txtphone = (EditText)findViewById(R.id.txtphone);
        txtrpass = (EditText)findViewById(R.id.txtrpass);
        txtshift = (EditText) findViewById(R.id.txtshift);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);

        Cursor c = sb.query(HospitalConstant.TBL_RNAME, null, HospitalConstant.COL_RID + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            shift = c.getString(c.getColumnIndex(HospitalConstant.COL_RSHIFT));
            rpass = c.getString(c.getColumnIndex(HospitalConstant.COL_RPASS));
            phone = c.getLong(c.getColumnIndex(HospitalConstant.COL_RPHONE));
            c.close();
        }
        txtshift.setText(shift);
        txtrpass.setText(rpass);
        txtphone.setText(String.valueOf(phone));
        btnsubmit.setOnClickListener(this);
    }

//    public void getValues(){

//    }
    @Override
    public void onClick(View v) {
//        getValues();
        shift = txtshift.getText().toString();
        phone = Long.parseLong(txtphone.getText().toString());
        rpass = txtrpass.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(HospitalConstant.COL_RPHONE, phone);
        cv.put(HospitalConstant.COL_RSHIFT, shift);
        cv.put(HospitalConstant.COL_RPASS, rpass);
        long rw = sb.update(HospitalConstant.TBL_RNAME, cv, HospitalConstant.COL_RID+" =?", args);
        if (rw>0){
            Toast.makeText(this, "Records updated", Toast.LENGTH_LONG).show();
            UpdateRecFields.this.finish();
        }
    }
    protected void onDestroy(){
        super.onDestroy();

        if (hm!=null){
            hm.closeDB();
        }
    }
}
