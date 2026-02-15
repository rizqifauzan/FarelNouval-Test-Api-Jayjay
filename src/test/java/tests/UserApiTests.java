package tests;

import requests.ApiClient;
import utils.TestUtils;
import com.google.gson.JsonObject;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserApiTests {
    private ApiClient apiClient;

    @BeforeClass
    public void setup() {
        apiClient = new ApiClient("https://jsonplaceholder.typicode.com");
    }

    // Tes Positif: GET user dengan ID valid
    @Test
    public void testGetUserValidId() throws IOException {
        Response response = apiClient.get("/users/1");
        Assert.assertEquals(response.code(), 200);

        String body = response.body().string();
        JsonObject json = TestUtils.parseJson(body);
        Assert.assertEquals(json.get("id").getAsInt(), 1);
    }

    // Tes Negatif: GET user dengan ID tidak valid (misal ID 0)
    @Test
    public void testGetUserInvalidId() throws IOException {
        Response response = apiClient.get("/users/0");
        Assert.assertEquals(response.code(), 404);
    }

    // Tes Batas: POST user dengan data minimal valid
    @Test
    public void testCreateUserMinimalData() throws IOException {
        String jsonBody = """
        {
            "name": "Test User",
            "username": "testuser",
            "email": "testuser@example.com"
        }
        """;

        Response response = apiClient.post("/users", jsonBody);
        Assert.assertEquals(response.code(), 201);

        String body = response.body().string();
        JsonObject json = TestUtils.parseJson(body);
        Assert.assertEquals(json.get("name").getAsString(), "Test User");
    }


}