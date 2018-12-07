package com.example.ks.moodle.teacher_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.ks.moodle.R;
import com.example.ks.moodle.video.ControllerActivity;

public class TeacherVideo2_6 extends Activity {
    private TextView chenerTv;

    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.teacher2_6);
        chenerTv = (TextView) findViewById(R.id.chener2_6);
        chenerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherVideo2_6.this, ControllerActivity.class);
                intent.putExtra("url", "http://software-moodle.oss-cn-qingdao.aliyuncs.com/moodle_vedio/zhedacy_06_06_10.mp4");
                startActivity(intent);
            }
        });
    }
}
