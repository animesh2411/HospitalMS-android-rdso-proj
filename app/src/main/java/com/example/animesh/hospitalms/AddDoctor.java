package com.example.animesh.hospitalms;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class AddDoctor extends AppCompatActivity implements View.OnClickListener {

    EditText txtdid, txtname, txtage, txtphone, txtemail, txtaddress, txtspec, txtqualification;
    RadioGroup rg;
    Spinner sp;
    RadioButton rb;
    String did, name, age, phone, email, address, specs, qualification, gender, maritalstatus;
    int radioid;
    Button btnsubmit;
    boolean checked;
    String data, days="";
    CheckBox chksun, chksat, chkmon, chktue, chkwed, chkthu, chkfri;

    HospitalManager hm;
    SQLiteDatabase sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        txtdid = (EditText)findViewById(R.id.txtdid);
        txtname = (EditText)findViewById(R.id.txtname);
        txtphone = (EditText)findViewById(R.id.txtphone);
        txtage = (EditText)findViewById(R.id.txtage);
        txtemail = (EditText)findViewById(R.id.txtemail);
        txtaddress = (EditText)findViewById(R.id.txtaddress);
        txtspec = (EditText)findViewById(R.id.txtspec);
        txtqualification = (EditText)findViewById(R.id.txtqualification);
        rg = (RadioGroup) findViewById(R.id.rg);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        sp= (Spinner)findViewById(R.id.sp);
        chksun = (CheckBox) findViewById(R.id.chksun);
        chkmon = (CheckBox) findViewById(R.id.chkmon);
        chktue = (CheckBox) findViewById(R.id.chktue);
        chkwed = (CheckBox) findViewById(R.id.chkwed);
        chkthu = (CheckBox) findViewById(R.id.chkthu);
        chkfri = (CheckBox) findViewById(R.id.chkfri);
        chksat = (CheckBox) findViewById(R.id.chksat);

        hm = new HospitalManager(this);
        sb = hm.openDB();

    }

    public void getValues(){
        did = txtdid.getText().toString();
        name = txtname.getText().toString();
        age = txtage.getText().toString();
        address = txtaddress.getText().toString();
        email = txtemail.getText().toString();
        phone = txtphone.getText().toString();
        specs = txtspec.getText().toString();
        qualification = txtqualification.getText().toString();
        maritalstatus = (String) sp.getSelectedItem();
        radioid = rg.getCheckedRadioButtonId();
        rb = (RadioButton)rg.findViewById(radioid);
        gender = rb.getText().toString();
    }
    @Override
    public void onClick(View v) {
        getValues();
        boolean fieldsOK = validate(new EditText[]{txtdid, txtname, txtphone, txtage, txtemail, txtaddress, txtqualification, txtspec});
        if (fieldsOK==false || radioid==-1){
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        }
        else if (fieldsOK == true) {
            ContentValues cv = new ContentValues();
            cv.put(HospitalConstant.COL_DID, Integer.parseInt(did));
            cv.put(HospitalConstant.COL_DNAME, name);
            cv.put(HospitalConstant.COL_DPHONE, Long.parseLong(phone));
            cv.put(HospitalConstant.COL_DAGE, Integer.parseInt(age));
            cv.put(HospitalConstant.COL_DGENDER, gender);
            cv.put(HospitalConstant.COL_DMARITAL, maritalstatus);
            cv.put(HospitalConstant.COL_DEMAIL, email);
            cv.put(HospitalConstant.COL_DADD, address);
            cv.put(HospitalConstant.COL_DQUALIFICATION, qualification);
            cv.put(HospitalConstant.COL_DSPECS, specs);
            cv.put(HospitalConstant.COL_DAVAIL, days);

            long rw = sb.insert(HospitalConstant.TBL_DNAME, null, cv);
            if (rw > 0) {
                Toast.makeText(this, "Doctor Details Row Inserted " + rw, Toast.LENGTH_LONG).show();

                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setTitle("Success!");
                adb.setMessage("Doctor Addition Successful");
                adb.setPositiveButton("OK", null);
                adb.setIcon(R.drawable.doc);
                AlertDialog ad = adb.create();
                ad.show();
                AddDoctor.this.finish();
            }
        }

    }

    public void onCheckboxClicked(View view) {
        checked = ((CheckBox) view).isChecked();
        if (view.getId() == R.id.chksun) {
            data = chksun.getText().toString();
            days+=data+", ";
        }
        if (view.getId() == R.id.chkmon) {
            data = chkmon.getText().toString();
            days+=data+", ";
        }
        if (view.getId() == R.id.chktue) {
            data = chkthu.getText().toString();
            days+=data+", ";
        }
        if (view.getId() == R.id.chkwed) {
            data = chkwed.getText().toString();
            days+=data+", ";
        }
        if (view.getId() == R.id.chkthu) {
            data = chkthu.getText().toString();
            days+=data+", ";
        }
        if (view.getId() == R.id.chkfri) {
            data = chkfri.getText().toString();
            days+=data+", ";
        }
        if (view.getId() == R.id.chksat) {
            data = chksat.getText().toString();
            days+=data+", ";
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
