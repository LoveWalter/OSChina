package com.walter.oschina;

import com.walter.app.AppManager;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //��ֹ��������תʱ����˫��
        Activity activity=AppManager.getActivity(cls)
    }

}
