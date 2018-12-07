package com.example.ks.moodle;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ks.moodle.entity.Student;
import com.example.ks.moodle.util.MyDatabaseHelper;
import com.example.ks.moodle.util.Register;

public class MyActivity extends Activity {

    private MyDatabaseHelper dbhelper;
    private TextView newUserTv;
    private Button loginBtn;
    private EditText xuehaoTv;
    private EditText pwdTv;
    private SQLiteDatabase db;
    private Student student;
    private TextView resetPwdTv;
    private CheckBox rememberTv;
    private MyDatabaseHelper myDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        xuehaoTv=(EditText)findViewById(R.id.xuehao);
        pwdTv=(EditText)findViewById(R.id.pwd);

        newUserTv=(TextView)findViewById(R.id.newUser);
        loginBtn=(Button)findViewById(R.id.login);
        resetPwdTv=(TextView)findViewById(R.id.resetPwd);
        rememberTv=(CheckBox)findViewById(R.id.remember);
        resetPwdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(MyActivity.this,ResetPass_Dialog.class);
               startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Login();
            }
        });
        newUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MyActivity.this,Register.class);
                startActivity(i);
            }
        });
        
        isRemember();


        //数据库的建立并且升级
        dbhelper=new MyDatabaseHelper(this,"moodle1.db",null,2);
        //创建数据库

        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put("id",2);
        //values.put("chapterName","第2章 可行性研究");
        /*values.put("id",3);
        values.put("chapterName","第3章 需求分析");
        values.put("id",4);
        values.put("chapterName","第4章 形式化说明技术");
        values.put("id",5);
        values.put("chapterName","第5章 总体设计");
        values.put("id",6);
        values.put("chapterName","第6章 详细设计");
        values.put("id",7);
        values.put("chapterName", "第7章 实现");
        values.put("id",8);
        values.put("chapterName","第8章 维护");
        values.put("id",9);
        values.put("chapterName","第9章 面向对象方法学引论");
        values.put("id",10);
        values.put("chapterName", "第10章 面向对象分析");
        values.put("id",11);
        values.put("chapterName","第11章 面向对象设计");
        values.put("id",12);
        values.put("chapterName","第12章 面向对象实现");*/

        /*{"1.1 软件危机", "1.2 软件工程", "1.3 软件生命周期", "1.4 软件过程"},
        {"2.1 可行性研究的任务", "2.2 可行性研究过程", "2.3 系统流程图", "2.4 数据流图", "2.5 数据字典", "2.6 成本/效益分析"},
        {"3.1 需求分析的任务", "3.2 与用户沟通获取需求的方法", "3.3 分析建模与规格说明", "3.4 实体联系图", "3.5 数据规范化", "3.6 状态转换图", "3.7 其他图形工具", "3.8 验证软件需求"},
        {"4.1 概述", "4.2 有穷状态机", "4.3 Petri 网", "4.4 Z语言"},
        {"5.1 设计过程", "5.2 设计原理", "5.3 启发规则", "5.4 描绘软件结构的图形工具", "5.5 面向数据流的设计方法"},
        {"6.1 结构程序设计", "6.2 人机界面设计", "6.3 过程设计的工具", "6.4 面向数据结构的设计方法", "6.5 程序复杂程度的定量度量"},
        {"7.1 编码", "7.2 软件测试基础", "7.3 单元测试", "7.4 集成测试", "7.5 确认测试", "7.6 白盒测试技术", "7.7 黑盒测试技术", "7.8 调试", "7.9 软件可靠性"},
        {"8.1 软件维护的定义", "8.2 软件维护的特点", "8.3 软件维护过程", "8.4 软件的可维护性", "8.5 预防性维护", "8.6 软件再工程过程"},
        {"9.1 面向对象方法学概述", "9.2 面向对象的概念", "9.4 对象模型", "9.6 功能模型"},
        {"10.1 面向对象分析的基本过程", "10.3 建立对象模型"},
        {"11.1 面向对象设计的准则", "11.5 设计问题域子系统", "11.6 设计人机交互子系统", "11.7 设计任务管理子系统", "11.8 设计数据管理子系统"},
        {"12.1 程序设计语言"}*/


        }




    /**
     * 判断是否保存过密码
     */

    private void isRemember() {
        SharedPreferences sharedPreferences=getSharedPreferences("remember",MODE_PRIVATE);
        boolean isRemember=sharedPreferences.getBoolean("remember",true);
        if(isRemember){
            xuehaoTv.setText(sharedPreferences.getString("userId",""));
            pwdTv.setText(sharedPreferences.getString("password",""));
            rememberTv.setChecked(true);
        }
    }

    /**
     * 无法登录？重置密码
     */
    private void ResetPwd() {


    }

    /**
     * 登录账号验证
     */
    private void Login(){
        String userId=xuehaoTv.getText().toString();
        String password=pwdTv.getText().toString();
        /*
        db=new MyDatabaseHelper(this,"moodle1.db",null,2).getReadableDatabase();
        Student student= (Student) db.rawQuery("select name from student where id=1801210729",null);

        student.setName("ning");
        Log.d("mo",student.getName());
*/
        if(userId.equals("")||password.equals("")){
            Toast.makeText(this,"请输入账号和密码",Toast.LENGTH_SHORT).show();
        }else{
            db = new MyDatabaseHelper(this, "moodle1.db", null, 2).getReadableDatabase();
            Cursor cursor=db.query("student",new String[]{"id","password"},null,null,null,null,null);
            boolean login=false;
            //从数据库中匹配学号和密码
            while(cursor.moveToNext()){
                if(userId.equals(cursor.getString(cursor.getColumnIndex("id")))&&
                password.equals(cursor.getString(cursor.getColumnIndex("password")))){
                    login=true;
                    break;
                }
            }
            //如果匹配成功
            if(login){





                Intent intent=new Intent(MyActivity.this,TestExpendListView.class);

                startActivity(intent);


            }else{
                Toast.makeText(this,"学号或密码错误",Toast.LENGTH_SHORT).show();
            }
        }

    }
}