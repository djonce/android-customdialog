package com.wangj.sample;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wangj.customdialog.CustomDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doShowDialog(View view)
    {
        new CustomDialog.Builder(this)
                .setTitle("Hello")         //设置标题
                .setMessage("我的测试信息")   // 设置提示正文
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "PositiveButton Click");
                    }
                })                        // 确认按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "NegativeButton Click");
                    }
                })                        // 取消按钮

                .setCancelable(true)      // 点击dialog外区域，是否取消显示
                .create().show();
    }
}
