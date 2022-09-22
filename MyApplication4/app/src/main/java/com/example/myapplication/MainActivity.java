package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//이 액티비티에선 stt로 검색하여서 다음 액티비티로 좌표 전송
//추가할 것 stt
public class MainActivity extends AppCompatActivity {
    private String startX = "126.9243"; // 홍익대학교 앞
    private String startY = "37.5528";
    private String endX = "126.928824"; // 연세대앞
    private String endY = "37.5681";
    private Button button; // 액티비티 전환 테스트 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.change);

        button.setOnClickListener(new View.OnClickListener(){ // 버튼 클릭하면
            @Override public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class); //인텐트 선언

                intent.putExtra("startX", startX); // 다음 액티비티로 넘기는 데이터
                intent.putExtra("startY", startY);
                intent.putExtra("endX", endX);
                intent.putExtra("endY", endY);

                //액티비티 이동
                startActivity(intent);
            }
        });
    }

}