package rqlzpk1112;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parse.JSONParser;

public class JSONReadRQLZPK {
	public static void main(String[] args) {
		try(FileReader reader = new FileReader("orarendRQLZPK.json")) {
			JSONParser jsonParser = JSONParser();
			JSONObject = (JSONObject)jsonParser.parse(reader);
			
			JSONObject root = (JSONObject) jsonObject.get("RQLZPK_orarend");
			JSONArray bb = (JSONArray) root.get("ora");
			
			System.out.println("Órarend Mérnökinformatika 2024\n");
			
			for(int i=0; i<bb.size(); i++) {
				JSONObject lesson = (JSONObject) bb.get(i);
				JSONObject time = (JSONObject) lesson.get("idopont");
				System.out.println("Tárgy:" + lesson.get("targy"));
				System.out.println("Időpont:" +time.get("nap") +time.get("tol") +time.get("ig"));
				System.out.println("Helyszín:" +lesson.get("helyszin"));
				System.out.println("Oktató:" +lesson.get("oktato"));
				System.out.println("Szak:" +lesson.get("szak")+ "\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
