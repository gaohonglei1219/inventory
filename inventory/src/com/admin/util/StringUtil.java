package com.admin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}

	/**
	 * 获取配置文件数据
	 * @param str 配置文件字段名称
	 * @return
	 */
	public static String getProperty(String str){
		Properties pro = new Properties();//属性集合对象
		String result="";

		InputStream in = StringUtil.class.getClassLoader().getResourceAsStream("/config.properties");


		try {
			if(null !=in){
				pro.load(in);
			}
			if(null !=pro && pro.containsKey(str)){
				result = pro.getProperty(str).trim();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
