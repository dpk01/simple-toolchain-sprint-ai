package test.test;

import java.util.ArrayList;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class App {
	
	public static final String USENAME= "0be98465-ea2f-4f9f-bedc-19a0731f949a";
	public static final String PASSWORD= "UNkfeKefSGut";
	public static final String WORKSPACE_ID= "141d7139-40da-4b46-87be-971c41c06b4d";
	
	public static ArrayList<String> main(ArrayList<String> question) {
		ConversationService service = new ConversationService("2016-09-20");
		service.setUsernameAndPassword(USENAME, PASSWORD);

		MessageRequest newMessage = new MessageRequest.Builder().build();

		MessageResponse response = service.message(WORKSPACE_ID, newMessage).execute();

		ArrayList<String> resp = new ArrayList<String>();

		for (String string : question) {
//			System.out.println("Yo: " + string);
			MessageRequest second = new MessageRequest.Builder().inputText(string).context(response.getContext())
					.build();
			response = service.message(WORKSPACE_ID, second).execute();
//			System.out.println("Chat: " + response.getOutput().get("text"));
			resp.add(response.getOutput().get("text").toString().substring(1,response.getOutput().get("text").toString().length() - 1));
		}
		
		return resp;

	}
}
