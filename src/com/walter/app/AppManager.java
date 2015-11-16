package com.walter.app;

import java.util.Stack;

import android.app.Activity;
import android.content.Context;

/**
 * Activity堆栈式管理
 * 
 * @author walter
 * 
 */
public class AppManager {
	//使用堆栈管理Activity更符合Activity的本质，也可以用LinkedList，或者ArrayList(LinkedList更合适)
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			synchronized (AppManager.class) {
				if (instance == null) {
					instance = new AppManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 添加Activity到栈中
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获得当前的Activity 最后一个压入栈的
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null && !activity.isFinishing()) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;// 置为空，GC回收
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
				break;
			}
		}
	}

	/**
	 * 结束所有的Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				finishActivity(activityStack.get(i));
				break;
			}
		}
		activityStack.clear();
	}

	/**
	 * 获取指定的Activity
	 */
	public static Activity getActivity(Class<?> cls) {
		if (activityStack != null) {
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					return activity;
				}
			}
		}
		return null;
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context){
		try{
			finishAllActivity();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}catch(Exception e){
			
		}
	}
}
