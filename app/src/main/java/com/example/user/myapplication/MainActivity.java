package com.example.user.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //btn点击事件
    private Button btnUpdates;
    //全局上下文
    private Context context;
    //从服务器获取的版本号
    private int versionCode;
    //apk下载地址
    private String apkUrl;
    //更新的内容
    private String upDatesManagers = "更新了XX版本，修复了XX问题";
    //  6.0读取内存卡权限声明
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
    }

    private void initView() {
        context = this;
        btnUpdates = (Button) findViewById(R.id.btn_updates);
        btnUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //6.0读取内存卡权限方法
                verifyStoragePermissions(MainActivity.this);
                //传入对应的参数 上下文、服务器获取的版本号、apk下载地址、更新内容、强制更新
                BaseAndroid.checkUpdate(context,versionCode,apkUrl,upDatesManagers,true);
            }
        });
    }

    //Android 6.0读取内存卡动态权限方法  Greated by liang  2017.07.24
    public static void verifyStoragePermissions(MainActivity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}
