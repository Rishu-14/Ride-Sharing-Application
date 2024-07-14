package com.ridesharing.dashboards;

import com.ridesharing.controller.LoginController;
import com.ridesharing.firebaseconfig.DataService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VehicleChooseDashboard {
    private Stage primaryStage;
    private DataService dataService;
    private UserPage userPage;
    private PublishDashboard publishDashboard;
    private Ride ride;
    private PassengerDashboard passengerDashboard;
    private BookedRides bookedRides;

    public VehicleChooseDashboard(Stage primaryStage,DataService dataService){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();

    }

    public VehicleChooseDashboard(Stage primaryStage,UserPage userPage){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.userPage = userPage;
    }
    public VehicleChooseDashboard(Stage primaryStage, PassengerDashboard passengerDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerDashboard = passengerDashboard;

    }
    public VehicleChooseDashboard(Stage primaryStage, BookedRides bookedRides){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.bookedRides = bookedRides;

    }




    public Scene creatVehicleChooseDashboard(Stage primaryStage){

        this.primaryStage = primaryStage;

        AnchorPane root = new AnchorPane();
        root.setPrefSize(950, 700);
        root.setStyle("-fx-background-color: white;");

        // Header AnchorPane
        AnchorPane headerPane = new AnchorPane();
        headerPane.setLayoutX(15);
        headerPane.setLayoutY(20);
        headerPane.setPrefSize(950, 58);
        headerPane.setStyle("-fx-background-color: white;");
        AnchorPane.setTopAnchor(headerPane, 0.0);
        AnchorPane.setLeftAnchor(headerPane, 0.0);
        AnchorPane.setRightAnchor(headerPane, 0.0);

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
        backButton.setMinWidth(52);
        backButton.setPrefSize(52, 30);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        backButton.setFont(new Font("Arial Black", 11));
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ShowUserPage();
            }
            
        });

        ImageView backButtonImageView = new ImageView(new Image("Assets/images/back.png"));
        backButtonImageView.setFitHeight(30);
        backButtonImageView.setFitWidth(42);
        backButton.setGraphic(backButtonImageView);
        backButton.getStyleClass().add("button");

        Button myBookingButton = new Button("My Booking");
        myBookingButton.setLayoutX(780);
        myBookingButton.setLayoutY(16);
        myBookingButton.setStyle("-fx-background-color: transparent;");
        myBookingButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        myBookingButton.setFont(new Font("Arial Black", 14));
        myBookingButton.getStyleClass().add("button");
        myBookingButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showBookedRidesScene();
            }

        });


        headerPane.getChildren().addAll(carLogoImageView, easyGooText, userProfileImageView, backButton, myBookingButton);

        // Main AnchorPane
        AnchorPane mainPane = new AnchorPane();
        mainPane.setLayoutX(10);
        mainPane.setLayoutY(68);
        mainPane.setPrefSize(950, 434);
        mainPane.setStyle("-fx-background-color: #e3f8ff;");
        AnchorPane.setTopAnchor(mainPane, 59.0);
        AnchorPane.setLeftAnchor(mainPane, 0.0);
        AnchorPane.setRightAnchor(mainPane, 0.0);

        ImageView driverPageImageView = new ImageView(new Image("Assets/images/driverpageimage.png"));
        driverPageImageView.setFitHeight(289);
        driverPageImageView.setFitWidth(653);
        driverPageImageView.setLayoutX(149);
        driverPageImageView.setLayoutY(68);
        driverPageImageView.setOpacity(0.06);

        Text chooseVehicleText = new Text("Choose your vehicle type!");
        chooseVehicleText.setFill(javafx.scene.paint.Color.web("#131313"));
        chooseVehicleText.setFont(new Font("Rockwell Extra Bold", 24));
        chooseVehicleText.setLayoutX(318);
        chooseVehicleText.setLayoutY(101);
        chooseVehicleText.setWrappingWidth(373.2593994140625);

        HBox vehicleTypeBox = new HBox(80);
        vehicleTypeBox.setLayoutX(137);
        vehicleTypeBox.setLayoutY(280);
        vehicleTypeBox.setPrefSize(676, 43);

        Button carButton = new Button("Car");
        carButton.setPrefSize(172, 40);
        carButton.setStyle("-fx-background-color: #2882ea;");
        carButton.setTextFill(javafx.scene.paint.Color.WHITE);
        carButton.setFont(new Font("Arial Black", 18));
        carButton.getStyleClass().add("button");
        carButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showPassengerDashboard("Car");
            }
            
        });

        Button bikeButton = new Button("Bike");
        bikeButton.setPrefSize(172, 40);
        bikeButton.setStyle("-fx-background-color: #2882ea;");
        bikeButton.setTextFill(javafx.scene.paint.Color.WHITE);
        bikeButton.setFont(new Font("Arial Black", 18));
        bikeButton.getStyleClass().add("button");
        bikeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPassengerDashboard("Bike");
            }
        });

        Button scootyButton = new Button("Scooty");
        scootyButton.setPrefSize(172, 40);
        scootyButton.setStyle("-fx-background-color: #2882ea;");
        scootyButton.setTextFill(javafx.scene.paint.Color.WHITE);
        scootyButton.setFont(new Font("Arial Black", 18));
        scootyButton.getStyleClass().add("button");
        scootyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPassengerDashboard("Scooty");
            }
        });

        vehicleTypeBox.getChildren().addAll(carButton, bikeButton, scootyButton);

        HBox vehicleImageBox = new HBox(130);
        vehicleImageBox.setLayoutX(138);
        vehicleImageBox.setLayoutY(151);
        vehicleImageBox.setPrefSize(676, 0);

        ImageView carImageView = new ImageView(new Image("Assets/images/car.png"));
        carImageView.setFitHeight(138);
        carImageView.setFitWidth(132);

        ImageView bikeImageView = new ImageView(new Image("Assets/images/bike1.png"));
        bikeImageView.setFitHeight(85);
        bikeImageView.setFitWidth(125);

        ImageView scootyImageView = new ImageView(new Image("Assets/images/scooty.png"));
        scootyImageView.setFitHeight(87);
        scootyImageView.setFitWidth(121);

        vehicleImageBox.getChildren().addAll(carImageView, bikeImageView, scootyImageView);

        mainPane.getChildren().addAll(driverPageImageView, chooseVehicleText, vehicleTypeBox, vehicleImageBox);

        // Footer ImageView
        ImageView driverTextImageView = new ImageView(new Image("Assets/images/passengertext.png"));
        driverTextImageView.setFitHeight(214);
        driverTextImageView.setFitWidth(842);
        driverTextImageView.setLayoutX(54);
        driverTextImageView.setLayoutY(494);

        // Add all panes to the root pane
        root.getChildren().addAll(headerPane, mainPane, driverTextImageView);

        Scene vehicleScene = new Scene(root);
        String cssPath = "/style.css";
        vehicleScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        return vehicleScene;

    }
    private void ShowUserPage(){
        UserPage userPage = new UserPage(primaryStage, this);
        Scene userScene = userPage.createUserScene(primaryStage,this::logoutHandler); 
        primaryStage.setScene(userScene);
        primaryStage.setTitle("User page");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }
    public void logoutHandler() {
        LoginController loginController = new LoginController(primaryStage);

        primaryStage.setScene(loginController.getLoginScene());
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
    }

    public void showPassengerDashboard( String vehicleType){
        PassengerDashboard passengerDashboard = new PassengerDashboard(primaryStage, this , vehicleType);
        Scene passengersScene = passengerDashboard.createPassengerScene(primaryStage,this);
        primaryStage.setScene(passengersScene);
        primaryStage.setTitle("Passenger Dashboard");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();

    }
    private void showBookedRidesScene() {
        BookedRides bookedRides = new BookedRides(primaryStage,this);
        Scene BookedRidesScene = bookedRides.createBookedRidesShowScene(primaryStage);
        primaryStage.setScene(BookedRidesScene);
        primaryStage.setTitle("Booked rides");
        primaryStage.show();
    }

    public String setLoggedInUsername(String loggedInUsername) {
        return loggedInUsername;
    }

    
}
