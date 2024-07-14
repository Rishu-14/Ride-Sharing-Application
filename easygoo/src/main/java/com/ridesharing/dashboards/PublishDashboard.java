package com.ridesharing.dashboards;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginContext;

import com.ridesharing.controller.LoginController;
import com.ridesharing.firebaseconfig.DataService;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PublishDashboard {

    private Stage primaryStage;
    private DriverDashboard driverDashboard;
    private DataService dataService;
    private Ride ride;
    private String vehicleType;
    private LoginController loginController;
    private AvailableRidesDashboard availableRidesDashboard;

    public PublishDashboard(Stage primaryStage, DriverDashboard driverDashboard,String vehicleType) {
        System.out.println("VEHICAL TYPE");
        System.out.println(vehicleType);
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.vehicleType = vehicleType;

    }

    public PublishDashboard(Stage primaryStage,DriverDashboard driverDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.driverDashboard = driverDashboard;
       

    }
    public PublishDashboard(Stage primaryStage,LoginController loginController){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.loginController = loginController;
    }
    public PublishDashboard(Stage primaryStage, Ride ride){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.ride = ride;
    }
    public PublishDashboard(Stage primaryStage,AvailableRidesDashboard availableRidesDashboard){
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        this.availableRidesDashboard = availableRidesDashboard;
    }



    public Scene creatPublishDashboard(Stage primaryStage,String vehicleType){
        this.primaryStage = primaryStage;
        this.vehicleType = vehicleType;

        AnchorPane root = new AnchorPane();
        root.setPrefSize(950, 700);

        // Top AnchorPane
        AnchorPane topPane = new AnchorPane();
        topPane.setStyle("-fx-background-color: white;");
        topPane.setPrefSize(950, 58);
        topPane.setLayoutX(0);
        topPane.setLayoutY(0);

        ImageView logoImageView = new ImageView(new Image("Assets/images/carlogo.png"));
        logoImageView.setFitHeight(52);
        logoImageView.setFitWidth(52);
        logoImageView.setLayoutX(63);
        logoImageView.setLayoutY(4);

        Text appTitle = new Text("EasyGoo!");
        appTitle.setFill(javafx.scene.paint.Color.web("#2882ea"));
        appTitle.setFont(Font.font("System Bold", 18));
        appTitle.setLayoutX(115);
        appTitle.setLayoutY(36);

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
                ShowDriverDashboard();
            }
            
        });

        Button myRidesButton = new Button("My Rides");
        myRidesButton.setLayoutX(791);
        myRidesButton.setLayoutY(16);
        myRidesButton.setStyle("-fx-background-color: transparent;");
        myRidesButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        myRidesButton.setFont(new Font("Arial Black", 14));
        myRidesButton.getStyleClass().add("button");
        

        topPane.getChildren().addAll(logoImageView, appTitle, userProfileImageView, backButton, myRidesButton);

        // Middle AnchorPane
        AnchorPane middlePane = new AnchorPane();
        middlePane.setStyle("-fx-background-color: #e3f8ff;");
        middlePane.setPrefSize(950, 434);
        middlePane.setLayoutY(58);

        ImageView driverImageView = new ImageView(new Image("Assets/images/driverpageimage.png"));
        driverImageView.setFitHeight(317);
        driverImageView.setFitWidth(563);
        driverImageView.setLayoutX(373);
        driverImageView.setLayoutY(133);

        AnchorPane formPane = new AnchorPane();
        formPane.setStyle("-fx-background-color: white;");
        formPane.setPrefSize(415, 386);
        formPane.setLayoutX(27);
        formPane.setLayoutY(24);
        formPane.setEffect(new DropShadow());

        TextField fullNameField = new TextField();
        fullNameField.setLayoutX(28);
        fullNameField.setLayoutY(14);
        fullNameField.setPrefSize(370, 43);
        fullNameField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");
        fullNameField.setFont(new Font("System Bold", 12));
        fullNameField.setPromptText("Full Name");

        HBox genderBox = new HBox(20);
        genderBox.setLayoutX(28);
        genderBox.setLayoutY(255);

        RadioButton femaleRadioButton = new RadioButton("Female");
        femaleRadioButton.setMnemonicParsing(false);
        femaleRadioButton.setPrefSize(122, 20);
        femaleRadioButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        femaleRadioButton.setFont(new Font("Arial Black", 13));

        RadioButton maleRadioButton = new RadioButton("Male");
        maleRadioButton.setMnemonicParsing(false);
        maleRadioButton.setPrefSize(78, 20);
        maleRadioButton.setTextFill(javafx.scene.paint.Color.web("#2882ea"));
        maleRadioButton.setFont(new Font("Arial Black", 13));

        genderBox.getChildren().addAll(femaleRadioButton, maleRadioButton);

        Text genderText = new Text("Gender");
        genderText.setLayoutX(28);
        genderText.setLayoutY(236);
        genderText.setFont(new Font("Calibri", 14));

        TextField ageField = new TextField();
        ageField.setLayoutX(28);
        ageField.setLayoutY(119);
        ageField.setPrefSize(170, 43);
        ageField.setPromptText("Age");
        ageField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        TextField mobileNumberField = new TextField();
        mobileNumberField.setLayoutX(28);
        mobileNumberField.setLayoutY(171);
        mobileNumberField.setPrefSize(170, 43);
        mobileNumberField.setPromptText("Mobile No.");
        mobileNumberField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");

        TextField priceField = new TextField();
        priceField.setLayoutX(242);
        priceField.setLayoutY(171);
        priceField.setPrefSize(155,43);
        priceField.setPromptText("Amount");
        priceField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutX(221);
        datePicker.setLayoutY(288);
        datePicker.setPrefSize(169, 30);
        datePicker.setPromptText("Date");
        datePicker.setStyle("-fx-background-color: white; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        TextField timeField = new TextField();
        timeField.setLayoutX(28);
        timeField.setLayoutY(288);
        timeField.setPrefSize(155, 30);
        timeField.setPromptText("Time");
        timeField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        TextField leavingField = new TextField();
        leavingField.setLayoutX(28);
        leavingField.setLayoutY(71);
        leavingField.setPrefSize(155, 43);
        leavingField.setPromptText("Leaving From");
        leavingField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        ImageView directionImage = new ImageView(new Image("Assets/images/direction.jpg"));
        directionImage.setFitHeight(43);
        directionImage.setFitWidth(42);
        directionImage.setLayoutX(198);
        directionImage.setLayoutY(72);

        TextField goingToField = new TextField();
        goingToField.setLayoutX(247);
        goingToField.setLayoutY(71);
        goingToField.setPrefSize(155, 43);
        goingToField.setPromptText("Going To");
        goingToField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        TextField totalPassengerField = new TextField();
        totalPassengerField.setLayoutX(240);
        totalPassengerField.setLayoutY(119);
        totalPassengerField.setPrefSize(162, 43);
        totalPassengerField.setPromptText("Total seats");
        totalPassengerField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");


        Button publishButton = new Button("Publish ride");
        publishButton.setLayoutX(153);
        publishButton.setLayoutY(328);
        publishButton.setPrefSize(115, 47);
        publishButton.setTextFill(javafx.scene.paint.Color.WHITE);
        publishButton.setStyle("-fx-background-color: #2882ea;");
        publishButton.setFont(new Font("Arial Black", 14));
        publishButton.getStyleClass().add("button");
        publishButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                    String fullName = fullNameField.getText();
                    String mobileNumber = mobileNumberField.getText();
                    String age = ageField.getText();
                    String gender = maleRadioButton.isSelected() ? "Female" : "Male";
                    String leavingFrom = leavingField.getText();
                    String goingTo = goingToField.getText();
                    LocalDate date = datePicker.getValue();
                    String time = timeField.getText();
                    String totalPassenger = totalPassengerField.getText();
                    String price = priceField.getText();

                    // Validate the fields
                    if (fullName.isEmpty() || leavingFrom.isEmpty() || goingTo.isEmpty() || date == null || time.isEmpty()) {
                        System.out.println("Please fill all the fields");
                        return;
                    }
                    String dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

                    try {
                        
                        handlePublish(fullName, mobileNumber, age, gender,leavingFrom, goingTo, dateString, time, totalPassenger,price,vehicleType);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    fullNameField.clear();
                    mobileNumberField.clear();
                    ageField.clear();
                    leavingField.clear();
                    goingToField.clear();
                    timeField.clear();
                    priceField.clear();
            
            }
            
        });

        formPane.getChildren().addAll(fullNameField,genderBox, genderText,
                ageField, mobileNumberField, publishButton, datePicker, timeField, leavingField,
                directionImage, goingToField, totalPassengerField,priceField);

        middlePane.getChildren().addAll(driverImageView, formPane);

        // Bottom ImageView
        ImageView bottomImageView = new ImageView(new Image("Assets/images/drivertext.png"));
        bottomImageView.setFitHeight(207);
        bottomImageView.setFitWidth(950);
        bottomImageView.setLayoutX(0);
        bottomImageView.setLayoutY(492);

        root.getChildren().addAll(topPane, middlePane, bottomImageView);


        Scene publishdashboardScene = new Scene(root);
        String cssPath = "/style.css";
        publishdashboardScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        return publishdashboardScene;

    }
    private void ShowDriverDashboard(){
        DriverDashboard driverDashboard = new DriverDashboard(primaryStage,this);
        Scene driverScene = driverDashboard.createDriverScene(primaryStage);
        primaryStage.setScene(driverScene);
        primaryStage.setTitle("Driver Dashboard");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));
        primaryStage.show();
    }
    private void handlePublish(String fullName, String mobileNumber,String age, String gender,String leavingFrom, String goingTo, String dateString, String time, String totalPassenger,String price,String vehicleType) throws InterruptedException, ExecutionException {
        // Create a new Ride object and publish it using DataService
        System.out.println(vehicleType);
        Ride ride = new Ride(fullName, mobileNumber, age, gender, leavingFrom, goingTo, dateString, time, totalPassenger,price,vehicleType);
        dataService.publishRide(ride);
        showAlert(AlertType.INFORMATION, "Success", "Ride published successfully!");
        System.out.println("Ride Published: " + ride.toString());
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

   

   

}
