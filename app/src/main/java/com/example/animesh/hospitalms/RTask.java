package com.example.animesh.hospitalms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RTask extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lv;
    ArrayList<String> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtask);
        lv = (ListView) findViewById(R.id.lv);
        taskList = new ArrayList<>();

        fillList();
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
    }

    public void fillList(){
        taskList.add("ADD Patient Details");
        taskList.add("VIEW Patient Details");
        taskList.add("DELETE Patient");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mname = taskList.get(position);
        Toast.makeText(this, mname, Toast.LENGTH_LONG).show();
        if(position == 0){
            Intent i1 = new Intent(this, AddPatient.class);
            startActivity(i1);
        }
        if(position == 1){
            Intent i2 = new Intent(this, ViewPatient.class);
            startActivity(i2);
        }
        if(position == 2){
            Intent i4 = new Intent(this, DeletePatient.class);
            startActivity(i4);
        }

    }
}
