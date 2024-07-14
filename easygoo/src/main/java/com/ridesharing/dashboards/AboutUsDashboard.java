package com.ridesharing.dashboards;

import com.ridesharing.controller.LoginController;
import com.ridesharing.firebaseconfig.DataService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AboutUsDashboard {
    private Stage primaryStage;
    private DataService dataService;
    private UserPage userPage;

    public void AboutUsDashboard(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();

    }

    public void AboutUsDashboard(Stage primaryStage, UserPage userPage) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.userPage = userPage;

    }

    public Scene crateAboutUsScene(Stage primaryStage) {

        this.primaryStage = primaryStage;
        AnchorPane root = new AnchorPane();
        root.setPrefSize(950, 700);

        // Top AnchorPane
        AnchorPane topPane = new AnchorPane();
        topPane.setLayoutX(5);
        topPane.setLayoutY(10);
        topPane.setPrefSize(950, 58);
        topPane.setStyle("-fx-background-color: white;");
        AnchorPane.setTopAnchor(topPane, 0.0);
        AnchorPane.setLeftAnchor(topPane, 0.0);
        AnchorPane.setRightAnchor(topPane, 0.0);

        ImageView carLogo = new ImageView(new Image("Assets/images/carlogo.png"));
        carLogo.setFitHeight(52);
        carLogo.setFitWidth(52);
        carLogo.setLayoutX(63);
        carLogo.setLayoutY(4);

        Text title = new Text("EasyGoo!");
        title.setFill(javafx.scene.paint.Color.web("#2882ea"));
        title.setFont(Font.font("System Bold", 18));
        title.setLayoutX(115);
        title.setLayoutY(36);

        ImageView userProfile = new ImageView(new Image("Assets/images/Account-User.png"));
        userProfile.setFitHeight(43);
        userProfile.setFitWidth(43);
        userProfile.setLayoutX(886);
        userProfile.setLayoutY(8);

        Button backButton = new Button();
        backButton.setLayoutY(13);
        backButton.setMinWidth(52);
        backButton.setPrefSize(52, 30);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        backButton.setFont(Font.font("Arial Black", 11));
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ShowUserPage();
            }

        });

        ImageView backbuttonImage = new ImageView(new Image("Assets/images/back.png"));
        backbuttonImage.setFitHeight(30);
        backbuttonImage.setFitWidth(42);
        backButton.setGraphic(backbuttonImage);

        topPane.getChildren().addAll(carLogo, title, userProfile, backButton);

        // Main AnchorPane
        AnchorPane mainPane = new AnchorPane();
        mainPane.setLayoutY(58);
        mainPane.setPrefSize(950, 434);
        mainPane.setStyle("-fx-background-color: #e3f8ff;");
        AnchorPane.setTopAnchor(mainPane, 58.0);
        AnchorPane.setLeftAnchor(mainPane, 0.0);
        AnchorPane.setRightAnchor(mainPane, 0.0);

        ImageView driverPageImage = new ImageView(new Image("Assets/images/driverpageimage.png"));
        driverPageImage.setFitHeight(317);
        driverPageImage.setFitWidth(563);
        driverPageImage.setLayoutX(373);
        driverPageImage.setLayoutY(133);
        driverPageImage.setOpacity(0.37);

        Text overviewText = new Text("Overview");
        overviewText.setFill(javafx.scene.paint.Color.web("#2882ea"));
        overviewText.setFont(Font.font("Arial Black", 18));
        overviewText.setLayoutX(441);
        overviewText.setLayoutY(34);
        overviewText.setEffect(new Glow());

        Text mainText = new Text(
                "Welcome to our ride-sharing platform, a unique solution designed to make your travel experience more convenient, affordable, and eco-friendly. Whether you're a driver looking to share your ride or a passenger searching for a cost-effective way to reach your destination, our platform connects you with the perfect match.");
        mainText.setFont(Font.font("Times New Roman", 18));
        mainText.setLayoutX(174);
        mainText.setLayoutY(66);
        mainText.setWrappingWidth(634.9990844726562);
        mainText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        mainText.setEffect(new Glow());

        Text missionTitle = new Text("Our Mission");
        missionTitle.setFill(javafx.scene.paint.Color.web("#2882ea"));
        missionTitle.setFont(Font.font("Arial Black", 18));
        missionTitle.setLayoutX(426);
        missionTitle.setLayoutY(164);
        missionTitle.setEffect(new Glow());

        Text missionText = new Text(
                "Our mission is to revolutionize the way people travel by offering a reliable, user-friendly platform that facilitates ride-sharing and helps reduce traffic congestion and carbon emissions. We aim to build a community where users can save money, meet new people, and contribute to a greener planet.");
        missionText.setFont(Font.font("Times New Roman", 18));
        missionText.setLayoutX(178);
        missionText.setLayoutY(202);
        missionText.setWrappingWidth(626.1991577148438);
        missionText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        missionText.setEffect(new Glow());

        Text featuresTitle = new Text("Key Features");
        featuresTitle.setFill(javafx.scene.paint.Color.web("#2882ea"));
        featuresTitle.setFont(Font.font("Arial Black", 18));
        featuresTitle.setLayoutX(425);
        featuresTitle.setLayoutY(310);
        featuresTitle.setEffect(new Glow());

        Text featuresText = new Text(
                "Ride Publishing and Searching: Drivers can easily publish their available rides, and passengers can search for rides that suit their schedule and route. Real-Time Updates: Our platform provides real-time updates, ensuring that drivers and passengers can communicate and coordinate efficiently.");
        featuresText.setFont(Font.font("Times New Roman", 18));
        featuresText.setLayoutX(185);
        featuresText.setLayoutY(346);
        featuresText.setWrappingWidth(626.1991577148438);
        featuresText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        featuresText.setEffect(new Glow());

        mainPane.getChildren().addAll(driverPageImage, overviewText, mainText, missionTitle, missionText, featuresTitle,
                featuresText);

        // Bottom ImageView
        ImageView bottomImage = new ImageView(new Image("Assets/images/passengertext.png"));
        bottomImage.setFitHeight(201);
        bottomImage.setFitWidth(912);
        bottomImage.setLayoutX(19);
        bottomImage.setLayoutY(497);
        bottomImage.setPickOnBounds(true);
        bottomImage.setPreserveRatio(true);
        AnchorPane.setBottomAnchor(bottomImage, 14.128173828125);
        AnchorPane.setLeftAnchor(bottomImage, 19.0);
        AnchorPane.setRightAnchor(bottomImage, 19.0);
        AnchorPane.setTopAnchor(bottomImage, 497.0);

        root.getChildren().addAll(topPane, mainPane, bottomImage);

        Scene AboutUsScene = new Scene(root);
        return AboutUsScene;
    }

    public void ShowUserPage() {
        UserPage userPage = new UserPage(primaryStage, this);
        Scene userScene = userPage.createUserScene(primaryStage, this::logoutHandler);
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

}
