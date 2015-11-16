package com.walter.app.util;

import android.content.pm.PackageManager;

import com.walter.app.base.BaseApplication;

public class TDevice {

	// 手机网络类型
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	public static int getVersionCode() {
		int versionCode = 0;
		try {
			versionCode = BaseApplication
					.context()
					.getPackageManager()
					.getPackageInfo(BaseApplication.context().getPackageName(),
							0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			versionCode = 0;
		}
		return versionCode;
	}

	public static int getVersionCode(String packageName) {
		int versionCode;
		try {
			versionCode = BaseApplication.context().getPackageManager()
					.getPackageInfo(packageName, 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			versionCode = 0;
		}
		return versionCode;
	}

	public static String getVersionName() {
		String name = "";
		try {
			name = BaseApplication
					.context()
					.getPackageManager()
					.getPackageInfo(BaseApplication.context().getPackageName(),
							0).packageName;
		} catch (PackageManager.NameNotFoundException e) {
			name = "";
		}
		return name;
	}

	public static String getVersionName(String packageName){
		String name="";
		try{
			name=BaseApplication.context().getPackageManager().getPackageInfo(packageName, 0).versionName;
		}catch(PackageManager.NameNotFoundException e){
			name="";
		}
		return name;
	}
}
