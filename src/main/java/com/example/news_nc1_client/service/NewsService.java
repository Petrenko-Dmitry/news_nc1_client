package com.example.news_nc1_client.service;

import com.example.news_nc1_client.entity.News;

import com.example.news_nc1_client.htttpRequest.NewsRequest;

import java.util.List;

import static com.example.news_nc1_client.constants.StringConstants.SERVER_NEWS_FILTER_URL;
import static com.example.news_nc1_client.constants.StringConstants.SERVER_NEWS_URL;

public class NewsService {

    private final NewsRequest newsRequest = new NewsRequest();

    /**
     * Fetches the list of news from the server.
     * Sends a request to the server to retrieve the news from the predefined URL.
     *
     * @return a list of News objects fetched from the server
     */
    public List<News> fetchNews() {
        return this.newsRequest.executeRequest(SERVER_NEWS_URL);
    }

    /**
     * Fetches the list of news filtered by the specified time of day.
     * Sends a request to the server with a time of day filter (e.g., morning, day, evening)
     * to retrieve news specific to that time.
     *
     * @param timeOfDay the time of day to filter the news by (e.g., "MORNING", "DAY", "EVENING")
     * @return a list of News objects that match the specified time of day
     */
    public List<News> filterNewsByTime(String timeOfDay) {
        return this.newsRequest.executeRequest(String.format(SERVER_NEWS_URL + SERVER_NEWS_FILTER_URL, timeOfDay));
    }

}
