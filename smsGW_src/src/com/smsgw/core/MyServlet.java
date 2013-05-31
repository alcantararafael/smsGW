package com.smsgw.core;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.TextView;

public class MyServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public PendingIntent sentPI;
	public PendingIntent deliveredPI;
		
	public MyServlet(PendingIntent sentPI, PendingIntent deliveredPI){
		this.sentPI=sentPI;
		this.deliveredPI=deliveredPI;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();

        
        String phone=req.getParameter("phone");
        String sms=req.getParameter("sms");            

        if (phone==null || sms==null){
        	out.println(createForm());
        }else{
        	out.println("phone:"+phone+";sms:"+sms);
        	
        	SmsManager smsManager = SmsManager.getDefault();
        	try {
				this.sentPI.send(666);
			} catch (CanceledException e) {
				e.printStackTrace();
			}
            smsManager.sendTextMessage(phone, null, sms, this.sentPI, this.deliveredPI);
        }
    	out.close();
	}

	public String createForm(){
		String form="";
		
		form+="<style>\n";
		form+="* {font: 13px/20px 'HelveticaNeue','Helvetica Neue',Helvetica,Arial,sans-serif;}\n";
		
		form+="body {background-color: #C0DEED; background-image: url(https://si0.twimg.com/images/themes/theme1/bg.png); background-repeat: no-repeat; background-attachment: fixed;}\n";
		
		form+="form {width: 200px; text-align:center; border-left: solid 1px #EEE; border-right: solid 1px #EEE; border-bottom: solid 1px #CCC;\n";
		form+="background: -webkit-linear-gradient(top,white 0,#DDD 100%); background: -moz-linear-gradient(top,white 0,#DDD 100%); background: -ms-linear-gradient(top,white 0,#DDD 100%); background: -o-linear-gradient(top,white 0,#DDD 100%); background: linear-gradient(top,white 0,#DDD 100%); \n";
		form+="position: fixed; top: 30%; left: 50%; margin-top: -50px; margin-left: -100px; } \n";
		
		form+="div {width:auto;}\n";
		
		form+="input {width:150px;}\n";
		
		form+="textarea {width:150px; height:200px;}\n";
		form+="</style>\n";
		
		form+="<form action='/smsgw'>\n";
		form+="<div>DDD+Phone (ex.: 1199669666):</div> <input type='text' name='phone' maxlength='13'/> <br/>\n";
		form+="<div>SMS:</div><textarea name='sms' maxlength='200'></textarea> <br/>\n";
		form+="<input type='submit' value='Send SMS!' />\n";
		form+="</form>\n";
		
		return form;
	}
}