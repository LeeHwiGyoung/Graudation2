package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//이 액티비티에선 stt로 검색하여서 다음 액티비티로 좌표 전송

/*private String startX = "126.9243"; // 홍익대학교 앞
   private String startY = "37.5528";
   private String endX = "126.928824"; // 연세대앞
   private String endY = "37.5681";*/
//http://ws.bus.go.kr/api/rest/pathinfo/getPathInfoByBus?serviceKey=uJVPZ36cG4TAmsXg9mpWZHtlod%2BuxSREmceXmb8%2BhOU2NDP2G2XcyW4KOua4%2FPMe%2BI1P5%2FMemCn1pNVoNQS8Iw%3D%3D&startX=126.9243&startY=37.5528&endX=126.928824&endY=37.5681&resultType=json
public class MainActivity extends AppCompatActivity {
    private String startX = "126.9243"; // 홍익대학교 앞
    private String startY = "37.5528";
    private String endX = "126.928824"; // 연세대앞
    private String endY = "37.5681";
    private Button button; // test button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.change);

        button.setOnClickListener(new View.OnClickListener(){ // 버튼 클릭하면
            @Override public void onClick(View view)
            {
                //String input = editText.getText().toString(); //editText에 입력한 문자열을 얻어 온다.
                //인텐트 선언 및 정의
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                //입력한 input값을 intent로 전달한다.
                intent.putExtra("startX", startX); // 다음 액티비티로 넘김
                intent.putExtra("startY", startY);
                intent.putExtra("endX", endX);
                intent.putExtra("endY", endY);

                //액티비티 이동
                startActivity(intent);
            }
        });
    }

}