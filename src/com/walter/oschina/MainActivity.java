package com.walter.oschina;

import com.walter.app.AppManager;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //防止第三方跳转时出现双例
        Activity activity=AppManager.getActivity(cls)
    }

}
