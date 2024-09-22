package com.example.news_nc1_client.entity;

public class News {
    private Long id;
    private String headline;
    private String description;
    private String publicationTime;

    public News() {
    }

    public News(Long id, String headline, String description, String publicationTime) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.publicationTime = publicationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }
}
