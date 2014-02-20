package org.ups.ter.morsecode;

public class ChatMessage {

	String data;
	boolean sentByMe;
	
	public ChatMessage(String data, boolean sentByMe) {
		this.data = data;
		this.sentByMe = sentByMe;
	}

	
	@Override
	public String toString() {
		if(sentByMe)
			return "Me : " + data;
		else
			return "Friend : " + data;
	}
	
}
