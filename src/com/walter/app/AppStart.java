package com.walter.app;

import java.io.File;

import org.kymjs.kjframe.http.KJAsyncTask;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.PreferenceHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.walter.app.util.TDevice;
import com.walter.oschina.MainActivity;
import com.walter.oschina.R;

public class AppStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ֹ��������תʱ����˫ʵ��
		Activity aty = AppManager.getActivity(MainActivity.class);
		if (aty != null && !aty.isFinishing()) {
			finish();
		}
		View view = View.inflate(this, R.layout.app_start, null);
		setContentView(view);
		// ����չʾ������
		AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
		aa.setDuration(800);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				 redirectTo();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ��ȡ��ǰ�汾��Ϣ���޸İ汾��Ϣ
		int cacheVersion = PreferenceHelper.readInt(this, "first_install",
				"first_install", -1);
		int currentVersion = TDevice.getVersionCode();
		//����汾���������ͼƬ����
		if (cacheVersion < currentVersion) {
			PreferenceHelper.write(this, "first_install", "first_install",
					currentVersion);
			cleanImageCache();
		}
	}

	//���ͼƬ����
	private void cleanImageCache(){
		final File folder=FileUtils.getSaveFolder("OSChina/imagecache");
		KJAsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				for(File file :folder.listFiles()){
					file.delete();
				}
			}
		});
	}
	/**
	 * ��ת,��ת��ͬʱ���ϴ���ӡ��־�ļ�
	 */
	private void redirectTo(){
		
		Intent uploadLog=new Intent(this,LogUpload.class);
		startService(uploadLog);
		
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
