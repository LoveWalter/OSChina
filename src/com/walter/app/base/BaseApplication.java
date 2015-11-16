package com.walter.app.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

public class BaseApplication extends Application{
	
	private static String PREF_NAME="createlocker.pref";
	private static String LAST_REFRESH_TIME="last_refresh_time.pref";
	static Context _context;
	static Resources _resource;
	private static String lastToast="";
	private static long lastToastTime;
	private static boolean sIsAtLeastGB;
	
	
	//ÅÐ¶Ï°æ±¾£¿
	static{
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.GINGERBREAD){
			sIsAtLeastGB=true;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		_context=getApplicationContext();
		_resource=_context.getResources();
	}
	
	public static synchronized BaseApplication context(){
		return (BaseApplication)_context;
	}
	
	public static Resources resources(){
		return _resource;
	}
}
