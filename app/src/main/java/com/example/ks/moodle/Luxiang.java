package com.example.ks.moodle;

import android.app.Activity;
import android.graphics.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Calendar;

public class Luxiang extends Activity implements SurfaceHolder.Callback{
    private SurfaceView mSurfaceview;
    private Button mBtnStartStop;
    private Button mBtnPlay;
    private boolean mStartedFlg=false;//是否正在录像
    private boolean mIsPlay=false;//是否正在播放录像
    private MediaRecorder mRecorder;
    private SurfaceHolder mSurfaceHolder;
    private ImageView mImageView;
    private android.hardware.Camera camera;
    private MediaPlayer mediaPlayer;
    private String path;
    private TextView textView;
    private int text=0;
    private android.os.Handler handler=new android.os.Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            text++;
            textView.setText(text+"");
            handler.postDelayed(this,1000);
        }
    };

    @Override
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.video);
        mSurfaceview=(SurfaceView)findViewById(R.id.surfaceview);
        mImageView=(ImageView)findViewById(R.id.imageview);
        mBtnStartStop=(Button)findViewById(R.id.btnStartStop);
        mBtnPlay=(Button)findViewById(R.id.btnPlayVideo);
        textView=(TextView)findViewById(R.id.textok);
        mBtnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsPlay){
                    if(mediaPlayer!=null){
                        mIsPlay=false;
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                }
                if(!mStartedFlg){
                    handler.postDelayed(runnable,1000);
                    mImageView.setVisibility(View.GONE);
                    if(mRecorder==null){
                        mRecorder=new MediaRecorder();
                    }
                    camera = android.hardware.Camera.open(android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK);
                    if(camera!=null){
                        camera.setDisplayOrientation(90);
                        camera.unlock();
                        mRecorder.setCamera(camera);
                    }
                    try{
                        //这两项要放在setOutputFormat之前
                       // mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                        //setoutputFormat
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

                        // 这两项需要放在setOutputFormat之后
                        //mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                        mRecorder.setVideoSize(640, 480);
                        mRecorder.setVideoFrameRate(30);
                        mRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                        mRecorder.setOrientationHint(90);
                        //设置记录会话的最大持续时间（毫秒）
                        mRecorder.setMaxDuration(30 * 1000);
                        mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

                        path=getSDPath();
                        if(path!=null){
                            File dir=new File(path+"/recordtest");
                            if(!dir.exists()){
                                dir.mkdir();
                            }
                            path=dir+"/"+getDate()+".mp4";
                            mRecorder.setOutputFile(path);
                            mRecorder.prepare();
                            mRecorder.start();
                            mStartedFlg=true;
                            mBtnStartStop.setText("Stop");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    //stop
                    if(mStartedFlg){
                        try{
                            handler.removeCallbacks(runnable);
                            mRecorder.stop();
                            mRecorder.reset();
                            mRecorder.release();
                            mRecorder=null;
                            mBtnStartStop.setText("Start");
                            if(camera!=null){
                                camera.release();
                                camera=null;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    mStartedFlg=false;
                }
            }
        });
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsPlay=true;
                mImageView.setVisibility(View.GONE);
                if(mediaPlayer==null){
                    mediaPlayer=new MediaPlayer();
                }
                mediaPlayer.reset();
                Uri uri= Uri.parse(path);
                mediaPlayer=MediaPlayer.create(Luxiang.this,uri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDisplay(mSurfaceHolder);
                try{
                    mediaPlayer.prepare();
                }catch (Exception e){
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });
        SurfaceHolder holder=mSurfaceview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }
    @Override
    protected void onResume(){
        super.onResume();
        if(!mStartedFlg){
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取系统时间
     * @return
     */
    public static String getDate(){
        Calendar ca=Calendar.getInstance();
        int year=ca.get(Calendar.YEAR);
        int month=ca.get(Calendar.MONTH);
        int day=ca.get(Calendar.DATE);
        int minute=ca.get(Calendar.MINUTE);
        int hour=ca.get(Calendar.HOUR);
        int second=ca.get(Calendar.SECOND);

        String date=""+year+(month+1)+day+hour+minute+second;
        Log.d("ks","date:"+date);
        return date;

    }

    /**
     * 获取sd卡的路径
     * @return
     */
    public String getSDPath(){
        File sdDir=null;
        boolean sdCardExist= Environment.getExternalStorageState().equals(Environment
        .MEDIA_MOUNTED);
        if(sdCardExist){
            sdDir=Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return null;

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder=surfaceHolder;

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mSurfaceHolder=surfaceHolder;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mSurfaceview = null;
        mSurfaceHolder = null;
        handler.removeCallbacks(runnable);
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
            Log.d("ks", "surfaceDestroyed release mRecorder");
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    }

