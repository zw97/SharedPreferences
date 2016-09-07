package com.example.w.sharedpreferences;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //SharedPreferences文件名
    private final static String SharedPreferencesFileName="config";

    //键
    private final static String Key_UserName="UserName";//姓名
    private final static String Key_LoginDate="LoginDate";//登录时间
    private final static String Key_Number="Number";//学号

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得SharedPreferences实例
        preferences=getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor=preferences.edit();//获得SharedPreferences实例

        Button btnWrite=(Button)findViewById(R.id.ButtonWrite);
        Button btnRead=(Button)findViewById(R.id.ButtonRead);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                //格式化日期，将日期按照年月日时分秒格式转换为字符串形式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());

                //写入键值对
                editor.putString(Key_UserName, "Zhang wen");
                editor.putString(Key_LoginDate, strLoginDate);
                editor.putInt(Key_Number, 2014011214);

                //提交修改，此处换成commit()也可以
                editor.apply();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = preferences.getString(Key_UserName, null);
                String strLoginDate = preferences.getString(Key_LoginDate, null);
                int nNumber=preferences.getInt(Key_Number,0);
                if (strUserName != null && strLoginDate != null)
                    Toast.makeText(MainActivity.this, "用户名:" + strUserName + "  登录时间:" + strLoginDate+"  学号:"+nNumber, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
