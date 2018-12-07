package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ks.moodle.R;
import com.example.ks.moodle.entity.Teacher;
import com.example.ks.moodle.video.ControllerActivity;

public class TeacherVideo2 extends Activity{
    private TextView chenTv;
    private TextView meiTv;
    private TextView sunTv;
    private TextView qiTv;
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher1_2);
        chenTv=(TextView)findViewById(R.id.chen2);
        meiTv=(TextView)findViewById(R.id.mei3);
        sunTv=(TextView)findViewById(R.id.sun);
        qiTv=(TextView)findViewById(R.id.qi);
        chenTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo2.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.119/mp4files/1152000001553C81/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_01_02_01.mp4");
                startActivity(intent);
            }
        });
        meiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo2.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.120/mp4files/112000000155E8F1/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_01_02_05.mp4");
                startActivity(intent);

            }
        });
        sunTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo2.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.116/mp4files/2085000000C1E7ED/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_01_03_03.mp4");
                startActivity(intent);

            }
        });
        qiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo2.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.119/mp4files/1086000001568841/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/guofangkedaqzc_01_01_02.mp4");
                startActivity(intent);
            }
        });
    }
}
