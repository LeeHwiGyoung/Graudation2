package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private TextView fnametext;
    private TextView timetext;
    private Intent intent;
    private String time;
    private String fname;
    private TransferItem transferlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        intent = getIntent();
        time = intent.getStringExtra("time");
        fname = intent.getStringExtra("fname");
        transferlist = (TransferItem) intent.getSerializableExtra("transferItem");
        timetext = findViewById(R.id.textView3);
        fnametext = findViewById(R.id.textView4);

        timetext.setText(time + "을 버스타고 이동합니다.");
        fnametext.setText(fname + "에서 탑승합니다.");

        Log.d("transferItem 내부 time", String.valueOf(transferlist.getTime()));
        for (int i = 0; i < transferlist.getPathItemList().size(); i++) {
            Log.d("버스번호", transferlist.getPathItemList().get(i).getRouteNm());
            Log.d("itemTransferItem fname", transferlist.getPathItemList().get(i).getFname());
            Log.d("fx", String.valueOf(transferlist.getPathItemList().get(i).getFx()));
            Log.d("fy", String.valueOf(transferlist.getPathItemList().get(i).getFy()));
        }
    }
}