package com.wash.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TempUtils {

	public static String urlencode(String url) {
		if (url.indexOf("/") > 0) {
			try {
				url = URLEncoder.encode(url, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
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
	 * 安全过滤，纯文本转换
	 * 
	 * @param str
	 * @return
	 */
	public static String textFilter(String str) {
		if (!"".equals(str) && str != null) {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			Pattern p_script = Pattern.compile(regEx_script,
					Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(str);
			str = m_script.replaceAll(""); // 过滤script标签
		}
		return str;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param propertieName
	 * @param key
	 * @return
	 */
	public static String getProp(String propertieName, String key) {
		TempUtils tempUtils = new TempUtils();
		InputStream inputStream = tempUtils.getClass().getClassLoader()
				.getResourceAsStream(propertieName + ".properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty(key, "查无此参数");
	}

	/**
	 * 
	 * 获取接口参数值
	 * 
	 * @param request
	 * @param param
	 * @return
	 */
	public static String getParameterValue(HttpServletRequest request,
			String param) {
		try {
			String value = request.getParameter(param);
			return textFilter(value == null ? "" : value);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 校验手机号
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * base64 加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	/**
	 * base64解密
	 * 
	 * @param s
	 * @return
	 */
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/** ------------------返回值处理---------------- **/
	public static void returnvalues(HttpServletResponse response, String value) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(value);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}