package rqlzpk1112;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriteRQLZPK {

	public static void main(String[] args) {
		
		JSONArray bb = new JSONArray();
		bb.add(createLesson("Szoftvertesztelés", "hétfő", "10", "12", "Inf.103", "Dr.Hornyák Olivér", "G3BIW"));
		
	}

}
