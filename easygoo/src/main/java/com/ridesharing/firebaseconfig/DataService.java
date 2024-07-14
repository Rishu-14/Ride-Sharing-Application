package com.ridesharing.firebaseconfig;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.ridesharing.dashboards.Ride;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DataService {

    private static String loggedInUsername;

    public String getLoggedInUsername(){
        return loggedInUsername;
    }

    private static Firestore db;

    static {
        try {
            initializeFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(
                "Ride-Sharing-Application/easygoo/src/main/resources/ridesharing.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }
    private Object dataservice;

    public void addData(String collection, String document, Map<String, Object> data) throws ExecutionException,
            InterruptedException {
        DocumentReference docRef = db.collection(collection).document(document);
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get();
    }

    public DocumentSnapshot getData(String collection, String document)
            throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collection).document(document);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        return future.get();
    }

    public boolean authenticateUser(String username, String password) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("username").document(username).get().get();
        if (document.exists()) {
            String storedPassword = document.getString("password");
            loggedInUsername = username;
            return password.equals(storedPassword);
        }
        return false;
    }

    public void publishRide(Ride ride) throws InterruptedException, ExecutionException {
        CollectionReference ridesCollection = db.collection("rides");
        DocumentReference userRidesDocRef = ridesCollection.document(loggedInUsername);

        // Store the ride under the driver's username
        userRidesDocRef.collection("userRides").add(ride.toMap());

        // Also add the ride to a common collection for global searching
        db.collection("allRides").add(ride.toMap());
        
    }

    public List<Ride> getRidesByCriteria(String leavingFrom, String goingTo, String vehicleType) {
        List<Ride> rides = new ArrayList<>();
        CollectionReference allRidesCollection = db.collection("allRides");

        try {
            System.out.println("Fetching rides with criteria: leavingFrom=" + leavingFrom + ", goingTo=" + goingTo + ", vehicleType=" + vehicleType);

            Query query = allRidesCollection
                    .whereEqualTo("leavingFrom", leavingFrom)
                    .whereEqualTo("goingTo", goingTo)
                    .whereEqualTo("vehicleType", vehicleType);

            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            // Print out the size of querySnapshot for debugging
            System.out.println("Query snapshot size: " + querySnapshot.get().size());

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Ride ride = document.toObject(Ride.class);
                if (ride != null) {
                    rides.add(ride);
                }
            }

            System.out.println("Fetched rides count: " + rides.size());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log the error or show an error message)
        }

        return rides;
    }
    
    
    public void displayRides(List<Ride> rides) {
        System.out.println("Fetched 2");
        System.out.println("Available rides:");
        
        for (Ride ride : rides) {
            System.out.println(ride.getFullName() + " | " + ride.getVehicleType() + " | " + ride.getDate() + " | "
                    + ride.getTime() );
        }
    }

    public void bookRide(Ride ride) throws InterruptedException, ExecutionException {
        CollectionReference bookedRidesCollection = db.collection("Booked Rides");
        Map<String, Object> rideMap = ride.toMap();
        rideMap.put("username", loggedInUsername); // Add the username to the booked ride
        bookedRidesCollection.document(loggedInUsername + "_" + ride.getDate() + "_" + ride.getTime()).set(rideMap);
    }

    public List<Ride> getBookedRides() {
        List<Ride> bookedRides = new ArrayList<>();
        CollectionReference bookedRidesCollection = db.collection("Booked Rides");

        try {
            ApiFuture<QuerySnapshot> querySnapshot = bookedRidesCollection
                    .whereEqualTo("username", loggedInUsername)
                    .get();

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Ride ride = new Ride();
                ride.setFullName(document.getString("fullName"));
                ride.setMobileNumber(document.getString("mobileNumber"));
                ride.setGender(document.getString("gender"));
                
                Object ageObj = document.get("age");
                if (ageObj instanceof Long) {
                    ride.setAge(Long.toString((Long) ageObj));
                } else if (ageObj instanceof String) {
                    ride.setAge((String) ageObj);
                }
                
                ride.setLeavingFrom(document.getString("leavingFrom"));
                ride.setGoingTo(document.getString("goingTo"));
                ride.setTime(document.getString("time"));
                ride.setDate(document.getString("date"));
                
                Object totalPassengerObj = document.get("totalPassenger");
                if (totalPassengerObj instanceof Long) {
                    ride.setTotalPassenger(Long.toString((Long) totalPassengerObj));
                } else if (totalPassengerObj instanceof String) {
                    ride.setTotalPassenger((String) totalPassengerObj);
                }
                
                Object priceObj = document.get("price");
                if (priceObj instanceof Double) {
                    ride.setPrice(Double.toString((Double) priceObj));
                } else if (priceObj instanceof String) {
                    ride.setPrice((String) priceObj);
                }
                
                ride.setVehicleType(document.getString("vehicleType"));
                
                bookedRides.add(ride);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return bookedRides;
    }

    public void displayBookedRides(List<Ride> rides) {
        System.out.println("Booked rides:");
        for (Ride ride : rides) {
            System.out.println(ride.getFullName() + " | " + ride.getVehicleType() + " | " + ride.getDate() + " | "
                    + ride.getTime());
        }
    }

    public Firestore getFirestore() {
        return db;
    }
    
    public List<QueryDocumentSnapshot> getAllDocuments(String collectionName)
            throws InterruptedException, ExecutionException {
        CollectionReference collectionRef = db.collection(collectionName);
        ApiFuture<QuerySnapshot> query = collectionRef.get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments();
    }
}
