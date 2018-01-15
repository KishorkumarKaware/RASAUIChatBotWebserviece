package com.chatbox.bussiness;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.chatbox.model.Entities;
import com.chatbox.model.Intent;
import com.chatbox.model.JavaModel;


import com.chatbox.model.ResponseMdl;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("itautomation")
public class RequestResponce {
	static String result = "";

	@GET
	public Response getMsg() {
		return Response.status(200).entity("Hello").build();

	}

	@POST

	@Consumes(MediaType.APPLICATION_JSON)
	public Response getConf(String inputJson) throws Exception {

		System.out.println("Request recieved");
		
		Intent nameOfIntent = null;

		Entities[] entities = null;
		
		API_AI_Request response = new API_AI_Request();

		JavaModel apiAiResponse = response.jsonToJava(inputJson);
		apiAiResponse.getResponse_text();

		System.out.println("response_text:"+apiAiResponse.getResponse_text());

		//ResponseMdl res=new ResponseMdl();
		JavaModel jm=new JavaModel();
		System.out.println(inputJson);

		Intent iname=new Intent();
		nameOfIntent = apiAiResponse.getIntent();
		String intentname = nameOfIntent.getName();
		//intentname=intentname.replace("\"", "");
		System.out.println("Intent Name:"+nameOfIntent.getName());

	
		//RasaIntegration ri=new RasaIntegration();
		ResponseMdl responsemdl = new ResponseMdl();
		entities = apiAiResponse.getEntities();


		//HashMap<String,String> hm=ri.check_response(text);  
		//for(Map.Entry m1:hm.entrySet()){  
		//System.out.println("Details :"+m1.getKey()+" "+m1.getValue());  
		// if(m1.getKey()==null || m1.getKey().equals("@sys.ignore"))
		/*if(entities[0].getEntity()==null || entities[0].getEntity().equalsIgnoreCase("@sys.ignore"))
			   {
				   responsemdl.setDisplayText("Which software do you want?");
				   responsemdl.setSpeech("Which software do you want?");
				   res.setSource("policyWS");
				   res.setSpeech("Which software do you want?");
				   res.setDisplayText("Which software do you want?");
				   System.out.println("Which software do you want?");

			   }
			   else
			   {
				   responsemdl.setDisplayText("Thanks..!! I will send you link for your software shortly.");
					System.out.println("Thanks..!! I will send you link for your software shortly.");
					 responsemdl.setSpeech("Thanks..!! I will send you link for your software shortly.");
			   }*/

		System.out.println("Responce Text :"+ apiAiResponse.getResponse_text());
		//} 

		// Case: Software Install
		if (intentname.equalsIgnoreCase("Software Installation")) {
			//System.out.println("\nintent name is:" + intentname);
			if(entities[0].getEntity()==null || entities[0].getEntity().equalsIgnoreCase("@sys.ignore"))
			{
				responsemdl.setDisplayText("Which software do you want?");
				responsemdl.setSpeech("Which software do you want?");

			}
			else
			{
				responsemdl.setDisplayText("Thanks..!! I will send you link for your software shortly.");
				System.out.println("Thanks..!! I will send you link for your software shortly.");
				responsemdl.setSpeech("Thanks..!! I will send you link for your software shortly.");
			}
			// AERestCall aeRestCall = new AERestCall();
			/*token = aeRestCall.authenticate();

			String jsonInput = "{\"ServiceRequest\": \"Software Installation\",\"params\": [{\"question\": \"Software\",\"answer\": \"%%SOFTWARE_NAME%%\"},{\"question\":\"Slack Channel\",\"answer\": \"%%SLACK_CHANNEL%%\"}]}";
			jsonInput = jsonInput.replace("%%SOFTWARE_NAME%%", rs.getParameters().getSoftware());
			jsonInput = jsonInput.replace("%%SLACK_CHANNEL%%",slackChannel);

			// Map<String, String> wfParams = new HashMap<>();
			wfParams.put("clientEmail", aeRestCall.getClientEmail(""));
			wfParams.put("jsonInput", jsonInput);
			wfParams.put("slackChannel", slackChannel);
			wfParams.put("slackUser", slackUser);

		//	System.out.println("\ntoken: " + token);
			aeRequestId = aeRestCall.execute(token, "Create Service Request In Remedyforce", wfParams);
		    res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");*/

		}
		// case: Get Incident Status
		else if (intentname.equalsIgnoreCase("incident_status")) {
			System.out.println("\nintent name is:" + intentname);
			//System.out.println("Entity:"+entities[0].getEntity());
			System.out.println("Entity length:"+entities.length);
			
			//for(int i=0; i<entities.length; i++)
			//{ 

				if( entities.length<=0)
				{  
					responsemdl.setDisplayText("Please provide incident number");
					responsemdl.setSpeech("Please provide incident number");
					System.out.println("Please provide incident number");
					ObjectMapper om = new ObjectMapper();
					String str2 = om.writeValueAsString(responsemdl);

					System.out.println("Final result : "+ str2);
					return Response.status(200).entity(str2).header("Content-Type", "application/json").build();
					

				}
				else
				{
					responsemdl.setDisplayText("Yeah sure..!! Please wait while I work on your request for status....");
					System.out.println("Yeah sure..!! Please wait while I work on your request for status....");
					responsemdl.setSpeech("Yeah sure..!! Please wait while I work on your request for status....");
				}
				/*token = aeRestCall.authenticate();
			System.out.println("\ntoken: " + token);
			String aeIncidentNo = rs.getParameters().getIncidentNumber();
			int len = aeIncidentNo.length();
			for (int i = len; i <= 8; i++) {
				if (8 - len != 0) {
					aeIncidentNo = "0" + aeIncidentNo;
					len = aeIncidentNo.length();
				}
			}
			System.out.println("\naeIncidentNo=" + aeIncidentNo);
			// wfParams.put("clientEmail", aeRestCall.getClientEmail("saurabh.kulkarni@vyomlabs.com"));
			wfParams.put("incidentNumber", aeIncidentNo);
			wfParams.put("slackChannel", slackChannel);
			aeRequestId = aeRestCall.execute(token, "Get Remedyforce Incident Status", wfParams);
			res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");*/
			}
		//}
		else if (intentname.equalsIgnoreCase("incident")) {
			System.out.println("\nintent name is:" + intentname);
			
			
			responsemdl.setDisplayText("Please wait while I work on your request to create incident");
			System.out.println("Please wait while I work on your request to create incident");
			responsemdl.setSpeech("Please wait while I work on your request to create incident");
			
			ObjectMapper om = new ObjectMapper();
			String str2 = om.writeValueAsString(responsemdl);

			System.out.println("Final result : "+ str2);
			return Response.status(200).entity(str2).header("Content-Type", "application/json").build();
			/*if(entities[0].getEntity()==null ||entities[0].getEntity()=="" || entities[0].getEntity().equalsIgnoreCase("@sys.ignore"))
			{
				responsemdl.setDisplayText("Please provide description of incident");
				responsemdl.setSpeech("Please provide description of incident");
				System.out.println("Please provide description of incident");
				res.setSource("policyWS");
				   res.setSpeech("Which software do you want?");
				   res.setDisplayText("Which software do you want?");
				   System.out.println("Which software do you want?");

			}
			else
			{
				responsemdl.setDisplayText("Please wait while I work on your request to create incident");
				System.out.println("Please wait while I work on your request to create incident");
				responsemdl.setSpeech("Please wait while I work on your request to create incident");
			}*/

			/*// AERestCall aeRestCall = new AERestCall();
			token = aeRestCall.authenticate();
			// Map<String, String> wfParams = new HashMap<>();
			wfParams.put("clientEmail", aeRestCall.getClientEmail(""));
			wfParams.put("description", rs.getParameters().getDescription());
			wfParams.put("slackChannel", slackChannel);
			wfParams.put("slackUser", slackUser);

		//	System.out.println("\ntoken: " + token);
			aeRequestId = aeRestCall.execute(token, "Create Incident In Remedyforce", wfParams);
			res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");*/
		}
		/*	// case: Create Box Account
		else if (intentname.equalsIgnoreCase("CreateBoxAccount")) {
			System.out.println("intent name is:" + intentname);
			// AERestCall aeRestCall = new AERestCall();
			token = aeRestCall.authenticate();
			String jsonInput = "{\"ServiceRequest\": \"Create Box Account\",\"params\": [{\"question\":\"First Name\",\"answer\": \"%%FIRST_NAME%%\"},{\"question\": \"Last Name\",\"answer\": \"%%LAST_NAME%%\"},{\"question\":\"Slack Channel\",\"answer\": \"%%SLACK_CHANNEL%%\"}]}";

			jsonInput = jsonInput.replace("%%FIRST_NAME%%", rs.getParameters().getFirstName());
			jsonInput = jsonInput.replace("%%LAST_NAME%%", rs.getParameters().getLastName());
			jsonInput = jsonInput.replace("%%SLACK_CHANNEL%%",slackChannel);
			// Map<String, String> wfParams = new HashMap<>();
			wfParams.put("clientEmail", aeRestCall.getClientEmail(""));
			wfParams.put("jsonInput", jsonInput);
			wfParams.put("slackChannel", slackChannel);
			wfParams.put("slackUser", slackUser);

			//System.out.println("token: " + token);
			aeRequestId = aeRestCall.execute(token, "Create Service Request In Remedyforce", wfParams);
			res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");
		}*/
		// Case: Add Member DL
		else if (intentname.equalsIgnoreCase("Add Member to DL")) {
			System.out.println("\nintent name is:" + intentname);

			if(entities[0].getEntity()==null || entities[0].getEntity().equalsIgnoreCase("@sys.ignore"))
			{   System.out.println(entities[0].getEntity());
			responsemdl.setDisplayText("Please provide username");
			responsemdl.setSpeech("Please provide username");
			
			}
			else if(entities[1].getEntity()==null || entities[1].getEntity().equalsIgnoreCase("@sys.ignore"))
			{    System.out.println(entities[1].getEntity());
			responsemdl.setDisplayText("Please provide groupname");
			responsemdl.setSpeech("Please provide groupname");
			}
			else
			{
				responsemdl.setDisplayText("Will add member to group​");
				//System.out.println("Will add member to group");
				responsemdl.setSpeech("Will add member to group​");
			}
			// AERestCall aeRestCall = new AERestCall();
			/*token = aeRestCall.authenticate();

			String jsonInput = "{\"ServiceRequest\": \"Add Members to DL\",\"params\": [{\"question\":\"Username\",\"answer\": \"%%USER_NAME%%\"},{\"question\": \"Group Name\",\"answer\": \"%%GROUP_NAME%%\"},{\"question\":\"Slack Channel\",\"answer\": \"%%SLACK_CHANNEL%%\"}]}";
			String username = rs.getParameters().getUserName();
			username = username.replaceAll(" ", ".");
			jsonInput = jsonInput.replace("%%USER_NAME%%", username);
			jsonInput = jsonInput.replace("%%GROUP_NAME%%", rs.getParameters().getGroupName());
			jsonInput = jsonInput.replace("%%SLACK_CHANNEL%%",slackChannel);
			System.out.println("user:"+ username);
			//System.out.println("JsonInput:" + jsonInput);
			// Map<String, String> wfParams = new HashMap<>();
			wfParams.put("clientEmail", aeRestCall.getClientEmail(""));
			wfParams.put("jsonInput", jsonInput);
			wfParams.put("slackChannel", slackChannel);
			wfParams.put("slackUser", slackUser);

			//System.out.println("\ntoken: " + token);
			aeRequestId = aeRestCall.execute(token, "Create Service Request In Remedyforce", wfParams);
			res.setSource("policyWS");
			res.setSpeech("Please wait while we work on your request.");
			res.setDisplayText("Please wait while we work on your request.");*/
		}
		/*System.out.println("........................");
		//ResponseMdl res = new ResponseMdl();
		res.setSource("policyWS");
		res.setSpeech("Please wait while we work on your request.");
		res.setDisplayText("Please wait while we work on your request.");
        System.out.println("........................");*/

		ObjectMapper om = new ObjectMapper();
		String str2 = om.writeValueAsString(responsemdl);

		//System.out.println("Final result : "+ str2);
		return Response.status(200).entity(str2).header("Content-Type", "application/json").build();
	}
	/*public static void main(String args[]) throws Exception{
	String str="{"
        +"\"text\": \"install MS office on my machine\","
        +"\"intent\": {"
        +"\"name\": \"Software Installation\","
        +"\"confidence\": 0.859798215769884"
        +"},"
        +"\"entities\": ["
        +"{"
        + "\"extractor\": \"ner_mitie\","
        + "\"value\": \"JDK\","
        + "\"end\": 18,"
        +"\"start\": 15,"
        + "\"entity\": \"Software\","
        + "\"processors\": ["
        + "\"ner_synonyms\""
        +" ]"
        + "}"
        + "],"
        + "\"response_text\": \"Thanks..!! I will send you link for your software shortly...\""
        + "}";

		Response outputJSON=new RequestResponce().getConf(str);


	}*/
}
