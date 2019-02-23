package com.baidu.api.sdk.android.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.api.Baidu;
import com.baidu.api.BaiduDialog.BaiduDialogListener;
import com.baidu.api.BaiduDialogError;
import com.baidu.api.BaiduException;
import com.baidu.api.Util;

public class MainActivity extends Activity {

    private Button oauthButton;

    private String clientId = "dnGkNQkQ1ndLGEf1rbzrkNBU";

    private Baidu baidu = null;

	//是否每次授权都强制登陆
	private boolean isForceLogin = false;

	private boolean isConfirmLogin = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oauthButton = (Button) findViewById(R.id.oauthButton);
        oauthButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {

                baidu = new Baidu(clientId, MainActivity.this);
                baidu.authorize(MainActivity.this, isForceLogin,isConfirmLogin,new BaiduDialogListener() {

                    @Override
                    public void onComplete(Bundle values) {
                        showTokenInfo();
                    }

                    @Override
                    public void onBaiduException(BaiduException e) {

                    }

                    @Override
                    public void onError(BaiduDialogError e) {

                    }

                    @Override
                    public void onCancel() {
	                    Util.logd("cancle","I am back");
                    }
                });
            }
        });
    }

    private void showTokenInfo() {
            Intent intent = new Intent(MainActivity.this,TokenInfoActivity.class);
            intent.putExtra("baidu", baidu);
            startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
