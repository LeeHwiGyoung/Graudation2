package com.example.myapplication;

import static com.example.myapplication.tts.SingleTonTTS.tts;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<RecyclerViewItem> mData = null;
    private ViewHolder holder;
    private long delay = 0; // 더블클릭용

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView routeNm;
        private TextView station;
        private TextView transferNm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조
            time = itemView.findViewById(R.id.time);
            routeNm = itemView.findViewById(R.id.routeNm);
            station = itemView.findViewById(R.id.station);
            transferNm = itemView.findViewById(R.id.transferNm);



        }
    }

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> data) {
        mData = data;
    }

    @NonNull
    @Override //viewholder 객체 생성
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_item,parent,false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        RecyclerViewItem item = mData.get(position);
        int num = item.getTransferItems().get(0).getPathItemList().size() - 1;
        String text = String.valueOf(num);
        holder.time.setText(item.getTime()); //정류소 이름
        holder.station.setText(item.getStation());
        holder.transferNm.setText(text);
        holder.routeNm.setText(item.getTransferItems().get(0).getPathItemList().get(0).getRouteNm());

        holder.itemView.setOnClickListener(new View.OnClickListener() { //다음 액티비티로 넘어감
            @Override
            public void onClick(View view) {
                /*if(System.currentTimeMillis() > delay){ //1번 클릭
                    delay = System.currentTimeMillis() + 300;
                    tts.speak();
                }*/
                Intent intent =  new Intent(view.getContext(), MainActivity3.class);
                intent.putExtra("time" , mData.get(holder.getAdapterPosition()).getTime());
                intent.putExtra("station" , mData.get(holder.getAdapterPosition()).getStation());
                intent.putExtra("transferItem", mData.get(holder.getAdapterPosition()).getTransferItems().get(0)); //이게 대부분이 정보를 갖고 있음
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }



}
