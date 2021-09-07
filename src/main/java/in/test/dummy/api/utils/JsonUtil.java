package in.test.dummy.api.utils;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonUtil.
 */
public class JsonUtil {

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public JSONArray getUsers() {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			Object obj = jsonParser.parse(new FileReader("users.json"));
			jsonArray = (JSONArray) obj;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	/**
	 * Gets the users from mongo.
	 *
	 * @return the users from mongo
	 */
	public JSONArray getUsersFromMongo() {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			Object obj = jsonParser.parse(new FileReader("users_mongo.json"));
			jsonArray = (JSONArray) obj;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
