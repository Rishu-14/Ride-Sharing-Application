package com.ridesharing.dashboards;

import com.ridesharing.controller.LoginController;
import com.ridesharing.firebaseconfig.DataService;
import com.google.cloud.firestore.DocumentSnapshot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserPage {
    static String userName;
    private DataService dataService;
    private LoginController loginController;
    private Stage primaryStage;
    private PassengerDashboard passengerDashboard;
    private DriverDashboard driverDashboard;
    private VehicleChooseDashboard vehicleChooseDashboard;
    private AboutUsDashboard aboutUsDashboard;

    public UserPage(Stage primaryStage, DataService dataService) {
        this.primaryStage = primaryStage;

        this.dataService = dataService;
    }

    public UserPage(Stage primaryStage, LoginController loginController) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.loginController = loginController;
    }

    public UserPage(Stage primaryStage, PassengerDashboard passengerDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.passengerDashboard = passengerDashboard;

    }

    public UserPage(Stage primaryStage, DriverDashboard driverDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.driverDashboard = driverDashboard;
    }

    public UserPage(Stage primaryStage, VehicleChooseDashboard vehicleChooseDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleChooseDashboard = vehicleChooseDashboard;

    }

    public UserPage(Stage primaryStage, AboutUsDashboard aboutUsDashboard) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.aboutUsDashboard = aboutUsDashboard;

    }

    public Scene createUserScene(Stage primaryStage, Runnable logoutHandler) {
        this.primaryStage = primaryStage;

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(950, 700);

        // Left pane
        AnchorPane leftPane = new AnchorPane();
        leftPane.setPrefSize(296, 700);
        leftPane.setStyle("-fx-background-color: #2882ea;");

        VBox leftVBox = new VBox(20);
        leftVBox.setAlignment(javafx.geometry.Pos.CENTER);
        leftVBox.setPrefSize(297, 258);

        ImageView userImageView = new ImageView(new Image("Assets/images/user-image.jpg"));
        userImageView.setFitWidth(109);
        userImageView.setFitHeight(104);
        userImageView.setPreserveRatio(true);

        Label usernameLabel = new Label(); // Set username dynamically based on your logic
        usernameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        usernameLabel.setFont(Font.font("Arial Black", 14));
        try {
            String key = LoginController.key;
            System.out.println("Value of key: " + key);

            DocumentSnapshot dataObject = dataService.getData("username", key);

            userName = dataObject.getString("username");

            System.out.println("username fetched:" + userName);
            usernameLabel.setText(userName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        leftVBox.getChildren().addAll(userImageView, usernameLabel);

        Button logoutButton = new Button("Log out");
        logoutButton.setLayoutX(92);
        logoutButton.setLayoutY(608);
        logoutButton.setPrefSize(126, 40);
        logoutButton.setStyle("-fx-background-color: #4e96ea;");
        logoutButton.setTextFill(javafx.scene.paint.Color.WHITE);
        logoutButton.setFont(Font.font("Arial Black", 14));
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                logoutHandler.run();
            }
        });

        ImageView logoutImageView = new ImageView(new Image("Assets/images/logout.png"));
        logoutImageView.setFitWidth(25);
        logoutImageView.setFitHeight(25);
        logoutImageView.setPreserveRatio(true);
        logoutButton.setGraphic(logoutImageView);
        logoutButton.getStyleClass().add("button");

        leftPane.getChildren().addAll(leftVBox, logoutButton);
        borderPane.setLeft(leftPane);

        // Center pane
        AnchorPane centerPane = new AnchorPane();
        centerPane.setPrefSize(654, 700);
        centerPane.setStyle("-fx-background-color: white;");

        ImageView centerImageView = new ImageView(
                new Image("Assets/images/WhatsApp Image 2024-07-03 at 12.25.20 AM.jpeg"));
        centerImageView.setFitWidth(559);
        centerImageView.setFitHeight(405);
        centerImageView.setLayoutX(70);
        centerImageView.setLayoutY(14);
        centerImageView.setPreserveRatio(true);

        Text centerText = new Text("Are you?");
        centerText.setLayoutX(291);
        centerText.setLayoutY(447);
        centerText.setFont(Font.font("Calibri Italic", 18));

        Button driverButton = new Button("Driver");
        driverButton.setLayoutX(242);
        driverButton.setLayoutY(486);
        driverButton.setPrefSize(162, 34);
        driverButton.setStyle("-fx-background-color: #2882ea;");
        driverButton.setTextFill(javafx.scene.paint.Color.WHITE);
        driverButton.setFont(Font.font("Arial Black", 18));
        driverButton.getStyleClass().add("button");

        driverButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ShowDriverDashboard();
            }

        });

        Button passengerButton = new Button("Passenger");
        passengerButton.setLayoutX(242);
        passengerButton.setLayoutY(556);
        passengerButton.setPrefSize(162, 34);
        passengerButton.setStyle("-fx-background-color: #2882ea;");
        passengerButton.setTextFill(javafx.scene.paint.Color.WHITE);
        passengerButton.setFont(Font.font("Arial Black", 18));
        passengerButton.getStyleClass().add("button");
        passengerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ShowVehicleChooseDashboard();
            }

        });
        Button aboutUsButton = new Button("About us");
        aboutUsButton.setLayoutX(485);
        aboutUsButton.setLayoutY(12);
        aboutUsButton.setPrefSize(148, 35);
        aboutUsButton.setStyle("-fx-background-color: white;");
        aboutUsButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        aboutUsButton.setFont(new Font("Arial Black", 14));
        aboutUsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showAboutUsDashboard();
            }

        });

        ImageView downArrowImageView = new ImageView(new Image("Assets/images/downarrow.png"));
        downArrowImageView.setFitHeight(20);
        downArrowImageView.setFitWidth(20);
        downArrowImageView.setLayoutX(592);
        downArrowImageView.setLayoutY(20);
        downArrowImageView.setPreserveRatio(true);

        centerPane.getChildren().addAll(centerImageView, centerText, driverButton, passengerButton, aboutUsButton);
        borderPane.setCenter(centerPane);

        Scene userScene = new Scene(borderPane);
        String cssPath = "/style.css";
        userScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());

        return userScene;

    }

    private void ShowVehicleChooseDashboard() {
        VehicleChooseDashboard vehicleChooseDashboard = new VehicleChooseDashboard(primaryStage, this);
        Scene vehicleScene = vehicleChooseDashboard.creatVehicleChooseDashboard(primaryStage);
        primaryStage.setScene(vehicleScene);
        primaryStage.setTitle("Vehicle Dashboard");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();

    }

    private void ShowDriverDashboard() {
        DriverDashboard driverDashboard = new DriverDashboard(primaryStage, this);
        Scene driverScene = driverDashboard.createDriverScene(primaryStage);
        primaryStage.setScene(driverScene);
        primaryStage.setTitle("Driver Dashboard");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();

    }

    public void showAboutUsDashboard() {
        AboutUsDashboard aboutUsDashboard = new AboutUsDashboard();
        Scene AboutUsScene = aboutUsDashboard.crateAboutUsScene(primaryStage);
        primaryStage.setScene(AboutUsScene);
        primaryStage.setTitle("About Us");
        primaryStage.show();

    }

}
