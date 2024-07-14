package com.ridesharing.initialize;

import com.ridesharing.dashboards.FrontPage;
import com.ridesharing.dashboards.PassengerDashboard;
import com.ridesharing.dashboards.UserPage;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InitializeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        FrontPage frontPage = new FrontPage();
        frontPage.start(primaryStage); 

        /*PassengerDashboard passengerDashboard = new PassengerDashboard();
        passengerDashboard.createUserScene(primaryStage);*/

        primaryStage.setTitle("Ride Sharing Application");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }
}
