package com.wash.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.gp.query.common.MyMailServiceCenter;

public class EmailUtil{
	
	public static void main(String[] args) {
		System.out.println(new EmailUtil().execute("liuwm@mediapower.mobi",null,"测试多少收电费","参测试啊"));
	}
	//private String purport="測試發送郵件";//"測試發送郵件";//郵件主旨
	//private String content="測試發送郵件成功!";//"測試發送郵件成功!";//郵件內容
	public boolean execute(String to,String copyto,String purport,String content){
		boolean flag=false;
		String smtp="";//"csmail.casetekcorp.com";//服務器地址 
		String from="";//"Join_Liu@intra.casetekcorp.com";//發件人地址
		String username="";//用戶名
		String password="";//用戶密碼
		InputStream is=null;
		Properties pro=null;
		try {
		is=EmailUtil.class.getResourceAsStream("/dataSource.properties");
		pro=new Properties();
		pro.load(is);
		smtp=pro.getProperty("MAIL_HOST");
		System.out.println(smtp);
		from =pro.getProperty("SCHEDULE_FROMEMAIL");
	    username = pro.getProperty("SCHEDULE_USER");
	    password = pro.getProperty("SCHEDULE_PASS");
	    	if(MyMailServiceCenter.send(smtp,from,to,copyto,purport,content,username,password,null)){
				System.out.println("發送郵件成功");
				flag=true;
			}
		} catch (Exception e) {
			//發送失敗后再發一次,并打印錯誤時間
			flag=false;
			System.out.println("發送郵件失敗了"+new Date());
			content="發送郵件失敗了"+new Date();
			if(MyMailServiceCenter.send(smtp,from,to,copyto,purport,content,username,password,null)){
				System.out.println("發送郵件失敗了");
			}
			e.printStackTrace();
		}finally{
			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pro.clear();
		}
		return flag;
	}
	
	
}










