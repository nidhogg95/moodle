package com.example.ks.moodle;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ks.moodle.util.MyDatabaseHelper;

public class ResetPass_Dialog extends Activity implements View.OnClickListener {
    private Context context;
    private EditText resetIdTv;
    private EditText resetNewPwdTv;
    private EditText resetNewPwdTv2;
    private Button resetBtn;
    private Button backBtn;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        this.setContentView(R.layout.resetpwd_dialog);
        resetIdTv=(EditText)findViewById(R.id.xuehao2);
        resetNewPwdTv=(EditText)findViewById(R.id.mima2);
        resetNewPwdTv2=(EditText)findViewById(R.id.newmima);
        resetBtn=(Button)findViewById(R.id.reset);
        backBtn=(Button)findViewById(R.id.back2);
        resetBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.reset:
                if(isResetPass()){
                   Intent i=new Intent(ResetPass_Dialog.this,MyActivity.class);
                   startActivity(i);
                }
                break;
            case R.id.back2:
               Intent i=new Intent(ResetPass_Dialog.this,MyActivity.class);
               startActivity(i);
                break;
        }

    }

    /**
     * 账号与改密口令验证
     * 修改密码
     * @return
     */
    private boolean isResetPass() {
        boolean isReSetPassFinish=false;
        String userId=resetIdTv.getText().toString();
        String passwd=resetNewPwdTv.getText().toString();
        String newPwd=resetNewPwdTv2.getText().toString();

        if(userId.equals("")||passwd.equals("")||newPwd.equals("")){
            Toast.makeText(this,"请输入改密所需的每项内容",Toast.LENGTH_SHORT).show();
        }else{
            db=new MyDatabaseHelper(this,"moodle1.db",null,2).getReadableDatabase();
            Cursor cursor=db.query("student",new String[]{"id"},null,null,null,null,null);
            //验证学号是否存在
            while (cursor.moveToNext()){
                if(userId.equals(cursor.getString(cursor.getColumnIndex("id")))){
                    ContentValues values=new ContentValues();
                    values.put("password",newPwd);
                    db.update("student",values,"id=?",new String[]{userId});
                    Toast.makeText(this,"密码修改成功!\n请牢记密码并使用新密码登录",Toast.LENGTH_SHORT).show();
                    isReSetPassFinish=true;
                    break;
                }
            }
            if(!isReSetPassFinish){
                Toast.makeText(this,"学号不存在",Toast.LENGTH_SHORT).show();
            }
        }
        return isReSetPassFinish;
    }
}
