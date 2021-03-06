package org.limingnihao.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static final String CONTENT_TYPE_PLAIN = "text/plain";
	public static final String CONTENT_TYPE_XML = "text/xml";
	public static final String CONTENT_TYPE_HTML = "text/html";
	public static final String CONTENT_TYPE_JS = "text/javascript";
	public static final String CONTENT_TYPE_APP_JSON = "application/json";
	public static final String CONTENT_TYPE_APP_EXCEL = "application/vnd.ms-excel";
	public static final String CONTENT_TYPE_APP_FORM = "application/x-www-form-urlencoded";
	private static final String DEFAULT_ENCODING = "UTF-8";

	private static final int TIME_OUT_HTTP = 5000;

	public static void main(String args[]) {
		String httpUrl = "http://127.0.0.1:7080/com.longshine.whb.manager/interface/testFile.do";
		// String httpUrl = "http://192.168.1.19:8080/com.longshine.met.manager/interface/getCurrentSystemDateTime.do";
		Map<String, String> params = new HashMap<String, String>();
		// params.put("talkId", "1");
		// params.put("userId", "1");
		String a = sendApacheUploadFile(httpUrl, "d:\\Users\\limingnihao\\Pictures\\RB11021203_8.jpg", params);
		System.out.println(a);
	}

	/**
	 * 使用apache上传文件
	 */
	public static String sendApacheUploadFile(String url, String filePath, Map<String, String> params) {
		logger.info("uploadFile - url=" + url + ", filePath=" + filePath);

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				logger.info("uploadFile - file error - url=" + url + ", filePath=" + filePath);
				return null;
			}
			List<StringPart> partList = new ArrayList<StringPart>();
			if (params != null && params.size() > 0) {
				Iterator<String> it = params.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					String value = params.get(key);
					partList.add(new StringPart(key, value));
				}
			}
			Part[] parts = new Part[partList.size() + 1];
			for (int i = 0; i < partList.size(); i++) {
				parts[i] = partList.get(i);
			}
			parts[parts.length - 1] = new FilePart("fileData", file);
			postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(HttpUtil.TIME_OUT_HTTP);
			httpClient.executeMethod(postMethod);
			int status = postMethod.getStatusCode();
			logger.info("uploadFile - url=" + url + ", filePath=" + filePath + ", status=" + status);
			if (status == HttpStatus.SC_OK) {
				InputStream inputStream = postMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = br.readLine()) != null) {
					stringBuffer.append(str);
				}
				String response = stringBuffer.toString();
				logger.info("uploadFile - url=" + url + ", filePath=" + filePath + ", response=" + response);
				return response;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 使用jdk的http的get请求
	 */
	public static String sendGetHttpRequest(String endpoint) {
		String result = null;
		try {
			URL url = new URL(endpoint);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(HttpUtil.TIME_OUT_HTTP);
			InputStreamReader reader = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
			BufferedReader bReader = new BufferedReader(reader);
			StringBuffer resultBuffer = new StringBuffer();
			String line;
			while ((line = bReader.readLine()) != null) {
				resultBuffer.append(line);
			}
			bReader.close();
			result = resultBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 使用jdk的http的post请求
	 */
	public static String sendPostHttpRequest(String endpoint, Map<String, String> params) {
		String result = null;
		try {
			HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(endpoint).openConnection();
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setConnectTimeout(5000);
			httpUrlConnection.setReadTimeout(5000);
			httpUrlConnection.setRequestMethod("POST");

			String paramString = "";
			if (params != null && params.size() > 0) {
				Iterator<String> it = params.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					String value = params.get(key);
					paramString += (key + "=" + value + "&");
				}
				paramString = paramString.substring(0, paramString.length() - 1);
			}
			DataOutputStream writer = new DataOutputStream(httpUrlConnection.getOutputStream());
			writer.write(paramString.getBytes("UTF-8"));
			writer.flush();
			writer.close();
			BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) httpUrlConnection.getInputStream(), DEFAULT_ENCODING));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			httpUrlConnection.disconnect();
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 使用apache的httpapi请求post
	 */
	public static String sendApachePostRequest(String url, Map<String, String> params) {
		String response = null;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			List<StringPart> partList = new ArrayList<StringPart>();
			if (params != null && params.size() > 0) {
				Iterator<String> it = params.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					String value = params.get(key);
					partList.add(new StringPart(key, value));
				}
			}
			Part[] parts = new Part[partList.size()];
			for (int i = 0; i < partList.size(); i++) {
				parts[i] = partList.get(i);
			}
			postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(HttpUtil.TIME_OUT_HTTP);
			httpClient.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				InputStream inputStream = postMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = br.readLine()) != null) {
					stringBuffer.append(str);
				}
				response = stringBuffer.toString();
				return response;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.info("http请求失败， url=" + url + ", e=" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			postMethod.releaseConnection();
		}
	}

	/**
	 * 获取客户端IP
	 */
	public static String getClientAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取服务器IP地址
	 */
	public static String getServerAddress(HttpServletRequest request) {
		String serverAddress = request.getScheme() + "://" + request.getServerName();
		return serverAddress;
	}
}
