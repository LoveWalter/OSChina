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
		// 防止第三方跳转时出现双实例
		Activity aty = AppManager.getActivity(MainActivity.class);
		if (aty != null && !aty.isFinishing()) {
			finish();
		}
		View view = View.inflate(this, R.layout.app_start, null);
		setContentView(view);
		// 渐变展示启动屏
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
		// 获取当前版本信息，修改版本信息
		int cacheVersion = PreferenceHelper.readInt(this, "first_install",
				"first_install", -1);
		int currentVersion = TDevice.getVersionCode();
		//如果版本升级，清除图片缓存
		if (cacheVersion < currentVersion) {
			PreferenceHelper.write(this, "first_install", "first_install",
					currentVersion);
			cleanImageCache();
		}
	}

	//清空图片缓存
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
	 * 跳转,跳转的同时，上传打印日志文件
	 */
	private void redirectTo(){
		
		Intent uploadLog=new Intent(this,LogUpload.class);
		startService(uploadLog);
		
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
