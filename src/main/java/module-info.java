module com.example.news_nc1_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;


    opens com.example.news_nc1_client to javafx.fxml;
    exports com.example.news_nc1_client;
    exports com.example.news_nc1_client.entity;
    opens com.example.news_nc1_client.entity to javafx.fxml;
}