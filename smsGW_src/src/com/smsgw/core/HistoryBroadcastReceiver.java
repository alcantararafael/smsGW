package com.smsgw.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HistoryBroadcastReceiver extends BroadcastReceiver
{
  public static final String ACTION = HistoryBroadcastReceiver.class.getPackage().getName() + ".HISTORY";
  public static final String RESULT = HistoryBroadcastReceiver.class.getPackage().getName() + ".RESULT";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    HistoryEntry localHistoryEntry = (HistoryEntry)paramIntent.getSerializableExtra(HistoryService.ENTRY);
    if (localHistoryEntry != null)
    {
      localHistoryEntry.update(getResultCode());
      Intent localIntent = new Intent(paramContext, HistoryService.class);
      localIntent.putExtra(HistoryService.ENTRY, localHistoryEntry);
      paramContext.startService(localIntent);
      
      System.out.println("\n"+"SMS Status:["+getResultCode()+"] ");
      System.out.println("\n"+"Phone:["+localHistoryEntry.getPhoneNumber()+"] ");
    }
  }
}
