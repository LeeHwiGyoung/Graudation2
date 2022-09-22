package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<RecyclerViewItem> mData = null;
    private ViewHolder holder;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView fname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조
            time = itemView.findViewById(R.id.time);
            fname = itemView.findViewById(R.id.fname);

        }

        public TextView getFname() {
            return fname;
        }

        public TextView getTime() {
            return time;
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
        /*Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_holder_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, view);
        //RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view);

        return viewHolder;*/
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        //TransferItem item = mData.get(position);
        RecyclerViewItem item = mData.get(position);
        holder.time.setText(item.getTime()); //정류소 이름
        holder.fname.setText(item.getFname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(), MainActivity3.class);
                intent.putExtra("time" , mData.get(position).getTime());
                intent.putExtra("fname" , mData.get(position).getFname());
                intent.putExtra("transferItem", mData.get(position).getTransferItems().get(0));
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
