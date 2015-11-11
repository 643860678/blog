package com.project.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssRequestWapper extends HttpServletRequestWrapper {

	public XssRequestWapper(HttpServletRequest request) {

		super(request);
	}

	public String getParameter(String key) {
		String tmp = super.getParameter(key);
		return XssUtil.getSafeStringXSS(tmp);
	}

	public String[] getParameterValues(String key) {
		String[] results = super.getParameterValues(key);
		if (results != null) {
			for (int i = 0; i < results.length; i++) {
				results[i] = XssUtil.getSafeStringXSS(results[i]);
			}
		}
		return results;
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value != null) {
			value = XssUtil.getSafeStringXSS(value);
		}
		return value;
	}
}
