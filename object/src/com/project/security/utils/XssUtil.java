package com.project.security.utils;

public class XssUtil {
	public XssUtil() {
	}

	public static String getSafeStringXSS(String s) {
		if ((s == null) || ("".equals(s))) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '?':
				sb.append("&quot;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '&':
				sb.append("&quot;");
				break;
			case '\\':
				sb.append("&prime;");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
