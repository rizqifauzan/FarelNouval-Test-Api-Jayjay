package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestUtils {
    private static final Gson gson = new Gson();

    public static JsonObject parseJson(String jsonString) {
        return gson.fromJson(jsonString, JsonObject.class);
    }
}