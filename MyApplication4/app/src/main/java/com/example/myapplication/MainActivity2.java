package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//이 엑티비티에선 메인 엑티비티에서 전송 받은 좌표로 api를 호출하고 listview에 띄워줌
//서울특별시_정류소정보조회 서비스
public class MainActivity2 extends AppCompatActivity {
    private Intent intent;
    private ArrayList<TransferItem> mylist;
    RecyclerViewAdapter myAdapter;
    ArrayList<RecyclerViewItem> Rylist;
    private TransferAPI transferAPI;

    private String Transfer_key = "uJVPZ36cG4TAmsXg9mpWZHtlod+uxSREmceXmb8+hOU2NDP2G2XcyW4KOua4/PMe+I1P5/MemCn1pNVoNQS8Iw=="; //환승시 사용하는 요청키
    private String type = "json"; // 요청타입
    /* private String startX = "126.9243"; // 홍익대학교 앞
      private String startY = "37.5528";
      private String endX = "126.928824"; // 연세대앞
      private String endY = "37.5681";

  */
    private String startX;// 홍익대학교 앞
    private String startY;
    private String endX;; // 연세대앞
    private String endY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = getIntent();
        startX = intent.getStringExtra("startX");
        startY = intent.getStringExtra("startY");
        endX = intent.getStringExtra("endX");
        endY = intent.getStringExtra("endY");
        Button button = findViewById(R.id.button);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        //mylist = new ArrayList<>();
        Rylist = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new RecyclerViewAdapter(Rylist);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(myAdapter);

        Transfer_Info();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0 ; i < myAdapter.getItemCount(); i++){
                    Log.d("i" , i+"번" );
                    Log.d("time" , Rylist.get(i).getTime());
                    Log.d("fname" ,Rylist.get(i).getFname());
                    Log.d("transferItem" , String.valueOf(Rylist.get(i).getTransferItems()));
                    Log.d("ItemTransferItem.time" , String.valueOf(Rylist.get(i).getTransferItems().get(0).getTime()));
                    for(int j = 0 ; j < Rylist.get(i).getTransferItems().get(0).getPathItemList().size();j++){
                        Log.d("transferItem 내부" , j+"번 째 transferItem 내부");
                        Log.d("버스번호" ,Rylist.get(i).getTransferItems().get(0).getPathItemList().get(j).getRouteNm());
                        Log.d("itemTransferItem fname" , Rylist.get(i).getTransferItems().get(0).getPathItemList().get(j).getFname());
                        Log.d("fx" , String.valueOf(Rylist.get(i).getTransferItems().get(0).getPathItemList().get(j).getFx()));
                        Log.d("fy" , String.valueOf(Rylist.get(i).getTransferItems().get(0).getPathItemList().get(j).getFy()));
                    }
                    //Log.d("size" , String.valueOf(Rylist.get(i).getTransferItems().get(0).getPathItemList().size()));
                    /*Log.d("버스번호" ,Rylist.get(i).getTransferItems().getPathItemList().get(0).getRouteNm());
                    Log.d("ItemTransferItem.time" , String.valueOf(Rylist.get(i).getTransferItems().getTime()));
                    Log.d("itemTransferItem fname" , Rylist.get(i).getTransferItems().getPathItemList().get(0).getFname());
                    Log.d("fx" , String.valueOf(Rylist.get(i).getTransferItems().getPathItemList().get(0).getFx()));
                    Log.d("fy" , String.valueOf(Rylist.get(i).getTransferItems().getPathItemList().get(0).getFy()));*/
                }

            }
        });

    }
    private void addItem(String time, String fname , ArrayList<TransferItem> transferItems){
        RecyclerViewItem item = new RecyclerViewItem();

        item.setTime(time);
        item.setFname(fname);
        item.setTransferItems(transferItems);

        Rylist.add(item);
    }


    //item list = 20개
    void Transfer_Info() {
        RetrofitTransferClient retrofitTransferClient = RetrofitTransferClient.getInstance();
        if (retrofitTransferClient != null) {
            transferAPI = RetrofitTransferClient.getTransferAPI();
            transferAPI.getTransfer(Transfer_key, startX, startY, endX, endY, type).enqueue(new Callback<Transfer>() {
                @Override
                public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                    if(response.isSuccessful()) {
                        mylist =  response.body().getTmsgBody().getItemList();
                        Log.d("String 결과값", "response.body().toString() : " + response.body().toString());
                        Collections.sort(mylist);
                        for(int i = 0 ; i <mylist.size() ; i++) {
                            if(mylist.get(i).getPathItemList().size() <= 2) {
                                ArrayList<TransferItem> temp = new ArrayList<>();
                                temp.add(mylist.get(i));
                                String time = String.valueOf(mylist.get(i).getTime());
                                String Fname = mylist.get(i).getPathItemList().get(0).getFname();
                                addItem(time , Fname , temp) ;
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                    else{
                        Log.d("onResponse 실패",  "onResponse 실패");
                    }
                }

                @Override
                public void onFailure(Call<Transfer> call, Throwable t) {
                    Toast.makeText(MainActivity2.this, "network failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}