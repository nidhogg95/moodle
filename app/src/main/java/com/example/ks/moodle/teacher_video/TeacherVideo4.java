package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ks.moodle.R;

public class TeacherVideo4 extends Activity{
    private TextView chen4Tv;
    private TextView chener4Tv;
    private TextView qi4Tv;
    private TextView sun4Tv;

    @Override
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher1_4);
        chen4Tv=(TextView)findViewById(R.id.chen4);
        chen4Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo4.this,TeacherVideo4_1.class);
                startActivity(intent);
            }
        });

    }
}
