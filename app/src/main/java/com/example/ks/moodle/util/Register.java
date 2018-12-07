package com.example.ks.moodle.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ks.moodle.MyActivity;
import com.example.ks.moodle.R;

public class Register extends Activity {
    private Button register_go;
    private EditText useridTv;
    private EditText pwdTv;
    private EditText pwdTv2;
    private EditText nameTv;
    private EditText descTv;
    private MyDatabaseHelper myDatabaseHelper;
    private Button backBtn;



    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.regist);
        register_go=(Button)findViewById(R.id.regist);
        useridTv=(EditText)findViewById(R.id.userid);
        pwdTv=(EditText)findViewById(R.id.password);
        pwdTv2=(EditText)findViewById(R.id.password2);
        nameTv=(EditText)findViewById(R.id.name);
        descTv=(EditText)findViewById(R.id.desc);
        backBtn=(Button)findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Register.this,MyActivity.class);
                startActivity(i);
            }
        });
        register_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterSave();
            }
        });

    }

    private void RegisterSave() {
        String userId=useridTv.getText().toString();
        String password1=pwdTv.getText().toString();
        String password2=pwdTv2.getText().toString();
        String name=nameTv.getText().toString();
        String desc=descTv.getText().toString();
        boolean creatUser=true;
        if(userId.equals("")||password1.equals("")||password2.equals("")||name.equals("")){
            Toast.makeText(this,"请完整输入各项注册内容",Toast.LENGTH_SHORT).show();
        }else if(!password1.equals(password2)){
            Toast.makeText(this,"两次密码输入不一致，请重新输入",Toast.LENGTH_SHORT).show();
        }else if(password1.length()<6){
            Toast.makeText(this, "密码小于六位数，请重新输入", Toast.LENGTH_SHORT).show();

        }else{
            myDatabaseHelper=new MyDatabaseHelper(Register.this,"moodle1.db",null,2);
            SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
            Cursor cursor=db.query("student",new String[]{"id"},null,null,null,null,null);
            while (cursor.moveToNext()){
                if(userId.equals(cursor.getString(cursor.getColumnIndex("id")))){
                    Toast.makeText(this, "该学生已经存在", Toast.LENGTH_SHORT).show();
                    creatUser=false;
                }
            }
            if(creatUser){
                ContentValues values=new ContentValues();
                values.put("id",userId);
                values.put("password",password1);
                values.put("name",name);
                values.put("description",desc);
                db.insert("student",null,values);
                AlertDialog.Builder dialog=new AlertDialog.Builder(Register.this);
                dialog.setTitle("注册成功");
                dialog.setMessage("您已成功注册账户，请返回登录界面");
                dialog.setPositiveButton("返回登录界面", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.setNegativeButton("留在注册界面", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        }

    }
}
