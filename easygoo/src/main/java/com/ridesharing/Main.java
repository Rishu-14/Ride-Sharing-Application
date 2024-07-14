package com.ridesharing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.ridesharing.firebaseconfig.DataService;
//import com.ridesharing.initialize.InitializeApp;
import com.ridesharing.initialize.InitializeApp;

import javafx.application.Application;

public class Main {

        

    public static void main(String[] args) {
        System.out.println("Hello world!");
        
        Application.launch(InitializeApp.class, args);
    }


}
