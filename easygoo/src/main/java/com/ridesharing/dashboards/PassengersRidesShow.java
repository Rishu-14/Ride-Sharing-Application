package com.ridesharing.dashboards;

import com.ridesharing.firebaseconfig.DataService;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PassengersRidesShow {
    private Stage primaryStage;
    private DataService dataService;
    private PassengerDashboard passengerDashboard;
    private VehicleChooseDashboard vehicleChooseDashboard;
    private PassengerInfo passengerInfo;
    private DriverDashboard driverDashboard;
    private PublishDashboard publishDashboard;


    public PassengersRidesShow(Stage primaryStage, DataService dataService){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
    } 
    
    public PassengersRidesShow(Stage primaryStage,PassengerDashboard passengerDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerDashboard = passengerDashboard;
    }

    public PassengersRidesShow(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleChooseDashboard = vehicleChooseDashboard;
    }

    public PassengersRidesShow(Stage primarysStage, PassengerInfo passengerInfo){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerInfo = passengerInfo;
    }
    public PassengersRidesShow(Stage primaryStage,DriverDashboard driverDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.driverDashboard = driverDashboard;
    }
    public PassengersRidesShow(Stage primaryStage, PublishDashboard publishDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.publishDashboard = publishDashboard;
    }

    public Scene creatRideShowScene(Stage primaryStage)  {
        this.primaryStage = primaryStage;
        // Creating AnchorPane as the root node
        AnchorPane root = new AnchorPane();

        // First AnchorPane (top part)
        AnchorPane topPane = new AnchorPane();
        topPane.setStyle("-fx-background-color: white;");
        topPane.setPrefSize(950, 58);
        topPane.setLayoutX(15);
        topPane.setLayoutY(20);

        // ImageView for car logo
        ImageView carLogo = new ImageView(new Image("Assets/images/carlogo.png"));
        carLogo.setLayoutX(63);
        carLogo.setLayoutY(4);
        carLogo.setFitWidth(52);
        carLogo.setFitHeight(52);

        // Text "EasyGoo!"
        Text easyGooText = new Text("EasyGoo!");
        easyGooText.setLayoutX(115);
        easyGooText.setLayoutY(36);
        easyGooText.setText("#2882ea");
        easyGooText.setStrokeWidth(0);
        easyGooText.setFont(Font.font("System Bold", 18));

        // ImageView for user profile
        ImageView userProfile = new ImageView(new Image("Assets/images/Account-User.png"));
        userProfile.setLayoutX(886);
        userProfile.setLayoutY(8);
        userProfile.setFitWidth(43);
        userProfile.setFitHeight(43);

        // Button with graphic (back button)
        Button backButton = new Button();
        backButton.setLayoutY(13);
        backButton.setMinWidth(52);
        backButton.setPrefWidth(52);
        backButton.setPrefHeight(30);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setText("#2882ea");
        backButton.setFont(Font.font("Arial Black", 11));

        ImageView backImage = new ImageView(new Image("Assets/images/back.png"));
        backImage.setFitWidth(42);
        backImage.setFitHeight(30);

        backButton.setGraphic(backImage);

        // Adding nodes to topPane
        topPane.getChildren().addAll(carLogo, easyGooText, userProfile, backButton);

        // Second AnchorPane (bottom part)
        AnchorPane bottomPane = new AnchorPane();
        bottomPane.setStyle("-fx-background-color: #2882ea;");
        bottomPane.setPrefSize(273, 642);
        bottomPane.setLayoutY(58);

        // ImageView for logo
        ImageView logoImage = new ImageView(new Image("Assets/images/logo.png"));
        logoImage.setLayoutY(238);
        logoImage.setFitWidth(181);
        logoImage.setFitHeight(166);

        // Text "EasyGoo!"
        Text bottomText = new Text("EasyGoo!");
        bottomText.setLayoutX(144);
        bottomText.setLayoutY(331);
        bottomText.setText("#f4f0f0");
        bottomText.setStrokeWidth(0);
        bottomText.setFont(Font.font(24));

        // Adding nodes to bottomPane
        bottomPane.getChildren().addAll(logoImage, bottomText);

        // Adding AnchorPanes to root
        root.getChildren().addAll(topPane, bottomPane);

        // Setting up the scene and stage
        Scene rideShowScene = new Scene(root, 950, 700);
        return rideShowScene;
        
    }
    
}
