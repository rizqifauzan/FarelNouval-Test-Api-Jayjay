package requests;

import okhttp3.*;
import java.io.IOException;

public class ApiClient {
    private final OkHttpClient client;
    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.client = new OkHttpClient();
        this.baseUrl = baseUrl;
    }

    public Response get(String endpoint) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .get()
                .build();
        return client.newCall(request).execute();
    }

    public Response post(String endpoint, String jsonBody) throws IOException {
        RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

    // Tambahkan method put, delete jika perlu
}