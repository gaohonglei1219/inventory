package com.admin.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.admin.util.http.HttpClient;
import com.admin.util.http.HttpParame;
import com.admin.util.http.HttpRequest;
import com.admin.util.http.HttpResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * HttpUtils
 *
 * @author: lrj
 * @since: 14-12-23 上午9:29
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 访问总后台
	 * @param content
	 * @return
	 */
	public  static   String httpClientAction(String content,String url){
		HttpClient httpClient = new HttpClient();
		HttpParame[] params = new HttpParame[1];
		HttpParame entity_source = new HttpParame("requestContext",content);
		params[0] = entity_source;
		HttpRequest httpRequest = new HttpRequest(StringUtil.getProperty("serviceEndPoint")+StringUtil.getProperty("clientName")+url, params,
				HttpRequest.MethodType.POST, null);
		HttpResponse httpResponse = httpClient.post(StringUtil.getProperty("serviceEndPoint")+StringUtil.getProperty("clientName")+url, params);
		logger.info("httpClientAction======================================"+httpResponse.getBody());
		return httpResponse.getBody();
	}
	public static String httpAction(String url, String params,
			String methodType, String encoding, String contentType) {
		if (Strings.isNullOrEmpty(encoding)) {
			encoding = "UTF-8";
		}
		if (Strings.isNullOrEmpty(methodType)) {
			methodType = "POST";
		}
		if (Strings.isNullOrEmpty(contentType)) {
			contentType = "ationapplic/json";
		}

		URL u = null;
		HttpURLConnection con = null;
		StringBuffer content = new StringBuffer();

		//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.neuedu.com", 8080));
		/*
		 * URL url1 = new URL("http://www.google.com.hk"); HttpURLConnection uc
		 * = (HttpURLConnection)url.openConnection(proxy); uc.connect();
		 */
		// 发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			// con.setConnectTimeout(3000);//连接主机的超时时间（单位：毫秒）
			// con.setReadTimeout(3000);//从主机读取数据的超时时间（单位：毫秒）
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Content-Type", contentType);
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("contentType", "utf-8");
			// con.setRequestProperty("Content-TypeWifiConstants.SPILT_STRapplication/json");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			// 构建请求参数
			if (methodType.equalsIgnoreCase("post")) {
				OutputStreamWriter osw = new OutputStreamWriter(
						con.getOutputStream(), encoding);
				osw.write(params);
				osw.flush();
				osw.close();
			}
			System.out.print(con.getInputStream().toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), encoding));
			String line = "";
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return content.toString();
	}

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
//	public static String doGet(String url, String queryString, String charset,
//			boolean pretty) {
//		logger.info("调用接口start！");
//		logger.info("url="+url+";queryString="+queryString+";charset="+charset+";pretty="+pretty);
//		logger.info("调用接口end！");
//		StringBuffer response = new StringBuffer();
//		HttpClient client = new HttpClient();
//		//client.getHostConfiguration().setProxy("proxy.neuedu.com", 8080); // 生产环境，要去除这项代理
//		HttpMethod method = new GetMethod(url);
//		try {
//			if (StringUtils.isNotBlank(queryString))
//				// 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
//				method.setQueryString(URIUtil.encodeQuery(queryString));
//			client.executeMethod(method);
//			if (method.getStatusCode() == HttpStatus.SC_OK) {
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(method.getResponseBodyAsStream(),
//								charset));
//				String line;
//				while ((line = reader.readLine()) != null) {
//					if (pretty)
//						response.append(line).append(
//								System.getProperty("line.separator"));
//					else
//						response.append(line);
//				}
//				reader.close();
//			}
//		} catch (URIException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			method.releaseConnection();
//		}
//		return response.toString();
//	}

}
