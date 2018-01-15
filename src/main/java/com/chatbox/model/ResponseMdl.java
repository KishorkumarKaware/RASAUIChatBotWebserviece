package com.chatbox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMdl {

	String speech;
	String displayText;
	DataToClient dataToClient;
	public String getSpeech() {
		return speech;
	}
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	
	public DataToClient getDataToClient() {
		return dataToClient;
	}
	public void setDataToClient(DataToClient dataToClient) {
		this.dataToClient = dataToClient;
	}
	@Override
	public String toString() {
		return "ResponseMdl [speech=" + speech + ", displayText=" + displayText + ", dataToClient=" + dataToClient
				+ "]";
	}
	
	
	
}

