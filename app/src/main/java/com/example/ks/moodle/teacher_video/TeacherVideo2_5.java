package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.ks.moodle.R;
import com.example.ks.moodle.video.ControllerActivity;

public class TeacherVideo2_5 extends Activity {
    private TextView sun2_5Tv;

    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher2_5);
        sun2_5Tv=(TextView)findViewById(R.id.sun2_5);
        sun2_5Tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(TeacherVideo2_5.this, TeacherVideo2_5_1.class);
                startActivity(intent);
            }
        });
    }
}
