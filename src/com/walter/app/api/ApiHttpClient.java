package com.walter.app.api;

import android.util.Log;
import android.util.TimingLogger;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.walter.app.util.TLog;

public class ApiHttpClient {
	public final static String HOST="www.oschina.net";
	private static String API_URL="http://www.oschina.net/%s";
	
	public static final String DELETE="DELETE";
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String PUT="PUT";
	public static AsyncHttpClient client;
	
	public ApiHttpClient(){}
	public static AsyncHttpClient getHttpClient(){
		return client;
	}
	
	public static void log(String log){
		Log.d("BaseApi",log);
		TLog.log("test", log);
	}
	public static void post(String partUrl,AsyncHttpResponseHandler handler){
		client.post(getAbsoluteApiUrl(partUrl), handler);
		log();
	}
	
	public static String getAbsoluteApiUrl(String partUrl){
		String url=String.format(API_URL, partUrl);
		Log.d("BASE_CLIENT","request:"+url);
		return url;
	}
	
}
