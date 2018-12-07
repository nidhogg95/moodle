package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.ks.moodle.R;
import com.example.ks.moodle.video.ControllerActivity;

public class TeacherVideo2_5_1 extends Activity {
    private TextView shuzizidiandeneirongTv;
    private TextView shujvzidiandeyongtuTv;
    private TextView shuzizidiandeshixianTv;

    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher2_5_1);
        shuzizidiandeneirongTv=(TextView)findViewById(R.id.shuzizidiandeneirong);
        shujvzidiandeyongtuTv=(TextView)findViewById(R.id.shujvzidiandeyongtu);
        shuzizidiandeshixianTv=(TextView)findViewById(R.id.shuzizidiandeshixian);

        shuzizidiandeneirongTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(TeacherVideo2_5_1.this, ControllerActivity.class);
                intent.putExtra("url","http://software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_04_01_09.mp4");
                startActivity(intent);
            }
        });

        shujvzidiandeyongtuTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(TeacherVideo2_5_1.this, ControllerActivity.class);
                intent.putExtra("url","http://software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_04_01_10.mp4");
                startActivity(intent);
            }
        });

        shuzizidiandeshixianTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(TeacherVideo2_5_1.this, ControllerActivity.class);
                intent.putExtra("url","http://software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_04_01_19.mp4");
                startActivity(intent);
            }
        });
    }
}
