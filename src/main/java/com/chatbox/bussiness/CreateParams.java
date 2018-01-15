package com.chatbox.bussiness;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateParams {

	@JsonIgnore
	public static String getParams(String inputJson){
		String params ="";
		String json = "{";
		
		Map<String, String> map = new HashMap<String, String>();
		JSONObject result = new JSONObject( new JSONObject(inputJson).get("result").toString());
		
		params = result.get("parameters").toString();
		System.out.println("Parameters : "+params);
		try {

			ObjectMapper mapper = new ObjectMapper();
			// convert JSON string to Map
			
			map = mapper.readValue(params, new TypeReference<Map<String, String>>(){});
			System.out.println("Map : "+map);

		}
		catch(JsonGenerationException e){
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
//		Map<String, String> paraMap = new HashMap<String, String>();
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String, String> pair = it.next();
		    json +="\"question\":\""+pair.getKey()+"\",\"answer\":\""+pair.getValue()+"\" ";
		}
		json = json.replace(" ", ",");
		return json+"}";
	}
	public static void main(String[] args) {
		String inputJson = "{"
				 + "  \"id\": \"273a3713-6d72-4437-a687-fbeb5db8d165\","
				+ "  \"timestamp\": \"2017-11-16T09:07:59.707Z\","
				+" \"lang\": \"en\","
				+"  \"result\": {"
				+"    \"source\": \"agent\","
				+"    \"resolvedQuery\": \"I want to browsw internet give me access\","
				+"    \"action\": \"\","
				+"    \"actionIncomplete\": false,"
				+"    \"parameters\": {"
				+"                \"PCName.original\": \"B908\","
				+ "          \"PCName\": \"B908\","
				+ "          \"EndDate.original\": \"2nd Nov\","
				+ "          \"AdminAccessFor.original\": \"Laptop\","
				+ "          \"RequestType.original\": \"temporary\","
				+ "          \"Justification.original\": \"dgvwjwgD\","
				+ "         \"AdminAccessFor\": \"Laptop\","
				+ "          \"Justification\": \"dgvwjwgD\","
				+ "          \"EndDate\": \"2018-11-02\","
				+ "          \"RequestType\": \"Temporary\""
				+ "  },"
				+"    \"contexts\": [],"
				+"    \"metadata\": {"
				+"      \"intentId\": \"94fbc8a1-394e-4b38-92e3-f9a9bb33e82b\","
				+"      \"webhookUsed\": \"false\","
				+"      \"webhookForSlotFillingUsed\": \"false\","
				+"      \"intentName\": \"SecformRequest: Internet/External Email\""
				+"    },"
				+"    \"fulfillment\": {"
				+"      \"speech\": \"Thank You, Your Request is submitted successfully..\","
				+"      \"messages\": ["
				+"        {"
				+"          \"type\": 0,"
				+"          \"speech\": \"Thank You, Your Request is submitted successfully..\""
				+"        }"
				+"      ]"
				+"    },"
				+"    \"score\": 1"
				+"  },"
				+"  \"status\": {"
				+"    \"code\": 200,"
				+"    \"errorType\": \"success\","
				+"    \"webhookTimedOut\": false"
				+"  },"
				+"  \"sessionId\": \"90656b0d-0499-4e46-84d9-de41df154c04\" }";
		
		
		System.out.println(getParams(inputJson));
		
}
}