package com.smsgw.core;

import java.io.Serializable;

public class HistoryEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private long eventTime;
	private final EventType eventType;
	private final String message;
	private MessageType messageType;
	private final String phoneNumber;
	private int resultCode;
	private final long scheduledTime;
	private String statusMessage;

	public HistoryEntry(String paramString1, long paramLong1, long paramLong2, String paramString2, EventType paramEventType) {
		this.phoneNumber = paramString1;
		this.scheduledTime = paramLong1;
		this.eventTime = paramLong2;
		this.message = paramString2;
		this.eventType = paramEventType;
	}

	public long getEventTime() {
		return this.eventTime;
	}

	public EventType getEventType() {
		return this.eventType;
	}

	public String getMessage() {
		return this.message;
	}

	public MessageType getMessageType() {
		return this.messageType;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public int getResultCode() {
		return this.resultCode;
	}

	public long getScheduledTime() {
		return this.scheduledTime;
	}

	public String getStatusMessage() {
		return this.statusMessage;
	}

	public boolean isPersistable() {
		int i;
		if ((this.phoneNumber == null) || (this.message == null))
			i = 0;
		else
			i = 1;
		return true;
	}

	public void update(int paramInt) {
		this.resultCode = paramInt;
	}
}




