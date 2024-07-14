package com.ridesharing.dashboards;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.ridesharing.exception.NoRidesAvailableException;
import com.ridesharing.firebaseconfig.DataService;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Ride {

    private String fullName;
    private String mobileNumber;
    private String age;
    private String gender;
    private String leavingFrom;
    private String goingTo;
    private String totalPassenger;
    private String time;
    private String dateString;
    private String price;
    private String vehicleType;
    private String loggedInUsername;

    private DataService dataService;
    private Stage primaryStage;
    private DriverDashboard driverDashboard;
    private PublishDashboard publishDashboard;
    private PassengerDashboard passengerDashboard;
    private PassengerInfo passengerSearchDashboard;

    public Ride(DataService dataService) {
        this.dataService = dataService;
    }

    // Default constructor required for Firestore deserialization
    public Ride() {
    }

    public Ride(String fullName, String mobileNumber, String age, String gender, String leavingFrom, String goingTo,
            String date, String time, String totalPassenger, String price, String vehicleType) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.gender = gender;
        this.leavingFrom = leavingFrom;
        this.goingTo = goingTo;
        this.dateString = date;
        this.time = time;
        this.totalPassenger = totalPassenger;
        this.price = price;
        this.vehicleType = vehicleType;
    }

    // Getters and setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLeavingFrom() {
        return leavingFrom;
    }

    public void setLeavingFrom(String leavingFrom) {
        this.leavingFrom = leavingFrom;
    }

    public String getGoingTo() {
        return goingTo;
    }

    public void setGoingTo(String goingTo) {
        this.goingTo = goingTo;
    }

    public String getTotalPassenger() {
        return totalPassenger;
    }

    public void setTotalPassenger(String totalPassenger) {
        this.totalPassenger = totalPassenger;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return dateString;
    }

    public void setDate(String date) {
        this.dateString = date;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public DriverDashboard getDriverDashboard() {
        return driverDashboard;
    }

    public void setDriverDashboard(DriverDashboard driverDashboard) {
        this.driverDashboard = driverDashboard;
    }

    public PublishDashboard getPublishDashboard() {
        return publishDashboard;
    }

    public void setPublishDashboard(PublishDashboard publishDashboard) {
        this.publishDashboard = publishDashboard;
    }

    public PassengerDashboard getPassengerDashboard() {
        return passengerDashboard;
    }

    public void setPassengerDashboard(PassengerDashboard passengerDashboard) {
        this.passengerDashboard = passengerDashboard;
    }

    public PassengerInfo getPassengerSearchDashboard() {
        return passengerSearchDashboard;
    }

    public void setPassengerSearchDashboard(PassengerInfo passengerSearchDashboard) {
        this.passengerSearchDashboard = passengerSearchDashboard;
    }

    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

    /**
     * Convert the Ride object to a Map for Firestore serialization.
     * 
     * @return Map representation of the Ride object
     */
    public Map<String, Object> toMap() {
        Map<String, Object> rideMap = new HashMap<>();
        rideMap.put("fullName", fullName);
        rideMap.put("mobileNumber", mobileNumber);
        rideMap.put("age", age);
        rideMap.put("gender", gender);
        rideMap.put("leavingFrom", leavingFrom);
        rideMap.put("goingTo", goingTo);
        rideMap.put("totalPassenger", totalPassenger);
        rideMap.put("time", time);
        rideMap.put("date", dateString);
        rideMap.put("vehicleType", vehicleType);
        rideMap.put("price", price);
        rideMap.put("username", loggedInUsername); // Add loggedInUsername to the map
        return rideMap;
    }

    /**
     * Fetch rides from Firestore based on the vehicle type, leavingFrom, and
     * goingTo.
     * 
     * @param leavingFrom The location from where the ride is leaving
     * @param goingTo     The destination location
     * @param vehicleType The type of vehicle for which rides are fetched
     * @return List of rides matching the criteria
     */
    public List<Ride> fetchRides(String leavingFrom, String goingTo, String vehicleType)
            throws NoRidesAvailableException {
        List<Ride> rides = new ArrayList<>();
        try {
            // Use the existing DataService instance
            DataService obj = new DataService();
            List<QueryDocumentSnapshot> documents = obj.getAllDocuments("allRides");

            System.out.println("Criteria - Leaving From: " + leavingFrom + ", Going To: " + goingTo + ", Vehicle Type: "
                    + vehicleType);

            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> rideData = document.getData();

                // Create a Ride object from the document data
                Ride ride = new Ride();
                ride.setFullName((String) rideData.get("fullName"));
                ride.setMobileNumber((String) rideData.get("mobileNumber"));
                ride.setGender((String) rideData.get("gender"));
                Object ageObj = rideData.get("age");
                if (ageObj instanceof Long) {
                    ride.setAge(Long.toString((Long) ageObj));
                } else if (ageObj instanceof String) {
                    ride.setAge((String) ageObj);
                }

                ride.setLeavingFrom((String) rideData.get("leavingFrom"));
                ride.setGoingTo((String) rideData.get("goingTo"));
                ride.setTime((String) rideData.get("time"));
                ride.setDate((String) rideData.get("date"));
                Object totalpassengerObj = rideData.get("totalPassenger");
                if (totalpassengerObj instanceof Long) {
                    ride.setTotalPassenger(Long.toString((Long) totalpassengerObj));
                } else if (totalpassengerObj instanceof String) {
                    ride.setTotalPassenger((String) totalpassengerObj);
                }
                Object priceObj = rideData.get("price");
                if (priceObj instanceof Double) {
                    ride.setPrice(Double.toString((Double) priceObj));
                } else if (priceObj instanceof String) {
                    ride.setPrice((String) priceObj);
                }
                ride.setVehicleType((String) rideData.get("vehicleType"));

                // Check if the ride matches the criteria
                if (ride.getLeavingFrom().equalsIgnoreCase(leavingFrom) &&
                        ride.getGoingTo().equalsIgnoreCase(goingTo) &&
                        ride.getVehicleType().equalsIgnoreCase(vehicleType)) {
                    rides.add(ride);
                }
            }

            System.out.println("Fetched rides count: " + rides.size());

            // Throw a custom exception if no rides are found
            if (rides.isEmpty()) {
                throw new NoRidesAvailableException("No rides available for the given criteria.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log the error or show an error message)
        }

        return rides;
    }

    /**
     * Fetch booked rides for the logged-in user.
     * 
     * @return List of booked rides for the logged-in user
     */
    public List<Ride> fetchBookedRides() {
        List<Ride> rides = new ArrayList<>();
        DataService obj = new DataService();

        try {
            List<QueryDocumentSnapshot> documents = obj.getAllDocuments("Booked Rides");

            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> rideData = document.getData();

                // Create a Ride object from the document data
                Ride ride = new Ride();
                ride.setFullName((String) rideData.get("fullName"));
                ride.setMobileNumber((String) rideData.get("mobileNumber"));
                ride.setGender((String) rideData.get("gender"));
                Object ageObj = rideData.get("age");
                if (ageObj instanceof Long) {
                    ride.setAge(Long.toString((Long) ageObj));
                } else if (ageObj instanceof String) {
                    ride.setAge((String) ageObj);
                }

                ride.setLeavingFrom((String) rideData.get("leavingFrom"));
                ride.setGoingTo((String) rideData.get("goingTo"));
                ride.setTime((String) rideData.get("time"));
                ride.setDate((String) rideData.get("date"));
                Object totalpassengerObj = rideData.get("totalPassenger");
                if (totalpassengerObj instanceof Long) {
                    ride.setTotalPassenger(Long.toString((Long) totalpassengerObj));
                } else if (totalpassengerObj instanceof String) {
                    ride.setTotalPassenger((String) totalpassengerObj);
                }
                Object priceObj = rideData.get("price");
                if (priceObj instanceof Double) {
                    ride.setPrice(Double.toString((Double) priceObj));
                } else if (priceObj instanceof String) {
                    ride.setPrice((String) priceObj);
                }
                ride.setVehicleType((String) rideData.get("vehicleType"));

                rides.add(ride);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return rides;
    }

    // Other methods of Ride class...

    public void publishRide(Ride ride) throws InterruptedException, ExecutionException {
        DataService dataService = new DataService();
        dataService.publishRide(ride);
    }

    public void bookRide(Ride ride) throws InterruptedException, ExecutionException {
        DataService dataService = new DataService();
        dataService.bookRide(ride);
    }

    public void displayRides(List<Ride> rides) {
        DataService dataService = new DataService();
        dataService.displayRides(rides);
    }

    public void displayBookedRides(List<Ride> rides) {
        DataService dataService = new DataService();
        dataService.displayBookedRides(rides);
    }
}
