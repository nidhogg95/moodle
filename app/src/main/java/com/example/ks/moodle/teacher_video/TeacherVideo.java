package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ks.moodle.R;
import com.example.ks.moodle.video.ControllerActivity;


//首先继承Activity
public class TeacherVideo extends Activity {

    private TextView chenTv; //定义第一个文本框
    private TextView meiTv; //定义第二个文本框

    //这个方法用来初始化界面布局的
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher1_1);//填写你刚刚建立的布局名字
        chenTv=(TextView)findViewById(R.id.chen1);//找到对应的第一个文本框
        meiTv=(TextView)findViewById(R.id.mei1);//找对应的第二个文本框

/*
        //为第一个文本框的内容设置点击事件
        chenTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //构造一个意图intent，有了意图才能界面跳转
                Intent intent=new Intent(TeacherVideo.this, MyActivity1.class);
                startActivity(intent);
            }
        });
        //为第二个文本框的内容设置点击事件
        meiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //构造一个意图intent，有了意图才能界面跳转
                Intent intent=new Intent(TeacherVideo.this, MyActivity1.class);
                startActivity(intent);
            }
        });
*/
        chenTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.118/mp4files/1245000001542D72/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/beidawlf_01_02_02.mp4");
                startActivity(intent);
            }
        });
        meiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherVideo.this, ControllerActivity.class);
                intent.putExtra("url","http://222.29.159.119/mp4files/1152000001553C81/software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_01_02_01.mp4");
                startActivity(intent);

            }
        });

    }

}
