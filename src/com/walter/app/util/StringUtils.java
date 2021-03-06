package com.walter.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtils {
	/**
	 * 将一个InputStream流转换成字符串
	 * @param inputStream
	 * @return
	 */
	public static String toConvertString(InputStream inputStream){
		StringBuffer res=new StringBuffer();
		InputStreamReader isr=new InputStreamReader(inputStream);
		BufferedReader read=new BufferedReader(isr);
		try{
			String line;
			line=read.readLine();
			while(line!=null){
				res.append(line+"<br>");
				line=read.readLine();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(null != isr){
					isr.close();
				}
				if(null != read){
					read.close();
					read=null;
				}
				if(null != inputStream){
					inputStream.close();
					inputStream=null;
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return res.toString();
	}
	
	
	/**
	 * 判断给定字符串是否为空，空格、制表符、回车
	 * 换行符组成的字符串，若字符串为null或空字符串，返回true
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input){
		if(input==null || "".equals(input)){
			return true;
		}
		for(int i=0,length=input.length();i<length;i++){
			char c=input.charAt(i);
			if( c!=' ' && c !='\t' && c!='\r' && c!='\n'){
				return false;
			}
		}
		return true;
	}

}
