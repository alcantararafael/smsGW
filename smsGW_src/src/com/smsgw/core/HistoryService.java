package com.smsgw.core;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.smsgw.core.EventType;;

public class HistoryService extends IntentService
{
  public static final String ENTRY;
  public static final String EVENT_TIME;
  public static final String MESSAGE;
  public static final String PHONE_NUMBER = HistoryService.class.getName() + ".PHONE_NUMBER";
  public static final String SCHEDULED_TIME = HistoryService.class.getName() + ".SCHEDULED_TIME";
  public static final String TYPE;

  static
  {
    EVENT_TIME = HistoryService.class.getName() + ".EVENT_TIME";
    MESSAGE = HistoryService.class.getName() + ".MESSAGE";
    TYPE = HistoryService.class.getName() + ".TYPE";
    ENTRY = HistoryService.class.getName() + ".ENTRY";
  }

  public HistoryService()
  {
    super("HistoryService");
  }

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}

}
