package com.ridesharing.dashboards;

import com.ridesharing.controller.LoginController;
import com.ridesharing.exception.NoRidesAvailableException;
import com.ridesharing.firebaseconfig.DataService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PassengerDashboard {

    private Stage primaryStage;
    private DataService dataService;
    private UserPage userPage;
    private PassengerInfo passengerSearchDashboard;
    private VehicleChooseDashboard vehicleChooseDashboard;
    private PassengersRidesShow ridesShow;
    private AvailableRidesDashboard availableRidesDashboard;
    private String vehicleType;
    private BookedRides bookedRides;
    private LoginController loginController;
    private String leavingFrom;
    private String goingTo;

    public String getLeavingFrom(){
        return leavingFrom;
    }

    public String getGoingTo(){
        return goingTo;
    }

    public PassengerDashboard(Stage primaryStage,String leavingFrom, String goingTo,String vehicleType) {
        System.out.println("VEHICAL TYPE");
        System.out.println(vehicleType);
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleType = vehicleType;
        this.leavingFrom = leavingFrom;
        this.goingTo = goingTo;

    }

    public PassengerDashboard(Stage primaryStage, UserPage userPage) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.userPage = userPage;

    }

    public PassengerDashboard(Stage primaryStage, LoginController loginController) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.loginController = loginController;
    }

    public PassengerDashboard(Stage primaryStage, PassengerInfo passengerSearchDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerSearchDashboard = passengerSearchDashboard;

    }

    public PassengerDashboard(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard, String vehicleType) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleChooseDashboard = vehicleChooseDashboard;
        this.vehicleType = vehicleType;
    }

    public PassengerDashboard(Stage primaryStage, Ride ride) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.ridesShow = ridesShow;

    }

    public PassengerDashboard(Stage primaryStage, AvailableRidesDashboard availableRidesDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.availableRidesDashboard = availableRidesDashboard;

    }

    public PassengerDashboard(Stage primaryStage, BookedRides bookedRides) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.bookedRides = bookedRides;

    }

    public Scene createPassengerScene(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard2) {
        this.primaryStage = primaryStage;
        this.vehicleType = vehicleType;

        AnchorPane root = new AnchorPane();
        root.setPrefSize(950, 700);
        root.setStyle("-fx-background-color: white;");

        // Top Pane
        AnchorPane topPane = new AnchorPane();
        topPane.setPrefSize(950, 58);
        topPane.setStyle("-fx-background-color: white;");

        ImageView carLogoImageView = new ImageView(new Image("Assets/images/carlogo.png"));
        carLogoImageView.setFitHeight(52);
        carLogoImageView.setFitWidth(52);
        carLogoImageView.setLayoutX(63);
        carLogoImageView.setLayoutY(4);

        Text easyGooText = new Text("EasyGoo!");
        easyGooText.setFill(javafx.scene.paint.Color.web("#2882ea"));
        easyGooText.setFont(new Font("System Bold", 18));
        easyGooText.setLayoutX(115);
        easyGooText.setLayoutY(36);

        ImageView userProfileImageView = new ImageView(new Image("Assets/images/Account-User.png"));
        userProfileImageView.setFitHeight(43);
        userProfileImageView.setFitWidth(43);
        userProfileImageView.setLayoutX(886);
        userProfileImageView.setLayoutY(8);

        Button backButton = new Button();
        backButton.setLayoutY(13);
        backButton.setPrefSize(52, 30);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        backButton.setFont(new Font("Arial Black", 11));
        ImageView backIcon = new ImageView(new Image("Assets/images/back.png"));
        backIcon.setFitHeight(30);
        backIcon.setFitWidth(42);
        backButton.setGraphic(backIcon);
        backButton.getStyleClass().add("button");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ShowVehicleChooseDashboard();
            }

        });

        Button myBookingButton = new Button("My Booking");
        myBookingButton.setLayoutX(780);
        myBookingButton.setLayoutY(16);
        myBookingButton.setStyle("-fx-background-color: transparent;");
        myBookingButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        myBookingButton.setFont(new Font("Arial Black", 14));
        myBookingButton.getStyleClass().add("button");
        

        topPane.getChildren().addAll(carLogoImageView, easyGooText, backButton, userProfileImageView, myBookingButton);

        ImageView textImage = new ImageView(new Image("Assets/images/passengertext.png"));
        textImage.setFitHeight(217);
        textImage.setFitWidth(950);
        textImage.setLayoutX(0);
        textImage.setLayoutY(305);

        // Main Image
        ImageView mainImage = new ImageView(new Image("Assets/images/blabla.jpeg"));
        mainImage.setFitHeight(219);
        mainImage.setFitWidth(950);
        mainImage.setLayoutY(58);

        // Search Pane
        AnchorPane searchPane = new AnchorPane();
        searchPane.setLayoutX(54);
        searchPane.setLayoutY(262);
        searchPane.setPrefSize(846, 36);
        searchPane.setStyle("-fx-background-color: white;");
        searchPane.setEffect(new DropShadow());

        DatePicker datePicker = new DatePicker();
        datePicker.setPrefHeight(36);
        datePicker.setPrefWidth(221);
        datePicker.setLayoutX(521);
        datePicker.setPromptText("dd/mm/yy");
        TextField leavingField = new TextField();
        leavingField.setPrefHeight(36);
        leavingField.setPrefWidth(250);
        leavingField.setPromptText("Leaving From");
        leavingField.setFont(Font.font("Arial Black", 14));

        ImageView directionImageView = new ImageView(new Image("Assets/images/direction.jpg"));
        directionImageView.setFitHeight(36);
        directionImageView.setFitWidth(45);

        TextField goingToField = new TextField();
        goingToField.setPrefHeight(36);
        goingToField.setPrefWidth(233);
        goingToField.setPromptText("Going To");
        goingToField.setFont(Font.font("Arial Black", 14));

        Button searchButton2 = new Button("Search");
        searchButton2.setPrefHeight(36);
        searchButton2.setPrefWidth(100);
        searchButton2.setLayoutX(745);
        searchButton2.setStyle("-fx-background-color: #2882ea;");
        searchButton2.setTextFill(javafx.scene.paint.Color.web("#fcf8f8"));

        searchButton2.setOnAction(event -> {
            this.goingTo = goingToField.getText();
            this.leavingFrom = leavingField.getText();

            try {
                ShowAvailableRides();
            } catch (NoRidesAvailableException e) {
                
                e.printStackTrace();
            }
        });
        // searchButton2.setOnAction(new EventHandler<ActionEvent>() {

        // @Override
        // public void handle(ActionEvent event) {

        // ShowAvailableRides();
        // }

        // });

        ImageView searchImageView2 = new ImageView(new Image("Assets/images/search.png"));
        searchImageView2.setFitHeight(20);
        searchImageView2.setFitWidth(20);
        searchButton2.setGraphic(searchImageView2);
        searchButton2.setFont(Font.font("Arial Black", 11));
        searchButton2.getStyleClass().add("button");

        searchPane.getChildren().addAll(datePicker, searchButton2);

        // Location Box
        HBox locationBox = new HBox();
        locationBox.setLayoutX(55);
        locationBox.setLayoutY(262);
        locationBox.setPrefSize(518, 36);

        locationBox.getChildren().addAll(leavingField, directionImageView, goingToField);

        // Bottom Pane
        AnchorPane bottomPane = new AnchorPane();
        bottomPane.setLayoutX(-2);
        bottomPane.setLayoutY(538);
        bottomPane.setPrefSize(950, 162);
        bottomPane.setStyle("-fx-background-color: #5ca2f2;");
        bottomPane.setEffect(new Glow());

        ImageView bottomImageView = new ImageView(new Image("Assets/images/maindashboardimage-removebg-preview.png"));
        bottomImageView.setFitHeight(158);
        bottomImageView.setFitWidth(200);
        bottomImageView.setLayoutX(211);
        bottomImageView.setLayoutY(3);

        Text bottomTitleText = new Text("Help us keep you safe from scam");
        bottomTitleText.setFill(javafx.scene.paint.Color.WHITE);
        bottomTitleText.setFont(Font.font("System Bold", 18));
        bottomTitleText.setLayoutX(490);
        bottomTitleText.setLayoutY(33);

        Text bottomDescriptionText = new Text(
                "At EasyGoo, we're working hard to make our platform as secure as it can be. But when scams do happen, we want you to know exactly how to avoid and report them. Follow our tips to help us keep you safe.");
        bottomDescriptionText.setFill(javafx.scene.paint.Color.WHITE);
        bottomDescriptionText.setFont(Font.font("Arial Black", 13));
        bottomDescriptionText.setWrappingWidth(371);
        bottomDescriptionText.setLayoutX(490);
        bottomDescriptionText.setLayoutY(53);

        bottomPane.getChildren().addAll(bottomImageView, bottomTitleText, bottomDescriptionText);

        // Scroll Bar
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setLayoutX(935);
        scrollBar.setLayoutY(58);
        scrollBar.setOrientation(javafx.geometry.Orientation.VERTICAL);
        scrollBar.setPrefHeight(642);
        scrollBar.setPrefWidth(15);
        scrollBar.setMax(700);
        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                root.setLayoutY(-newValue.doubleValue() + 10);
            }

        });

        root.getChildren().addAll(topPane, textImage, mainImage, searchPane, locationBox, bottomPane, scrollBar);

        Scene passengerScene = new Scene(root);
        String cssPath = "/style.css";
        passengerScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        return passengerScene;

    }

    private void ShowVehicleChooseDashboard() {
        VehicleChooseDashboard vehicleChooseDashboard = new VehicleChooseDashboard(primaryStage, this);
        Scene vehiclScene = vehicleChooseDashboard.creatVehicleChooseDashboard(primaryStage);
        primaryStage.setScene(vehiclScene);
        primaryStage.setTitle("User page");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }

   
    private void ShowAvailableRides() throws NoRidesAvailableException {
        AvailableRidesDashboard availableRidesDashboard = new AvailableRidesDashboard(primaryStage, leavingFrom,goingTo,this);
        Scene availableridesScene = availableRidesDashboard.createAvailableRidesScene(primaryStage, leavingFrom,goingTo ,vehicleType);
        primaryStage.setScene(availableridesScene);
        primaryStage.setTitle("Available rides");
        primaryStage.show();

    }

}
