package com.chatbox.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


import com.chatbox.bussiness.RequestResponce;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.vyom.rasa.integration.validate_Response;

public class RasaIntegration{
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04";
	// GET action URL, query string appended to the URL as ?stype=models
	//private final String urlRaiseRequest = "http://192.168.1.194:8888/aeengine/rest/execute";

	// POST action URL
	private final static String urlPOST = "http://10.41.5.101:5001/api/v2/rasa/parse";

	// Post data or a payload

	public String inputLine=null;
	// Main class
	/*JSONObject entities = null;
	JSONObject intent=null;
	JSONArray intentarr, entitiesarr=null,entitiesarr1;
	String intentname = null;
	String value =null,value1=null,name=null;
	String entity= null,entity1=null;*/
	public HashMap<String,String> check_response(String msg) throws Exception
	{

		// POST example URL
		URL obj = new URL(urlPOST);

		// Send post request
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// Basic reuqest header to simulate a browser request
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Upgrade-Insecure-Requests", "1");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("content-type", "application/json");
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlByYWRueWEiLCJuYW1lIjoiUHJhZG55YSIsImlhdCI6MTUxMzA4NTAyMn0.9wdhayc00t508lUYf0cCuWdz14MbTdsPfz29OZfnz4o"
);
		
		String postDataBody = "{\"project\":\"DF4\",\"model\":\"model_20171206-190813\",\"intent\":\"\",\"query\":\""+msg+"\"}";
		// Payload
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		System.out.println("Sent body="+postDataBody);
		
		// POST data added to the request as a part of body
		wr.writeBytes(postDataBody);
		wr.flush();
		wr.close();
	
		// Reading the HTML output of the POST HTTP request
		//int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String str = "";
		while ((inputLine = in.readLine()) != null)
		{
			str=str+inputLine;
		}
		
    System.out.println(str);
	//JSONObject entities = null;
	/*JSONObject intent=null;*/
	 String intent="",entitiesarr="";
	 String entities = null;
	String intentname = null;
	String value =null,value1=null,name=null;
	String entity= null,entity1=null;
	try
	{
		ObjectMapper mapper1 = new ObjectMapper();
	    JsonNode actualObj = mapper1.readTree(str);
	    
	    intent = actualObj.get("intent").toString();
	    
		/*intent = new JSONObject(new JSONObject(str).get("intent").toString());
		System.out.println("intent="+intent);*/
	  
	    
	    intentname = actualObj.get("intent").toString();
	    System.out.println("intent="+intentname);
	    intentname =actualObj.get("intent").get("name").toString();
	    intentname=intentname.replace("\"", "");
	    	    
	    entities = actualObj.get("entities").toString().replace("[", "").replace("]", "");
		System.out.println("Entities="+entities);
		
		actualObj = mapper1.readTree(entities);
		entity = actualObj.get("entity").toString();
		entity=entity.replace("\"", "");
		value = actualObj.get("value").toString();
		value=value.replace("\"", "");
		
		/*actualObj = mapper1.readTree(entities);
		entity1 = actualObj.get("entity").toString();
		value1 = actualObj.get("value").toString();*/
		/*
		for(int i=0;i<entitiesarr.length();i++)
		{
			entitiesarr = new JSONArray(new JSONObject(str).get("entities").toString());
			value = new JSONObject(entitiesarr.get(i).toString()).get("value").toString();
			entitiesarr = new JSONArray(new JSONObject(str).get("entities").toString());
			entity = new JSONObject(entitiesarr.get(i).toString()).get("entity").toString();
			
		}*/
		
		
	}
	
	catch (Exception e) {
		
	}
	System.out.println("intentname:"+intentname);
	System.out.println("entity:"+entity);
	System.out.println("value:"+value);
	//System.out.println("entity1:"+entity1);
	//System.out.println("value1:"+value1);
	in.close();
	
	HashMap<String,String> hm=new HashMap<String,String>();  

	  hm.put(entity,value); 
	  
	 
	  for(Map.Entry m:hm.entrySet()){  
	   System.out.println(m.getKey()+" "+m.getValue());  
	  }  
	/*try
	{
        Integer.valueOf((String)amount);
        temp = "amount:"+amount;
	}catch(Exception e){

		if(amount == null && payee == null)
		{
			return intent.get("name").toString();

		}else if(payee == null){
			return "payee:"+amount;
		}
	}
	return temp;

}*/
	/*String response=null;
	if(entity==null || entity=="@sys.ignore")
	{
		return "Which software do you want?";
	}
	else 
	{
		response="Thanks..!! I will send you link for your software shortly";
	
	}*/
	return hm;

	
}

 public static void main(String[] args ) throws Exception
{
	 /*String str = "{"
				+"\"intent\": {"
						+"\"name\": \"Install Software\","
						+" \"confidence\": 0.46538238975156204"
						+" },"
				+" \"text\": \"install oracle software\","
				+" \"entities\": ["
				+" {"
				+" \"value\": \"oracle\","
				 +"\"entity\": \"Software\","
				+" \"start\": 8,"
				 +"\"end\": 14,"
				+" \"extractor\": \"ner_mitie\""
				+" }	 ]		}";*/
	 //Install Software
	/* String str2="{"
 +"\"intent\": {"
 +"\"name\": \"Install Software\","
 +"\"confidence\": 0.6102648424999471"
 +"},"
 +"\"text\": \"install oracle software on WS/B3/064\","
 +"\"entities\": ["
 +"{"
 +"\"value\": \"oracle software\","
 +"\"entity\": \"Software\","
 +"\"start\": 8,"
 +"\"end\": 23,"
 +"\"extractor\": \"ner_mitie\""
 +"},{"
 +"\"value\": \"WS/B3/064\","
 +"\"entity\": \"workstation number\","
 +"\"start\": 27,"
 +"\"end\": 36,"
 +"\"extractor\": \"ner_mitie\""
 +"} ] }";
	 */
	 /*String str2="{"
			+"\"intent\": {"
	        +"\"name\": \"Software Installation\","
	        +"\"confidence\": 0.924618991835297"
	        +"},"
	        +"\"entities\": ["
	        + "{"
	        +"\"end\": 17,"
	        + "\"entity\": \"Software\","
	        + "\"extractor\": \"ner_mitie\","
	        + "\"value\": \"MS Office\","
	        + "\"processors\": ["
	        + "\"ner_synonyms\""
	        + "],"
	        + "\"start\": 8"
	        +"}"
	    +"],"
	   + "\"text\": \"Install MS Office on my machine\""
	+"}";
	 */
	HashMap<String, String> msg =new RasaIntegration().check_response("");
	System.out.println(msg);
	
	/*//System.out.println("Rasa Responce="+new validate_Response().check_response("i want to transfer money to mom"));
	
			String msg =new validate_Response().check_response("hi");
			System.out.println(msg);
			String payee[]=msg.split(":");
			System.out.println(payee[payee.length-1]);
	*/
}
}