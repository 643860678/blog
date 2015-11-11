package com.wash.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseWrite {
	
	public static void write(HttpServletResponse response,String str){
		try {
			response.getWriter().write(str);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
