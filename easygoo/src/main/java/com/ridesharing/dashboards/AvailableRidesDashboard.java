package com.ridesharing.dashboards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.ridesharing.exception.NoRidesAvailableException;
import com.ridesharing.firebaseconfig.DataService;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

// AvailableRidesDashboard.java

public class AvailableRidesDashboard {

    private Stage primaryStage;
    private DataService dataService;
    private PassengerDashboard passengerDashboard;
    private VehicleChooseDashboard vehicleChooseDashboard;
    private PassengerInfo passengerInfo;
    private PublishDashboard publishDashboard;
    private String vehicleType;
    private String leavingFrom;
    private String goingTo;
    ListView<Ride> rideListView = new ListView<>();

    public AvailableRidesDashboard(Stage primaryStage, String leavingFrom, String goingTo, String vehicleType) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleType = vehicleType;
        this.leavingFrom = leavingFrom;
        this.goingTo = goingTo;
    }

    public AvailableRidesDashboard(Stage primaryStage, String leavingFrom, String goingTo,PassengerDashboard passengerDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerDashboard = passengerDashboard;
        this.leavingFrom = leavingFrom;
        this.goingTo = goingTo;
    }

    public AvailableRidesDashboard(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleChooseDashboard = vehicleChooseDashboard;
    }

    public AvailableRidesDashboard(Stage primaryStage, PassengerInfo passengerInfo) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerInfo = passengerInfo;
    }

    public AvailableRidesDashboard(Stage primaryStage, PublishDashboard publishDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.publishDashboard = publishDashboard;
    }

    public AvailableRidesDashboard(Stage primaryStage, PassengerDashboard passengerDashboard, String leavingFrom, String goingTo,String vehicleType) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerDashboard = passengerDashboard;
        this.vehicleType = vehicleType;
        this.leavingFrom = leavingFrom;
        this.goingTo = goingTo;
    }

    public Scene createAvailableRidesScene(Stage primaryStage, String leavingFrom, String goingTo, String vehicleType) throws NoRidesAvailableException {
        this.primaryStage = primaryStage;
        AnchorPane root = new AnchorPane();

        AnchorPane topPane = new AnchorPane();
        topPane.setStyle("-fx-background-color: white;");
        topPane.setPrefSize(950, 58);
        topPane.setLayoutX(0);
        topPane.setLayoutY(0);

        ImageView carLogo = new ImageView(new Image("Assets/images/carlogo.png"));
        carLogo.setLayoutX(63);
        carLogo.setLayoutY(4);
        carLogo.setFitWidth(52);
        carLogo.setFitHeight(52);

        Text easyGooText = new Text("EasyGoo!");
        easyGooText.setLayoutX(115);
        easyGooText.setLayoutY(36);
        easyGooText.setStyle("-fx-fill: #2882ea;");
        easyGooText.setFont(Font.font("System Bold", 18));

        ImageView userProfile = new ImageView(new Image("Assets/images/Account-User.png"));
        userProfile.setLayoutX(886);
        userProfile.setLayoutY(8);
        userProfile.setFitWidth(43);
        userProfile.setFitHeight(43);

        Button backButton = new Button();
        backButton.setLayoutY(13);
        backButton.setMinWidth(52);
        backButton.setPrefWidth(52);
        backButton.setPrefHeight(30);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnAction(event -> showPassengerDashboard());

        ImageView backImage = new ImageView(new Image("Assets/images/back.png"));
        backImage.setFitWidth(42);
        backImage.setFitHeight(30);
        backButton.setGraphic(backImage);

        topPane.getChildren().addAll(carLogo, easyGooText, userProfile, backButton);

        AnchorPane bottomPane = new AnchorPane();
        bottomPane.setStyle("-fx-background-color: #2882ea;");
        bottomPane.setPrefSize(273, 642);
        bottomPane.setLayoutY(58);
        bottomPane.setLayoutX(0);

        ImageView logoImage = new ImageView(new Image("Assets/images/logo.png"));
        logoImage.setLayoutY(238);
        logoImage.setFitWidth(181);
        logoImage.setFitHeight(166);

        Text bottomText = new Text("EasyGoo!");
        bottomText.setLayoutX(144);
        bottomText.setLayoutY(331);
        bottomText.setStyle("-fx-fill: #f4f0f0;");
        bottomText.setFont(Font.font(24));

        bottomPane.getChildren().addAll(logoImage, bottomText);

        rideListView.setLayoutX(273);
        rideListView.setLayoutY(58);
        rideListView.setPrefSize(677, 642);

        loadRides(leavingFrom, goingTo, vehicleType);

        // Check for null values
        if (passengerDashboard == null) {
            throw new NoRidesAvailableException("Passenger Dashboard is not initialized.");
        }

        if (vehicleType == null || vehicleType.isEmpty()) {
            throw new NoRidesAvailableException("Vehicle type is not specified.");
        }

        if (leavingFrom == null || goingTo == null) {
            throw new NoRidesAvailableException("Location details are not provided.");
        }

      

        rideListView.setCellFactory(new Callback<ListView<Ride>, ListCell<Ride>>() {
            @Override
            public ListCell<Ride> call(ListView<Ride> listView) {
                return new ListCell<Ride>() {
                    @Override
                    protected void updateItem(Ride ride, boolean empty) {
                        super.updateItem(ride, empty);
                        if (empty || ride == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            VBox vbox = new VBox(
                                    new Text("Driver Name: " + ride.getFullName()),
                                    new Text("Mobile No.: " + ride.getMobileNumber()),
                                    new Text("Leaving From: " + ride.getLeavingFrom()),
                                    new Text("Going To: " + ride.getGoingTo()),
                                    new Text("Amount: " + ride.getPrice()),
                                    new Text("Total Passenger Seats : " + ride.getTotalPassenger()),
                                    new Text("Vehicle Type: " + ride.getVehicleType()),
                                    new Text("Time: " + ride.getTime())
                            );
                            Button bookButton = new Button("Book");
                            bookButton.setAlignment(Pos.CENTER);
                            bookButton.setStyle("-fx-background-color: #2882ea;");
                            bookButton.setTextFill(Color.WHITE);
                            bookButton.setFont(Font.font("Arial Black", 14));
                            bookButton.setOnAction(event -> {
                                try {
                                    dataService.bookRide(ride);
                                    showAlert(AlertType.INFORMATION, "Success", "Ride booked successfully!");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    showAlert(AlertType.ERROR, "Booking Failed", "Failed to book ride. Please try again.");
                                }
                            });

                            HBox hBox = new HBox(vbox, bookButton);
                            hBox.setSpacing(350);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

        root.getChildren().addAll(topPane, bottomPane, rideListView);

        Scene availableRidesScene = new Scene(root, 950, 700);
        return availableRidesScene;
    }

    private void loadRides(String leavingFrom, String goingTo, String vehicleType) {
        Task<List<Ride>> loadRidesTask = new Task<List<Ride>>() {
            @Override
            protected List<Ride> call() throws Exception {
                Ride rideFetcher = new Ride();
                rideFetcher.setDataService(new DataService());

                List<Ride> rides = rideFetcher.fetchRides(leavingFrom, goingTo, vehicleType);

                rideListView.getItems().setAll(rides);
                return rides;
            }
        };

        loadRidesTask.setOnSucceeded(event -> {
            ObservableList<Ride> rideItems = FXCollections.observableArrayList(loadRidesTask.getValue());
            rideListView.setItems(rideItems);
        });

        loadRidesTask.setOnFailed(event -> {
            Throwable exception = loadRidesTask.getException();
            if (exception instanceof NoRidesAvailableException) {
                showAlert(Alert.AlertType.INFORMATION, "No Rides Available", exception.getMessage());
            } else {
                exception.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Loading Failed", "Failed to load rides. Please try again.");
            }
        });

        Thread loadRidesThread = new Thread(loadRidesTask);
        loadRidesThread.setDaemon(true);
        loadRidesThread.start();
    }

    private void showPassengerDashboard() {
        if (passengerDashboard != null) {
            try {
                Scene passengerDashboardScene = passengerDashboard.createPassengerScene(primaryStage,vehicleChooseDashboard);
                primaryStage.setScene(passengerDashboardScene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Passenger Dashboard. Please try again.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Passenger Dashboard is not available.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

