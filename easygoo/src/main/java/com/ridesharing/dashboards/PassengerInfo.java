package com.ridesharing.dashboards;

import com.ridesharing.firebaseconfig.DataService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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

public class PassengerInfo {

    private Stage primaryStage;
    private DataService dataService;
    private PassengerDashboard passengerDashboard;

    public PassengerInfo(Stage primaryStage, DataService dataService){
        this.primaryStage = primaryStage;
        this.dataService = dataService;
    }

    public PassengerInfo(Stage primaryStage,PassengerDashboard passengerDashboard){
        this.primaryStage = primaryStage;
        this.dataService = dataService;
        this.passengerDashboard = passengerDashboard;

    }

    public Scene creatPassengerSearch(Stage primaryStage){
        this.primaryStage = primaryStage;

        AnchorPane root = new AnchorPane();
        root.setPrefSize(950, 700);

        AnchorPane headerPane = new AnchorPane();
        headerPane.setLayoutX(5);
        headerPane.setLayoutY(0);
        headerPane.setPrefSize(950, 58);
        headerPane.setStyle("-fx-background-color: white;");

        ImageView logo = new ImageView(new Image("Assets/images/carlogo.png"));
        logo.setFitHeight(52);
        logo.setFitWidth(52);
        logo.setLayoutX(63);
        logo.setLayoutY(4);

        Text title = new Text("EasyGoo!");
        title.setFill(javafx.scene.paint.Color.web("#2882ea"));
        title.setFont(new Font("System Bold", 18));
        title.setLayoutX(115);
        title.setLayoutY(36);

        ImageView userProfile = new ImageView(new Image("Assets/images/Account-User.png"));
        userProfile.setFitHeight(43);
        userProfile.setFitWidth(43);
        userProfile.setLayoutX(886);
        userProfile.setLayoutY(8);

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
       
        headerPane.getChildren().addAll(logo, title, userProfile, backButton);

        AnchorPane contentPane = new AnchorPane();
        contentPane.setLayoutY(58);
        contentPane.setPrefSize(950, 434);
        contentPane.setStyle("-fx-background-color: #e3f8ff;");

        ImageView mainImage = new ImageView(new Image("Assets/images/driverpageimage.png"));
        mainImage.setFitHeight(317);
        mainImage.setFitWidth(563);
        mainImage.setLayoutX(360);
        mainImage.setLayoutY(124);

        AnchorPane formPane = new AnchorPane();
        formPane.setLayoutX(70);
        formPane.setLayoutY(45);
        formPane.setPrefSize(319, 366);
        formPane.setStyle("-fx-background-color: white;");
        formPane.setEffect(new DropShadow());

        TextField fullnameField = new TextField();
        fullnameField.setLayoutX(28);
        fullnameField.setLayoutY(14);
        fullnameField.setPrefSize(278, 43);
        fullnameField.setPromptText("Full Name");
        fullnameField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");

        TextField mobilenumber = new TextField();
        mobilenumber.setLayoutX(28);
        mobilenumber.setLayoutY(65);
        mobilenumber.setPrefSize(278, 43);
        mobilenumber.setPromptText("Mobile No.");
        mobilenumber.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");

        TextField age = new TextField();
        age.setLayoutX(28);
        age.setLayoutY(119);
        age.setPrefSize(278, 43);
        age.setPromptText("Age");
        age.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");

        Text genderText = new Text("Gender");
        genderText.setLayoutX(28);
        genderText.setLayoutY(186);
        genderText.setFont(new Font("Calibri", 14));

        HBox genderBox = new HBox(20);
        genderBox.setLayoutX(38);
        genderBox.setLayoutY(200);

        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setPrefSize(122, 20);
        femaleRadio.setFont(new Font("Arial Black", 13));
        femaleRadio.setTextFill(javafx.scene.paint.Color.web("#2882ea"));

        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setPrefSize(78, 20);
        maleRadio.setFont(new Font("Arial Black", 13));
        maleRadio.setTextFill(javafx.scene.paint.Color.web("#2882ea"));

        genderBox.getChildren().addAll(femaleRadio, maleRadio);

        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutX(168);
        datePicker.setLayoutY(232);
        datePicker.setPrefSize(131, 30);
        datePicker.setPromptText("Date");
        datePicker.setStyle("-fx-background-color: white; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");

        TextField timeField = new TextField();
        timeField.setLayoutX(29);
        timeField.setLayoutY(230);
        timeField.setPrefSize(131, 30);
        timeField.setPromptText("Time");
        timeField.setStyle("-fx-background-color: transparent; -fx-border-color: #2882ea; -fx-border-width: 0px 0px 2px 0px;");

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(82);
        searchButton.setLayoutY(296);
        searchButton.setPrefSize(155, 43);
        searchButton.setTextFill(javafx.scene.paint.Color.web("#fcf8f8"));
        searchButton.setStyle("-fx-background-color: #2882ea;");
        searchButton.setFont(new Font("Arial Black", 14));

        formPane.getChildren().addAll(fullnameField, mobilenumber, age, genderText, genderBox, datePicker, timeField, searchButton);

        contentPane.getChildren().addAll(mainImage, formPane);

        ImageView footerImage = new ImageView(new Image("Assets/images/drivertext.png"));
        footerImage.setFitHeight(207);
        footerImage.setFitWidth(950);
        footerImage.setLayoutX(0);
        footerImage.setLayoutY(492);

        root.getChildren().addAll(headerPane, contentPane, footerImage);

        Scene passengerSearchScene = new Scene(root);
        String cssPath = "/style.css";
        passengerSearchScene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
        return passengerSearchScene;

    }
   /*  public void ShowPassengerDashboard(){
        PassengerDashboard passengerDashboard = new PassengerDashboard(primaryStage, this);
        Scene passengerScene = passengerDashboard.createPassengerScene(primaryStage, this);
        primaryStage.setScene(passengerScene);
        primaryStage.setTitle("PassengerDashBoard");
        primaryStage.getIcons().add(new Image("Assets/images/logo.png"));


    }*/

    
}
