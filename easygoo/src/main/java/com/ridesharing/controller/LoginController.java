package com.ridesharing.controller;

import com.ridesharing.dashboards.FrontPage;
import com.ridesharing.dashboards.UserPage;
import com.ridesharing.firebaseconfig.DataService;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.ExecutionException;

public class LoginController {

    private Stage primaryStage;
    private Scene loginScene;
    private Scene userScene;
    private DataService dataService;
    private FrontPage frontPage;
    private LoginController loginController;
    private SignupController signupController;
    private UserPage userPage;

    public static String key;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        System.out.println(dataService);
        initScenes();
    }

    public LoginController(Stage primaryStage, FrontPage frontPage) {
        this.primaryStage = primaryStage;
        this.frontPage = frontPage;
        this.dataService = new DataService();
    }

    public LoginController(Stage primaryStage, SignupController signupController) {
        this.primaryStage = primaryStage;
        this.signupController = signupController;
        this.dataService = new DataService();
    }

    public LoginController(Stage primaryStage, UserPage userPage) {
        this.primaryStage = primaryStage;
        this.userPage = userPage;
        this.dataService = new DataService();
    }

    private void initScenes() {

        loginScene = initLoginScene();
    }

    public Scene initLoginScene() {

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(950, 700);

        // Left Pane
        AnchorPane leftPane = new AnchorPane();
        leftPane.setPrefSize(468, 700);
        leftPane.setStyle("-fx-background-color: #2882ea;");
        borderPane.setLeft(leftPane);

        ImageView logoImageView = new ImageView(new Image("Assets/images/logo.png")); // Replace with your image path
        logoImageView.setFitWidth(206);
        logoImageView.setFitHeight(201);
        logoImageView.setLayoutX(33);
        logoImageView.setLayoutY(240);
        leftPane.getChildren().add(logoImageView);

        Text appNameText = new Text("EasyGoo!");
        appNameText.setFill(Color.web("#f4f0f0"));
        appNameText.setFont(Font.font(48));
        appNameText.setLayoutX(202);
        appNameText.setLayoutY(360);
        leftPane.getChildren().add(appNameText);

        leftPane.setEffect(new Glow(0.09));

        // Center Pane
        AnchorPane centerPane = new AnchorPane();
        centerPane.setPrefSize(486, 700);
        borderPane.setCenter(centerPane);

        Text welcomeText = new Text("Welcome");
        welcomeText.setFill(Color.web("#101010"));
        welcomeText.setFont(Font.font("Calibri", 48));
        welcomeText.setLayoutX(172);
        welcomeText.setLayoutY(119);
        centerPane.getChildren().add(welcomeText);

        Text accessAccountText = new Text("Access your account");
        accessAccountText.setFill(Color.web("#2882ea"));
        accessAccountText.setFont(Font.font(24));
        accessAccountText.setLayoutX(159);
        accessAccountText.setLayoutY(157);
        centerPane.getChildren().add(accessAccountText);

        TextField emailField = new TextField();
        emailField.setLayoutX(86);
        emailField.setLayoutY(264);
        emailField.setPrefSize(332, 48);
        emailField.setPromptText("username");
        emailField.setStyle(
                "-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent; -fx-border-color: #0598ff;");
        centerPane.getChildren().add(emailField);

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(86);
        passwordField.setLayoutY(384);
        passwordField.setPrefSize(335, 48);
        passwordField.setPromptText("Password");
        passwordField.setStyle(
                "-fx-background-color: transparent; -fx-border-color: #0598ff; -fx-border-width: 0px 0px 2px 0px;");
        centerPane.getChildren().add(passwordField);

        Text emailLabelText = new Text("Username");
        emailLabelText.setFill(Color.web("#0b0b0b"));
        emailLabelText.setFont(Font.font(18));
        emailLabelText.setLayoutX(79);
        emailLabelText.setLayoutY(258);
        centerPane.getChildren().add(emailLabelText);

        Text passwordLabelText = new Text("Password");
        passwordLabelText.setFill(Color.web("#0b0b0b"));
        passwordLabelText.setFont(Font.font(18));
        passwordLabelText.setLayoutX(84);
        passwordLabelText.setLayoutY(378);
        centerPane.getChildren().add(passwordLabelText);

        Text newUserText = new Text("New user?");
        newUserText.setFill(Color.web("#0b0b0b"));
        newUserText.setFont(Font.font("Arial", 18));
        newUserText.setLayoutX(151);
        newUserText.setLayoutY(585);
        centerPane.getChildren().add(newUserText);

        Button signupButton = new Button("Sign up");
        signupButton.setLayoutX(232);
        signupButton.setLayoutY(559);
        signupButton.setStyle("-fx-background-color: transparent;");
        signupButton.setTextFill(Color.web("#2882ea"));
        signupButton.setFont(Font.font("Arial Black", 18));
        signupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                showSignupScene();
                emailField.clear();
                passwordField.clear();
            }

        });
        centerPane.getChildren().add(signupButton);

        Button loginButton = new Button("Log in");
        loginButton.setLayoutX(190);
        loginButton.setLayoutY(472);
        loginButton.setStyle("-fx-background-color: #2882ea;");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setFont(Font.font("Arial Black", 18));
        loginButton.getStyleClass().add("button");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(emailField.getText(), passwordField.getText());
                emailField.clear();
                passwordField.clear();
            }

        });

        centerPane.getChildren().add(loginButton);

        ImageView userIconImageView = new ImageView(new Image("Assets/images/loginusericon.jpg"));

        userIconImageView.setFitWidth(30);
        userIconImageView.setFitHeight(30);
        userIconImageView.setLayoutX(49);
        userIconImageView.setLayoutY(273);
        centerPane.getChildren().add(userIconImageView);

        ImageView keyIconImageView = new ImageView(new Image("Assets/images/key.png"));
        keyIconImageView.setFitWidth(45);
        keyIconImageView.setFitHeight(39);
        keyIconImageView.setLayoutX(42);
        keyIconImageView.setLayoutY(396);
        centerPane.getChildren().add(keyIconImageView);

        Scene loginScene = new Scene(borderPane);
        String cssPath = "/style.css";
        loginScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());

        loginScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginButton.fire();
                event.consume();
            }
        });
        return loginScene;
    }

    private void initUserScene() {
        UserPage userPage = new UserPage(primaryStage, dataService); // Create UserPage instance
        userScene = userPage.createUserScene(primaryStage, this::handleLogout);
    }

    public void showLoginScene() {
        Scene loginScene = loginController.initLoginScene();
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }

    private void handleLogin(String username, String password) {
        try {
            System.out.println(dataService); // Check if dataService is not null

            if (dataService != null && dataService.authenticateUser(username, password)) {
                key = username;
                showAlert(AlertType.INFORMATION, "Success", "User Login successfull!");
                initUserScene();
                primaryStage.setScene(userScene);
                primaryStage.setTitle("Welcome Page");
                primaryStage.getIcons().add(new Image("Assets/images/logo.png"));

            } else {
                showAlert(AlertType.ERROR, "Failed", "Invalid Credential!");
                System.out.println("Invalidcredentials");
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void showSignupScene() {
        SignupController signupController = new SignupController(primaryStage, this);
        Scene signupScene = signupController.createSignupScene(primaryStage);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }

    public void handleLogout() {

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
    }

    public Scene getLoginScene() {
        return loginScene;
    }
}
