package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintContextWrapper;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //属性
    TextView tvTarget;
    TextView tvSource;
    TextView tvIndex;
    SeekBar sbBulsseys;
    Button btnOk;
    Button btnReplay;
    Button btnHelp;
    int randomSource;
    int finalSource = 0;
    int index = 1;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //一般初始化都会独立成一个方法
        findView();
        randomOfSource();
        setListener();
  }

    private void setListener() {
        //为事件源设置监听
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击就会调用这个方法
                //算分数
                index++;
                int currentSource = sbBulsseys.getProgress();
                int source = 100 - Math.abs(currentSource-randomSource);
                finalSource = source + finalSource;
                setViewText();
                //按钮点击后就会重新出题
                randomOfSource();
            }
        });
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新生成随机数
                randomOfSource();
                finalSource = 0;
                index = 1;
                setViewText();
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Help")
                        .setMessage("这是帮助")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    //因为定义为属性了，不需要传参
    private void setViewText(){
        tvSource.setText("分数："+finalSource);
        tvIndex.setText("局数："+index);
        sbBulsseys.setProgress(0);
    }
    private void randomOfSource() {
        Random random = new Random();
        randomSource = random.nextInt(99) + 1;
        tvTarget.setText("小样将进度条拖到：" + randomSource);
    }

    private void findView() {
        tvTarget = (TextView)this.findViewById(R.id.tv_target);
        tvIndex =  (TextView)this.findViewById(R.id.tv_index);
        tvSource = (TextView)this.findViewById(R.id.tv_source);
        sbBulsseys = (SeekBar)this.findViewById(R.id.sb_bulsseye);
        btnHelp = (Button)this.findViewById(R.id.btn_help);
        btnOk = (Button)this.findViewById(R.id.btn_ok);
        btnReplay = (Button)this.findViewById(R.id.btn_replay);
    }
}
