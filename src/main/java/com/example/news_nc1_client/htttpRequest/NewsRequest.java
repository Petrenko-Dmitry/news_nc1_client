package com.example.news_nc1_client.htttpRequest;

import com.example.news_nc1_client.entity.News;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsRequest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Executes an HTTP GET request to the specified URL and parses the response into a list of News objects.
     * Uses an HTTP client to send the request and processes the response as a JSON string.
     * If an error occurs during the request or parsing, an empty list is returned.
     *
     * @param url the URL to send the GET request to
     * @return a list of News objects parsed from the JSON response, or an empty list if an error occurs
     */
    public List<News> executeRequest(String url) {
        try (var client = HttpClients.createDefault()) {
            var request = new HttpGet(url);
            try (var response = client.execute(request)) {
                var json = EntityUtils.toString(response.getEntity());
                return objectMapper.readValue(json, new TypeReference<>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
