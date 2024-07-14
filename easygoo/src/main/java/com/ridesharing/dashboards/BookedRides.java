package com.ridesharing.dashboards;

import java.util.List;

import com.ridesharing.firebaseconfig.DataService;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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

public class BookedRides {

    private Stage primaryStage;
    private DataService dataService;
    private PassengerDashboard passengerDashboard;
    private VehicleChooseDashboard vehicleChooseDashboard;
    private PassengerInfo passengerInfo;
    private DriverDashboard driverDashboard;
    private PublishDashboard publishDashboard;
    private String vehicleType;
    private Ride ride;
    private String loggedInUsername;
    ListView<Ride> rideListView = new ListView<>();
    
    public BookedRides(Stage primaryStage,DataService dataService) {
        this.primaryStage = primaryStage;
        this.dataService = dataService;
        this.rideListView = new ListView<>();
    
    }
    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    public BookedRides(Stage primaryStage, PassengerDashboard passengerDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerDashboard = passengerDashboard;
    }

    public BookedRides(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard, String vehicleType) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleChooseDashboard = vehicleChooseDashboard;
        this.vehicleType = vehicleType;
    }
    public BookedRides(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleChooseDashboard = vehicleChooseDashboard;
        
    }


    public BookedRides(Stage primaryStage, PassengerInfo passengerInfo) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerInfo = passengerInfo;
    }

    public BookedRides(Stage primaryStage, DriverDashboard driverDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.driverDashboard = driverDashboard;
    }

    public BookedRides(Stage primaryStage, PublishDashboard publishDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.publishDashboard = publishDashboard;
    }
    public BookedRides(Stage primaryStage, Ride ride) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.ride = ride;
    }
   

    public Scene createBookedRidesShowScene(Stage primaryStage) {
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
        backButton.setOnAction(event -> ShowVehicleChooseDashboard());

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

        ListView<Ride> rideListView = new ListView<>();
        rideListView.setLayoutX(273);
        rideListView.setLayoutY(58);
        rideListView.setPrefSize(677, 642);
        System.out.println("ride" + rideListView.getItems());

        loadBookedRides(rideListView);
        
         

        Ride obj = new Ride();
        List<Ride> list = obj.fetchBookedRides();
        System.out.println("Rahul" + list);

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
                                    new Text("Vehicle Type: " + ride.getVehicleType()),
                                    new Text("Time: " + ride.getTime())
                                    );
                            Button PayButton = new Button("Pay");
                            PayButton.setAlignment(Pos.CENTER);
                            PayButton.setStyle("-fx-background-color: #2882ea;");
                            PayButton.setTextFill(Color.WHITE);
                            PayButton.setFont(Font.font("Arial Black", 14));
                            PayButton.setOnAction(event -> {
                                try {
                                    showAlert(AlertType.INFORMATION, "Success", "Pay Cash To Driver!");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    showAlert(AlertType.ERROR, "Booking Failed",
                                            "Failed to book ride. Please try again.");
                                }
                            });

                            HBox hBox = new HBox(vbox,PayButton);
                            hBox.setSpacing(400);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

        root.getChildren().addAll(topPane, bottomPane, rideListView);
        

        // Setting up the scene and stage
        Scene BookedRidesScene = new Scene(root, 950, 700);
        return BookedRidesScene;

    }

   
    private void loadBookedRides(ListView<Ride> rideListView) {
        Task<List<Ride>> loadRidesTask = new Task<List<Ride>>() {
            @Override
            protected List<Ride> call() throws Exception {
                return dataService.getBookedRides();
            }
        };

        loadRidesTask.setOnSucceeded(event -> {
            ObservableList<Ride> rideItems = FXCollections.observableArrayList(loadRidesTask.getValue());
            rideListView.setItems(rideItems);
        });

        loadRidesTask.setOnFailed(event -> {
            Throwable exception = loadRidesTask.getException();
            exception.printStackTrace();
            showAlert(AlertType.ERROR, "Loading Failed", "Failed to load rides. Please try again.");
        });

        Thread loadRidesThread = new Thread(loadRidesTask);
        loadRidesThread.setDaemon(true);
        loadRidesThread.start();
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    private void ShowVehicleChooseDashboard() {
        VehicleChooseDashboard vehicleChooseDashboard = new VehicleChooseDashboard(primaryStage, this);
        Scene vehicleScene = vehicleChooseDashboard.creatVehicleChooseDashboard(primaryStage);
        primaryStage.setScene(vehicleScene);
        primaryStage.setTitle("Vehicle Dashboard");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }
}
