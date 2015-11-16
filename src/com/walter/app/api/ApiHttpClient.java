package com.walter.app.api;

import com.loopj.android.http.AsyncHttpClient;

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
	
}
