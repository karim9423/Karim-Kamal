
package io;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
	public static JSONParser parser;

	public static JSONObject readJson(String path) {
		parser = new JSONParser();
		try {
			Object config = parser.parse(new FileReader(path));
			JSONObject cap = (JSONObject) config;
			return cap;

		} catch (ParseException | IOException e) {
			return null;
		}
	}
}