package com.jaymansmann.gameserver.gameserver.utility;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectGenerator {

	public static JSONObject ofType(String type) throws JSONException {
		JSONObject object = new JSONObject();
		object.put("type", type);
		return object;
	}
}
