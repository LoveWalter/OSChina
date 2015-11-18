package com.walter.app.api.remote;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.walter.app.api.ApiHttpClient;

public class OSChinaApi {

	private static void uploadLog(String data, String report,
			AsyncHttpResponseHandler handler) {
		RequestParams params=new RequestParams();
		params.put("app", "1");
		params.put("report", report);
		params.put("msg", data);
		ApiHttpClient
	}

}
