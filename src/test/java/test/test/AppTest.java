package test.test;



import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class AppTest {

	@Test
	public void testMain() {
		JSONParser parser = new JSONParser();
		
		int cont=1;
				
		try {
			
			Object obj = parser.parse(new FileReader("src/test/java/test/test/regression-test.json"));

			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray tags = (JSONArray) jsonObject.get("flow");
			Iterator<JSONObject> iteratorFlow = tags.iterator();
			while (iteratorFlow.hasNext()) {
				JSONObject iteC =iteratorFlow.next();
				JSONArray tagsC = (JSONArray) iteC.get("conversation");
				Iterator<JSONObject> iteratorFlowC = tagsC.iterator();

				System.out.println("---------Conversation "+cont +"----------");
				ArrayList<String> question = new ArrayList<String>();
				ArrayList<String> expect = new ArrayList<String>();
				while (iteratorFlowC.hasNext()) {
					JSONObject iteCoversation = (JSONObject) iteratorFlowC.next();
					question.add((String) iteCoversation.get("question"));
					expect.add((String) iteCoversation.get("response"));
					
				}
				ArrayList<String> actual=App.main(question);
				actual.removeAll(Collections.singleton(null));
				for (int i = 0; i < actual.size(); i++) {
					System.out.println("Expect: "+expect.get(i));
					System.out.println("Actual: "+actual.get(i));
				}
				assertEquals(expect, actual);
				cont++;
			}


		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			fail("error read file");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			fail("error read file");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			fail("error read file");
		}
		
	}

}
	