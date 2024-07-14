package com.ridesharing.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import com.ridesharing.dashboards.FrontPage;
import com.ridesharing.firebaseconfig.DataService;

public class SignupController {

    private LoginController loginController;
    private FrontPage frontPage;
    private Stage primaryStage;

    public SignupController(Stage primaryStage, LoginController loginController) {
        this.primaryStage = primaryStage;
        this.loginController = loginController;
        DataService dataService = new DataService();
    }

    public SignupController(Stage primaryStage, FrontPage frontPage) {
        this.primaryStage = primaryStage;
        this.frontPage = frontPage;
        DataService dataService = new DataService();

    }

    public Scene createSignupScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(950, 700);

        // Left Pane
        AnchorPane leftPane = new AnchorPane();
        leftPane.setPrefSize(354, 642);
        BorderPane.setMargin(leftPane, new Insets(0, 0, 0, 20)); // Adjust margin as needed

        AnchorPane innerLeftPane = new AnchorPane();
        innerLeftPane.setPrefSize(486, 700);
        leftPane.getChildren().add(innerLeftPane);

        Text helloText = new Text("Hello!");
        helloText.setFill(Color.BLACK);
        helloText.setFont(Font.font("Calibri", 48));
        helloText.setLayoutX(206);
        helloText.setLayoutY(119);
        innerLeftPane.getChildren().add(helloText);

        Text registerText = new Text("Register your account");
        registerText.setFill(Color.web("#2882ea"));
        registerText.setFont(Font.font(24));
        registerText.setLayoutX(159);
        registerText.setLayoutY(157);
        innerLeftPane.getChildren().add(registerText);

        TextField emailField = new TextField();
        emailField.setLayoutX(86);
        emailField.setLayoutY(264);
        emailField.setPrefSize(332, 48);
        emailField.setPromptText("username");
        emailField.setStyle(
                "-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent; -fx-border-color: #0598ff;");
        innerLeftPane.getChildren().add(emailField);

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(86);
        passwordField.setLayoutY(384);
        passwordField.setPrefSize(335, 48);
        passwordField.setPromptText("Password");
        passwordField.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
        innerLeftPane.getChildren().add(passwordField);

        Text emailLabelText = new Text("Username");
        emailLabelText.setFill(Color.web("#0b0b0b"));
        emailLabelText.setFont(Font.font(18));
        emailLabelText.setLayoutX(79);
        emailLabelText.setLayoutY(258);
        innerLeftPane.getChildren().add(emailLabelText);

        Text passwordLabelText = new Text("Password");
        passwordLabelText.setFill(Color.web("#0b0b0b"));
        passwordLabelText.setFont(Font.font(18));
        passwordLabelText.setLayoutX(84);
        passwordLabelText.setLayoutY(378);
        innerLeftPane.getChildren().add(passwordLabelText);

        Text existingUserText = new Text("Existing user?");
        existingUserText.setFill(Color.web("#0b0b0b"));
        existingUserText.setFont(Font.font("Arial", 18));
        existingUserText.setLayoutX(121);
        existingUserText.setLayoutY(586);
        innerLeftPane.getChildren().add(existingUserText);

        Button loginButton = new Button("Log in");
        loginButton.setLayoutX(225);
        loginButton.setLayoutY(559);
        loginButton.setPrefSize(86, 30);
        loginButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #2882ea;");
        loginButton.setFont(Font.font("Arial Black", 18));
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showLoginScene();
            }

        });
        innerLeftPane.getChildren().add(loginButton);

        Button signupButton = new Button("Sign up");
        signupButton.setLayoutX(190);
        signupButton.setLayoutY(472);
        signupButton.setPrefSize(120, 40);
        signupButton.setStyle("-fx-background-color: #2882ea;");
        signupButton.setTextFill(Color.WHITE);
        signupButton.setFont(Font.font("Arial Black", 18));
        signupButton.getStyleClass().add("button");
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSignup(primaryStage, emailField.getText(), passwordField.getText());
                // Handle signup
            }
        });
        innerLeftPane.getChildren().add(signupButton);
        ImageView userIconImageView = new ImageView(new Image("Assets/images/loginusericon.jpg")); // Replace with your
                                                                                                   // image path
        userIconImageView.setFitWidth(30);
        userIconImageView.setFitHeight(30);
        userIconImageView.setLayoutX(49);
        userIconImageView.setLayoutY(273);
        innerLeftPane.getChildren().add(userIconImageView);

        ImageView keyIconImageView = new ImageView(new Image("Assets/images/key.png")); // Replace with your image path
        keyIconImageView.setFitWidth(45);
        keyIconImageView.setFitHeight(39);
        keyIconImageView.setLayoutX(42);
        keyIconImageView.setLayoutY(396);
        innerLeftPane.getChildren().add(keyIconImageView);

        Button backButton = new Button("Back");
        backButton.setLayoutX(12);
        backButton.setLayoutY(14);
        backButton.setPrefSize(59, 26);
        backButton.setStyle("-fx-background-color: #cccdcf;");
        backButton.setTextFill(Color.web("#2882ea"));
        backButton.setFont(Font.font("Arial Black", 12));
        backButton.getStyleClass().add("button");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showLoginScene();
            }

            // Handle back button action
        });
        innerLeftPane.getChildren().add(backButton);

        borderPane.setLeft(leftPane);

        // Right Pane
        AnchorPane rightPane = new AnchorPane();
        rightPane.setPrefSize(441, 700);
        rightPane.setStyle("-fx-background-color: #2882ea;");
        BorderPane.setMargin(rightPane, new Insets(0, 20, 0, 0)); // Adjust margin as needed

        AnchorPane innerRightPane = new AnchorPane();
        innerRightPane.setPrefSize(468, 700);
        innerRightPane.setStyle("-fx-background-color: #2882ea;");
        rightPane.getChildren().add(innerRightPane);

        ImageView logoImageView = new ImageView(new Image("Assets/images/logo.png")); // Replace with your image path
        logoImageView.setFitWidth(206);
        logoImageView.setFitHeight(201);
        logoImageView.setLayoutX(33);
        logoImageView.setLayoutY(240);
        innerRightPane.getChildren().add(logoImageView);

        Text appNameText = new Text("EasyGoo!");
        appNameText.setFill(Color.WHITE);
        appNameText.setFont(Font.font(48));
        appNameText.setLayoutX(202);
        appNameText.setLayoutY(360);
        innerRightPane.getChildren().add(appNameText);

        innerRightPane.setEffect(new Glow(0.09));

        borderPane.setRight(rightPane);
        Scene signupScene = new Scene(borderPane);
        String cssPath = "/style.css";
        signupScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        return signupScene;
    }

    public void showLoginScene() {

        LoginController loginController = new LoginController(primaryStage, this);
        Scene loginScene = loginController.initLoginScene();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }

    // Method to handle signup action
    private void handleSignup(Stage primaryStage, String username, String password) {
        DataService dataService;
        try {
            dataService = new DataService();
            Map<String, Object> data = new HashMap<>();
            data.put("password", password);
            data.put("username", username);
            dataService.addData("username", username, data);
            System.out.println("User registered successfully");
            showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
