package com.example.animesh.hospitalms;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import dbutil.HospitalConstant;
import dbutil.HospitalManager;

public class AddPatient extends AppCompatActivity implements View.OnClickListener{

    EditText txtpid, txtname, txtage, txtphone, txtemail, txtaddress, txtadhar, txtregdoc, dob;
    RadioGroup rg;
    Spinner sp;
    RadioButton rb;
    String pid, name, phone, email, address, regdoc, gender, maritalstatus, dateofbirth, adhar, age;
    int radioid;
    Button btnsubmit;

    HospitalManager hm;
    SQLiteDatabase sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        txtpid = (EditText)findViewById(R.id.txtpid);
        txtname = (EditText)findViewById(R.id.txtname);
        txtphone = (EditText)findViewById(R.id.txtphone);
        txtage = (EditText)findViewById(R.id.txtage);
        txtemail = (EditText)findViewById(R.id.txtemail);
        txtaddress = (EditText)findViewById(R.id.txtaddress);
        txtadhar = (EditText)findViewById(R.id.txtadhar);
        txtregdoc = (EditText)findViewById(R.id.txtregdoc);
        rg = (RadioGroup) findViewById(R.id.rg);
        dob = (EditText) findViewById(R.id.dob);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        sp= (Spinner)findViewById(R.id.sp);

        hm = new HospitalManager(this);
        sb = hm.openDB();
    }

    public void getValues(){
        pid = txtpid.getText().toString();
        name = txtname.getText().toString();
        address = txtaddress.getText().toString();
        email = txtemail.getText().toString();
        age = txtage.getText().toString();
        regdoc = txtregdoc.getText().toString();
        phone = txtphone.getText().toString();
        maritalstatus = (String) sp.getSelectedItem();
        radioid = rg.getCheckedRadioButtonId();
        rb = (RadioButton)rg.findViewById(radioid);
        gender = rb.getText().toString();
        dateofbirth = dob.getText().toString();
        adhar = txtadhar.getText().toString();
    }

    @Override
    public void onClick(View v) {
        getValues();
        boolean fieldsOK = validate(new EditText[]{txtpid, txtname, txtphone, txtage, txtemail, txtaddress, txtadhar, txtregdoc, dob });
        if (fieldsOK == false || radioid == -1) {
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
        } else if (fieldsOK == true) {

            ContentValues cv = new ContentValues();
            cv.put(HospitalConstant.COL_PID, Integer.parseInt(pid));
            cv.put(HospitalConstant.COL_PNAME, name);
            cv.put(HospitalConstant.COL_PPHONE, Long.parseLong(phone));
            cv.put(HospitalConstant.COL_PDOB, dateofbirth);
            cv.put(HospitalConstant.COL_PAGE, Integer.parseInt(age));
            cv.put(HospitalConstant.COL_PGENDER, gender);
            cv.put(HospitalConstant.COL_PMARITAL, maritalstatus);
            cv.put(HospitalConstant.COL_PEMAIL, email);
            cv.put(HospitalConstant.COL_PADD, address);
            cv.put(HospitalConstant.COL_PADHAR, Long.parseLong(adhar));
            cv.put(HospitalConstant.COL_PDOCNAME, regdoc);

            long rw = sb.insert(HospitalConstant.TBL_PNAME, null, cv);
            if (rw > 0) {
                Toast.makeText(this, "Patient Details Row Inserted " + rw, Toast.LENGTH_LONG).show();
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setTitle("Success!");
                adb.setMessage("Patient Registration Successful");
                adb.setPositiveButton("OK", null);
                adb.setIcon(R.drawable.dna);
                AlertDialog ad = adb.create();
                ad.show();
                AddPatient.this.finish();
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

