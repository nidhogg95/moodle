package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.ks.moodle.R;
import com.example.ks.moodle.video.ControllerActivity;

public class TeacherVideo2_4 extends Activity {
    private TextView sun2_4Tv;

    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher2_4);
        sun2_4Tv=(TextView)findViewById(R.id.sun2_4);
        sun2_4Tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(TeacherVideo2_4.this, ControllerActivity.class);
                intent.putExtra("url","http://software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_04_01_08.mp4");
                startActivity(intent);
            }
        });
    }
}
