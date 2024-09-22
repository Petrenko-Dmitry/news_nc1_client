package com.example.news_nc1_client;

import com.example.news_nc1_client.entity.News;
import com.example.news_nc1_client.service.NewsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class NewsController {
    @FXML
    private TableView<News> newsTableView;
    @FXML
    private TableColumn<News, String> titleColumn;
    @FXML
    private TableColumn<News, String> dateColumn;
    @FXML
    private TextArea newsDetails;
    @FXML
    private Button morningButton;
    @FXML
    private Button dayButton;
    @FXML
    private Button eveningButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;

    private ObservableList<News> observableNewsList;
    private int currentIndex = 0;

    private final NewsService newsService;

    public NewsController() {
        this.newsService = new NewsService();
    }

    @FXML
    private void initialize() {
        this.initializeTableColumns();

        this.loadNews();
        this.initializeNewsSelectionListener();

        this.initializeButtons();
        this.initializeFirstNewsDetail();
    }

    /**
     * Initializes a listener for news selection in the table view.
     * When the selected news item changes, the details of the new item are displayed.
     */
    private void initializeNewsSelectionListener() {
        this.newsTableView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        this.displayNewsDetails(newValue);
                    }
                });
    }

    /**
     * Configures the table columns for displaying news.
     * Sets up the headline and publication time columns using property values from the News object.
     */
    private void initializeTableColumns() {
        this.titleColumn.setCellValueFactory(new PropertyValueFactory<>("headline"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationTime"));
    }

    /**
     * Initializes the action listeners for the buttons.
     * Each button filters the news based on the time of day (morning, day, evening)
     * or clears the filter for unknown time. It also sets actions for navigating
     * between previous and next news items.
     */
    private void initializeButtons() {
        this.morningButton.setOnAction(event -> this.filterNews("MORNING"));
        this.dayButton.setOnAction(event -> this.filterNews("DAY"));
        this.eveningButton.setOnAction(event -> this.filterNews("EVENING"));
        this.clearButton.setOnAction(event -> this.filterNews("UNKNOWN"));

        this.prevButton.setOnAction(event -> this.showPreviousNews());
        this.nextButton.setOnAction(event -> this.showNextNews());
    }

    /**
     * Initializes the selection of the first news item in the table view.
     * Selects the news item at the current index when the table is first loaded.
     */
    private void initializeFirstNewsDetail() {
        this.newsTableView.getSelectionModel().select(this.currentIndex);
    }

    /**
     * Loads the list of news from the news service and sets it to the table view.
     * Converts the fetched news list into an observable list to be displayed in the table.
     */
    private void loadNews() {
        var newsList = this.newsService.fetchNews();
        this.observableNewsList = FXCollections.observableArrayList(newsList);
        this.newsTableView.setItems(this.observableNewsList);
    }

    /**
     * Displays the details of the selected news item.
     * Updates the text area with the news description and sets the current index to the selected item.
     *
     * @param selectedNews the news item that is selected in the table view
     */
    private void displayNewsDetails(News selectedNews) {
        this.newsDetails.setText(selectedNews.getDescription());
        this.currentIndex = this.newsTableView.getItems().indexOf(selectedNews);
    }

    /**
     * Filters the news based on the specified time of day.
     * Updates the observable list with the filtered news and selects the first item from the filtered list.
     *
     * @param timeOfDay the time of day to filter the news by (e.g., "MORNING", "DAY", "EVENING", "UNKNOWN")
     */
    private void filterNews(String timeOfDay) {
        var filteredNews = this.newsService.filterNewsByTime(timeOfDay);
        this.observableNewsList.setAll(filteredNews);
        this.initializeFirstNewsDetail();
    }

    /**
     * Navigates to the previous news item in the list.
     * Decreases the current index and selects the corresponding news item in the table view.
     * Scrolls to the selected item if necessary.
     */
    private void showPreviousNews() {
        if (this.currentIndex > 0) {
            this.currentIndex--;
            this.newsTableView.getSelectionModel().select(this.currentIndex);
            this.newsTableView.scrollTo(this.currentIndex);
        }
    }

    /**
     * Navigates to the next news item in the list.
     * Increases the current index and selects the corresponding news item in the table view.
     * Scrolls to the selected item if necessary.
     */
    private void showNextNews() {
        if (this.currentIndex < this.observableNewsList.size() - 1) {
            this.currentIndex++;
            this.newsTableView.getSelectionModel().select(this.currentIndex);
            this.newsTableView.scrollTo(this.currentIndex);
        }
    }
}