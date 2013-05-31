package com.smsgw.core;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class SmsGWActivity extends Activity {
	public static final String SENT = "SMS_SENT";
	public static final String DELIVERED = "SMS_DELIVERED";
	
	public PendingIntent sentPI;
	public PendingIntent deliveredPI;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        smsSender();
    }
    
    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    public void smsSender(){
    	this.sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SmsGWActivity.SENT), 0);
    	this.deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(SmsGWActivity.DELIVERED), 0);
    	    	
    	String ip=getLocalIpAddress();
        new MyServer(this.sentPI, this.deliveredPI).start();
        
        TextView textView = (TextView) findViewById(R.id.textView2);
		textView.setText("http://"+ip+":8081/smsgw");
		
		TextView textView2 = (TextView) findViewById(R.id.textView5);
		textView2.setText("http://"+ip+":8081/smsgw?phone=[PHONE_NUMBER]&sms=[SMS MESSAGE]");
    	
    	//---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
        	
        	// PAREI AQUI
        	        	
            public void onReceive(Context context, Intent intent) {
            	
            	HistoryEntry localHistoryEntry = (HistoryEntry)intent.getSerializableExtra(HistoryService.ENTRY);
                
            	TextView textViewDebug = (TextView) findViewById(R.id.textView6);
            	textViewDebug.setText(textViewDebug.getText()+"\n"+"SMS Status:["+getResultCode()+"] ");
            	//textViewDebug.setText(textViewDebug.getText()+"\n"+"Phone:["+localHistoryEntry.getPhoneNumber()+"] ");
            	
            	
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        
                        break;
                }
            }
        }, new IntentFilter(SmsGWActivity.SENT));
 
        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            public void onReceive(Context context, Intent intent) {
            	TextView textViewDebug = (TextView) findViewById(R.id.textView6);
            	textViewDebug.setText(textViewDebug.getText()+"\n"+"SMS Delivered:["+getResultCode()+"]");
            	
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                    	
                        break;
                    case Activity.RESULT_CANCELED:
                        
                        break;                        
                }
            }
        }, new IntentFilter(SmsGWActivity.DELIVERED));
    }
}