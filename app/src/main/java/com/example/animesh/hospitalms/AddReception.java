package com.example.animesh.hospitalms;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class AddReception extends AppCompatActivity implements View.OnClickListener {
    EditText txtrid, txtname, txtphone, txtshift, txtrpass;
    String rid, name, shift, phone, rpass;
    Button btnsubmit;

    HospitalManager hm;
    SQLiteDatabase sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reception);
        txtrid = (EditText) findViewById(R.id.txtrid);
        txtname = (EditText) findViewById(R.id.txtname);
        txtphone = (EditText) findViewById(R.id.txtphone);
        txtrpass = (EditText) findViewById(R.id.txtrpass);
        txtshift = (EditText) findViewById(R.id.txtshift);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);

        hm = new HospitalManager(this);
        sb = hm.openDB();
    }

    public void getValues() {
        rid = txtrid.getText().toString();
        name = txtname.getText().toString();
        shift = txtshift.getText().toString();
        phone = txtphone.getText().toString();
        rpass = txtrpass.getText().toString();
    }

    @Override
    public void onClick(View v) {
        getValues();
        boolean fieldsOK = validate(new EditText[]{txtrid, txtname, txtphone, txtrpass, txtshift});
        if (fieldsOK==false){
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        }
        else if (fieldsOK == true) {

            ContentValues cv = new ContentValues();
        cv.put(HospitalConstant.COL_RID, Integer.parseInt(rid));
        cv.put(HospitalConstant.COL_RPASS, rpass);
        cv.put(HospitalConstant.COL_RNAME, name);
        cv.put(HospitalConstant.COL_RSHIFT, shift);
        cv.put(HospitalConstant.COL_DPHONE, Long.parseLong(phone));

        long rw = sb.insert(HospitalConstant.TBL_RNAME, null, cv);
        if (rw > 0) {
            Toast.makeText(this, "Receptionist Details Row Inserted " + rw, Toast.LENGTH_LONG).show();

            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Success!");
            adb.setMessage("Receptionist Registration Successful");
            adb.setPositiveButton("OK", null);
            adb.setIcon(R.drawable.reception);
            AlertDialog ad = adb.create();
            ad.show();
            AddReception.this.finish();
        }
    }
}
    private boolean validate(EditText[] fields){
        for(int i=0; i<fields.length; i++){
            EditText currentField=fields[i];
            if(currentField.getText().toString().length()<=0){
                return false;
            }
        }
        return true;
    }
    protected void onDestroy(){
        super.onDestroy();

        if (hm!=null){
            hm.closeDB();
        }
    }
}

