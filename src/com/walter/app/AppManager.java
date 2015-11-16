package com.walter.app;

import java.util.Stack;

import android.app.Activity;
import android.content.Context;

/**
 * Activity��ջʽ����
 * 
 * @author walter
 * 
 */
public class AppManager {
	//ʹ�ö�ջ����Activity������Activity�ı��ʣ�Ҳ������LinkedList������ArrayList(LinkedList������)
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * ����ģʽ
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
	 * ���Activity��ջ��
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * ��õ�ǰ��Activity ���һ��ѹ��ջ��
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * ������ǰActivity
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * ����ָ����Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null && !activity.isFinishing()) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;// ��Ϊ�գ�GC����
		}
	}

	/**
	 * ����ָ��������Activity
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
	 * �������е�Activity
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
	 * ��ȡָ����Activity
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
	 * �˳�Ӧ�ó���
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
