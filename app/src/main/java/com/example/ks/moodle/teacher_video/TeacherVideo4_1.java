package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ks.moodle.R;
import com.example.ks.moodle.video.ControllerActivity;

public class TeacherVideo4_1 extends Activity{
    private TextView pubuTv;
    private TextView zengliangTv;
    private TextView luoxuanTv;
    private TextView rantionTv;
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher1_4_1);
        pubuTv=(TextView)findViewById(R.id.pubu);
        zengliangTv=(TextView)findViewById(R.id.zengliang);
        luoxuanTv=(TextView)findViewById(R.id.luoxuan);
        rantionTv=(TextView)findViewById(R.id.rational);
        pubuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherVideo4_1.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.120/mp4files/518400000157F562/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_01_05_04.mp4");
                startActivity(intent);
            }
        });
        zengliangTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherVideo4_1.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.120/mp4files/20570000015E86E2/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_01_05_05.mp4");
                startActivity(intent);

            }
        });
        luoxuanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherVideo4_1.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.120/mp4files/20570000015E86E2/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_01_05_08.mp4");
                startActivity(intent);
            }
        });
        rantionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherVideo4_1.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.120/mp4files/20570000015E86E2/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_01_05_11.mp4");
                startActivity(intent);
            }
        });

    }
}
