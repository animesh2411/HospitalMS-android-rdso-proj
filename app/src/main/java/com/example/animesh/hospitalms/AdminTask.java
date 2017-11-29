package com.example.animesh.hospitalms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminTask extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView txtmsg;
    ListView lv;
    ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_task);

        Intent obj = getIntent();
        String data = obj.getStringExtra("msg");
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        txtmsg.setText(data);
        lv = (ListView)findViewById(R.id.lv);

        taskList = new ArrayList<>();

        fillList();
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
    }

    public void fillList(){
        taskList.add("ADD Doctor Details");
        taskList.add("VIEW Doctor Details");
        taskList.add("UPDATE Doctor Details");
        taskList.add("DELETE Doctor");
        taskList.add("ADD Receptionist Details");
        taskList.add("VIEW Receptionist Details");
        taskList.add("UPDATE Receptionist Details");
        taskList.add("DELETE Receptionist");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mname = taskList.get(position);
        Toast.makeText(this, mname, Toast.LENGTH_LONG).show();

        switch (position){
            case 0:
                Intent i1 = new Intent(this, AddDoctor.class);
                startActivity(i1);
                break;
            case 1:
                Intent i2 = new Intent(this, ViewDoctor.class);
                startActivity(i2);
                break;
            case 2:
                Intent i3 = new Intent(this, UpdateDoctor.class);
                startActivity(i3);
                break;
            case 3:
                Intent i4 = new Intent(this, DeleteDoctor.class);
                startActivity(i4);
                break;
            //Reception
            case 4:
                Intent i5 = new Intent(this, AddReception.class);
                startActivity(i5);
                break;
            case 5:
                Intent i6 = new Intent(this, ViewReceptionist.class);
                startActivity(i6);
                break;
            case 6:
                Intent i7 = new Intent(this, UpdateReception.class);
                startActivity(i7);
                break;
            case 7:
                Intent i8 = new Intent(this, DeleteReception.class);
                startActivity(i8);
                break;


        }
    }

}
