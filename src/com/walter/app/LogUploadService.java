package com.walter.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.kymjs.kjframe.utils.FileUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.walter.app.util.StringUtils;

public class LogUploadService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		final File log=FileUtils.getSaveFile("OSChina", "OSCLog.log");
		String data=null;
		try{
			FileInputStream fis=new FileInputStream(log);
			data=StringUtils.toConvertString(fis);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		if(!StringUtils.isEmpty(data)){
			
		}
		
		
		return super.onStartCommand(intent, flags, startId);
	}

}
