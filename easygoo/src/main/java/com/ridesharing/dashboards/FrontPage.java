package com.ridesharing.dashboards;

import com.ridesharing.controller.LoginController;
import com.ridesharing.controller.SignupController;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FrontPage extends Application {

    private Stage primaryStage;
    private LoginController loginController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Ride Sharing Application");
        Scene scene = new Scene(initialize(), 950, 700);
        String cssPath = "/style.css";
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private AnchorPane initialize() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(950, 700);

        // Background Image
        ImageView imageView = new ImageView(new Image("Assets/images/frontpageimage.jpeg")); // Replace with your image path
        imageView.setFitWidth(752);
        imageView.setFitHeight(463);
        imageView.setLayoutX(127);
        imageView.setLayoutY(7);

        Button loginButton = new Button("Log in");
        loginButton.isHover();
        loginButton.setLayoutX(381);
        loginButton.setLayoutY(492);
        loginButton.setPrefSize(187, 40);
        loginButton.setStyle("-fx-background-color: #2882ea;");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setFont(Font.font("Arial Black", FontWeight.BOLD, 18));
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showLoginScene();

                
            }
            
        });

        Button signupButton = new Button("Sign up");
        signupButton.setLayoutX(380);
        signupButton.setLayoutY(561);
        signupButton.setPrefSize(187, 40);
        signupButton.setStyle("-fx-background-color: #2882ea;");
        signupButton.setTextFill(Color.WHITE);
        signupButton.setFont(Font.font("Arial Black", FontWeight.BOLD, 18));
        signupButton.getStyleClass().add("button");
        signupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                showSignupScene();
            }
            
        });

        anchorPane.getChildren().addAll(signupButton, loginButton, imageView);
        return anchorPane;
    }

    private void showLoginScene() {

        LoginController loginController = new LoginController(primaryStage);
        Scene loginScene = loginController.initLoginScene();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }

    private void showSignupScene() {
        SignupController signupController = new SignupController(primaryStage,this);
        Scene signupScene = signupController.createSignupScene(primaryStage);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }
}
