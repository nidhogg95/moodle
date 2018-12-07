package com.example.ks.moodle.video;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ks.moodle.R;

import java.io.File;

public class VideoActivity extends Activity {
    private final String TAG="main";
    private EditText et_path;
    private Button btn_play,btn_pause,btn_replay,btn_stop;
    private SeekBar seekBar;//进度条
    private VideoView vv_video;//视频区域
    private boolean isPlaying;


    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_videoview);
        /**
         * 动态获取权限，Android 6.0新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }

        //找到相应的组件
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        et_path = (EditText) findViewById(R.id.et_path);
        vv_video = (VideoView) findViewById(R.id.vv_videoview);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_replay = (Button) findViewById(R.id.btn_replay);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        //为四个按钮设置监听事件
        btn_pause.setOnClickListener(click);
        btn_replay.setOnClickListener(click);
        btn_stop.setOnClickListener(click);
        btn_play.setOnClickListener(click);

        // 为进度条添加进度更改事件

        seekBar.setOnSeekBarChangeListener(change);
    }
    private SeekBar.OnSeekBarChangeListener change=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 当进度条停止修改的时候触
            // 取得当前进度条的刻度
            int progress=seekBar.getProgress();
            if(vv_video!=null&&vv_video.isPlaying()){
                //设置当前的播放位置
                vv_video.seekTo(progress);
            }

        }
    };

    private View.OnClickListener click=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_play:
                    play(0);
                    break;
                case R.id.btn_pause:
                    pause();
                    break;
                case R.id.btn_stop:
                    stop();
                    break;
                case R.id.btn_replay:
                    replay();
                    break;
                default:
                    break;
            }
        }
    };
    protected void play(int msec){
        Log.i(TAG,"获取视频文件地址");
        String path=et_path.getText().toString().trim();
        File file=new File(path);
        if(!file.exists()){
            Toast.makeText(this, "视频文件路径错误", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG,"指定视频源路径");
        vv_video.setVideoPath(file.getAbsolutePath());
        Log.i(TAG,"开始播放");
        vv_video.start();

        //按照初始位置播放
        vv_video.seekTo(msec);
        // 设置进度条的最大进度为视频流的最大播放时长
        seekBar.setMax(vv_video.getDuration());

        //开始线程
        new Thread(){
            @Override
            public void run(){
                try{
                    isPlaying=true;
                    while(isPlaying){
                        // 如果正在播放，没0.5.毫秒更新一次进度条
                        int current=vv_video.getCurrentPosition();
                        seekBar.setProgress(current);
                        sleep(500);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
        //播放之后设置播放按钮不可用
        btn_play.setEnabled(false);
        vv_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //在播放完毕后被回调
                btn_play.setEnabled(true);
            }
        });
        vv_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                //发生错误后重新播放
                play(0);
                isPlaying=false;
                return false;
            }
        });

    }
    /**
     * 重新开始播放
     */
    protected  void replay(){
        if(vv_video!=null&&vv_video.isPlaying()){
            vv_video.seekTo(0);
            Toast.makeText(this,"重新播放",Toast.LENGTH_SHORT).show();
            btn_pause.setText("暂停");
            return;
        }
        isPlaying=false;
        play(0);
    }
    /**
     * 暂停或继续
     */
    protected void pause(){
        if(btn_pause.getText().toString().trim().equals("继续")){
            btn_pause.setText("暂停");
            vv_video.start();
            Toast.makeText(this, "继续播放", Toast.LENGTH_SHORT).show();
            return;
        }
        if(vv_video!=null&&vv_video.isPlaying()){
            vv_video.pause();
            btn_pause.setText("继续");
            Toast.makeText(this,"暂停播放",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 停止播放
     */
    protected void stop(){
        if(vv_video!=null&&vv_video.isPlaying()){
            vv_video.stopPlayback();
            btn_play.setEnabled(true);
            isPlaying=false;
        }
    }




}
