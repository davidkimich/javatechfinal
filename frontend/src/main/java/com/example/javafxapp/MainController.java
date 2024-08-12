package com.example.javafxapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import javafx.scene.control.Alert;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainController implements Initializable {


    @FXML
    private Label todaysales;

    @FXML
    private Label todayin;

    @FXML
    private AnchorPane pane1;

    @FXML
    private Button pane1btn;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;


    @FXML
    private TextField pane4itemdescription;

    @FXML
    private TextField username;

    @FXML
    private TextField pane4itemid;

    @FXML
    private TextField pane4itemname;

    @FXML
    private TextField pane4itemprice;

    @FXML
    private TextField pane44itemname;

    @FXML
    private TextField pane44itemprice;
    @FXML
    private TextField pane44itemdescription;


    @FXML
    private Label pane4errorcode1;

    @FXML
    private Label pane4errorcode2;

    @FXML
    private Label pane4errorcode3;

    @FXML
    private Label pane4errorcode4;


    @FXML
    private AnchorPane pane5;

    @FXML
    private AnchorPane pane51;

    @FXML
    private TextArea pane5inputdescription;

    @FXML
    private TextField pane5inputname;

    @FXML
    private TextField pane5itemid;

    @FXML
    private TextField pane5quantity;

    @FXML
    private TextField pane5suppliername;


    @FXML
    private AnchorPane pane52;

    @FXML
    private AnchorPane pane521;

    @FXML
    private AnchorPane pane522;


    @FXML
    private AnchorPane pane6;

    @FXML
    private AnchorPane pane7;

    @FXML
    private AnchorPane pane8;

    @FXML
    private AnchorPane pane9;

    @FXML
    private Label pane9errorcode1;

    @FXML
    private Label pane9errorcode2;

    @FXML
    private Label pane9errorcode3;

    @FXML
    private Label pane9errorcode4;

    @FXML
    private TextField pane9supplierdescription;

    @FXML
    private TextField pane9supplierid;

    @FXML
    private TextField pane9suppliername;


    @FXML
    private Button pane2btn;


    @FXML
    private Button closebutton;


    @FXML
    private TableColumn<Supplier, String> supplierdescriptiontable;

    @FXML
    private TableColumn<Supplier, String> supplieridtable;

    @FXML
    private TableColumn<Supplier, String> suppliernametable;

    @FXML
    private TableView<Supplier> suppliertable;

    @FXML
    private TableColumn<Product, String> productdescriptiontable;

    @FXML
    private TableColumn<Product, String> productidtable;

    @FXML
    private TableColumn<Product, String> productnametable;
    @FXML
    private TableColumn<Product, String> productpricetable;
    @FXML
    private TableView<Product> producttable;


    @FXML
    private TableColumn<Inventory, String> inventoryid;

    @FXML
    private TableColumn<Inventory, String> inventorydescription;

    @FXML
    private TableColumn<Inventory, String> inventoryquantity;
    @FXML
    private TableColumn<Inventory, String> inventoryproductid;


    @FXML
    private TableView<Inventory> inventorytables;

    @FXML
    private Label inventoryin;

    @FXML
    private Label inventoryout;

    @FXML
    private TableColumn<Orderin, String> o8;

    @FXML
    private BarChart<String, Number> barChart;


    @FXML
    private Label currentinventoryvalue;


    ObservableList<Supplier> initialdata() {
        Supplier s1 = new Supplier("4", "David", "Electric");
        Supplier s2 = new Supplier("4", "David", "Electric");
        return FXCollections.observableArrayList(s1, s2);
    }

    public void initialise() {

    }

    public void openmain() {
        pane1.setVisible(true);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);

        displayOrderInSum();
        displayOrderOutSum();
        displayTodaySalesSum();
        currentinventoryvalue();
        displayTodayinSum();


    }

    public void populateBarChart() {
        // Clear existing data
        barChart.getData().clear();

        // Create categories and corresponding data points
        ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();

        // Make HTTP request for inventory in
        double totalIn = getTotalFromApi("orderin");
        XYChart.Series<String, Number> seriesIn = new XYChart.Series<>();
        seriesIn.setName("Inventory In");
        seriesIn.getData().add(new XYChart.Data<>("", totalIn));

        // Make HTTP request for inventory out
        double totalOut = getTotalFromApi("orders");
        XYChart.Series<String, Number> seriesOut = new XYChart.Series<>();
        seriesOut.setName("Inventory Out");
        seriesOut.getData().add(new XYChart.Data<>("", totalOut));

        // Add series to the bar chart
        barChartData.add(seriesIn);
        barChartData.add(seriesOut);
        barChart.setData(barChartData);
    }

    private double getTotalFromApi(String tableName) {
        try {
            URL url = new URL("http://localhost:8080/api/v1/" + tableName + "/sum");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String responseBody = in.readLine();
                    return Double.parseDouble(responseBody);
                }
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
                return 0.0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0; // Handle exceptions gracefully in your application
        }
    }


    public void openpain2() {
        pane1.setVisible(false);
        pane2.setVisible(true);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);

        populateBarChart();
    }

    public void openpain3() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(true);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);
    }

    public void openpain4() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(true);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);
    }

    public void openpain5() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(true);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);
    }

    public void openpain6() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(true);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);
    }

    public void openpain7() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(true);
        pane8.setVisible(false);
        pane9.setVisible(false);
    }

    public void openpain8() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(true);
        pane9.setVisible(false);
    }

    public void openpain9() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(true);
    }

    public void openpain51() {
        pane52.setVisible(false);
        pane51.setVisible(true);

    }

    public void openpain52() {
        pane52.setVisible(true);
        pane51.setVisible(false);

    }

    public void openpain521() {
        pane522.setVisible(false);
        pane521.setVisible(true);

    }

    public void openpain522() {
        pane522.setVisible(true);
        pane521.setVisible(false);

    }

    public void close() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();

    }

    public void adddetailsview() {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adddetails.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("Register Window");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

    public void adddetailsviewtwo() {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adddetailsout.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("Register Window");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

    /////////////////////////////pane4//////////////////////////////
    public void deleteSelectedItem() {

        // Get the selected item from the TableView
        Product selectedProduct = producttable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            // If no row is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Row Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        } else {
            // Confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this Item?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        try {
                            String productId = selectedProduct.getId();

                            // Construct the URL for deleting the product
                            URL url = new URL("http://localhost:8080/api/v1/products/" + productId);

                            // Open connection and send DELETE request
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("DELETE");
                            connection.setRequestProperty("Content-Type", "application/json");

                            int responseCode = connection.getResponseCode();

                            populateItemTable();

                            pane4errorcode4.setText("DELETED");


                            // Close connection
                            connection.disconnect();
                        } catch (IOException e) {
                            // Handle exceptions
                            e.printStackTrace();
                        }
                    });
        }


    }

    public void addItem() {

        try {


            String jsonPayload = "{"
                    + "\"productName\": \"" + pane4itemname.getText() + "\","
                    + "\"productDescription\": \"" + pane4itemdescription.getText() + "\","
                    + "\"productPrice\": \"" + pane4itemprice.getText() + "\""
                    + "}";

            URL url = new URL("http://localhost:8080/api/v1/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                wr.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                populateItemTable();
                pane4errorcode1.setText("User has Been Registered Successfully");
                // errorcode2.setText("User has Been Registered Successfully");
            } else {
                pane4errorcode2.setText("Error");
                //  errorcode2.setText("");
                // errorcode1.setText("Failed to register user. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();

        } catch (Exception e) {
            // errorcode2.setText("");
            // errorcode1.setText("Something Went Wrong");
            e.printStackTrace();
        }
    }


    public void updateSelectedItem() {
        // Get the selected item from the TableView
        Product selectedProduct = producttable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            // If no row is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Row Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to update.");
            alert.showAndWait();
        } else {
            pane44itemname.setVisible(true);
            pane44itemdescription.setVisible(true);
            pane44itemprice.setVisible(true);
            // Display attributes of the selected item in text fields
            pane44itemname.setText(selectedProduct.getName());
            pane44itemdescription.setText(selectedProduct.getDescription());
            pane44itemprice.setText(selectedProduct.getPrice());

            // Show an update dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Item");
            dialog.setHeaderText(null);

            // Add text fields to the dialog
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 20, 10, 10));

            grid.add(new Label("Name:"), 0, 0);
            grid.add(pane44itemname, 1, 0);
            grid.add(new Label("Description:"), 0, 1);
            grid.add(pane44itemdescription, 1, 1);
            grid.add(new Label("Price:"), 0, 2);
            grid.add(pane44itemprice, 1, 2);

            dialog.getDialogPane().setContent(grid);

            // Add buttons to the dialog
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the dialog and wait for user input
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Update the selected item with new attributes
                selectedProduct.setName(pane44itemname.getText());
                selectedProduct.setDescription(pane44itemdescription.getText());
                selectedProduct.setPrice(pane44itemprice.getText());

                // Send a request to update the item on the server
                try {
                    URL url = new URL("http://localhost:8080/api/v1/products/" + selectedProduct.getId());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("PUT");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);

                    String jsonPayload = "{"
                            + "\"productName\": \"" + selectedProduct.getName() + "\","
                            + "\"productDescription\": \"" + selectedProduct.getDescription() + "\","
                            + "\"productImage\": null,"
                            + "\"productPrice\": \"" + selectedProduct.getPrice() + "\""
                            + "}";

                    try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                        byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                        wr.write(input, 0, input.length);
                    }

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Item updated successfully
                        populateItemTable();
                        pane4errorcode4.setText("UPDATED");
                    } else {
                        // Handle error
                        pane4errorcode4.setText("Error");
                    }

                    connection.disconnect();
                } catch (IOException e) {
                    // Handle exceptions
                    e.printStackTrace();
                }
            }
        }
    }
    ////////////////////////////////////////////////////////pane9///////////////////////////////////


    public void deletesupplier() {

        // Get the selected item from the TableView
        Supplier selectedSupplier = suppliertable.getSelectionModel().getSelectedItem();

        if (selectedSupplier == null) {
            // If no row is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Row Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        } else {
            // Confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this Item?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        try {
                            String productId = selectedSupplier.getId();

                            // Construct the URL for deleting the product
                            URL url = new URL("http://localhost:8080/api/v1/suppliers/" + productId);

                            // Open connection and send DELETE request
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("DELETE");
                            connection.setRequestProperty("Content-Type", "application/json");

                            int responseCode = connection.getResponseCode();

                            populateSupplierTable();

                            pane9errorcode2.setText("DELETED");


                            // Close connection
                            connection.disconnect();
                        } catch (IOException e) {
                            // Handle exceptions
                            e.printStackTrace();
                        }
                    });
        }


    }


    public void addSupplier() {
        try {


            String jsonPayload = "{"
                    + "\"supplierName\": \"" + pane9suppliername.getText() + "\","
                    + "\"supplierDescription\": \"" + pane9supplierdescription.getText()
                    + "\"}";


            URL url = new URL("http://localhost:8080/api/v1/suppliers");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                wr.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                populateSupplierTable();
                pane9errorcode1.setText("User has Been Registered Successfully");
                // errorcode2.setText("User has Been Registered Successfully");
            } else {
                pane9errorcode2.setText("Error");
                //  errorcode2.setText("");
                // errorcode1.setText("Failed to register user. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();

        } catch (Exception e) {
            // errorcode2.setText("");
            // errorcode1.setText("Something Went Wrong");
            e.printStackTrace();
        }
    }

    @FXML
    private Label errordetector;

    public  int getMaxOrderDetailsId() {
        try {
            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/orderdetails/max");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse response body to integer
                return Integer.parseInt(response.toString());
            } else {
                // Handle HTTP error response
                System.err.println("Failed to get max order details ID. Response code: " + responseCode);
                return -1; // or throw an exception
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return -1; // or throw an exception
        }
    }

    public void addOrderin() {
        try {
            // Construct JSON payload for the order
            String jsonPayload = "{"
                    + "\"accountId\": 5,"
                    + "\"orderName\": \"" + pane5inputname.getText() + "\","
                    + "\"orderPrice\": 5,"
                    + "\"orderDescription\": \"" + pane5inputdescription.getText() + "\","
                    + "\"orderDetailsId\": 5,"
                    + "\"supplierId\": \"" + pane5suppliername.getText() + "\","
                    + "\"orderDate\": 2024-05-04"
                    + "}";


            // Create URL object for the API endpoint
            URL url = new URL("http://localhost:8080/api/v1/orderin");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send JSON payload in the request body
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                wr.write(input, 0, input.length);
            }

            // Get response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                // If order added successfully, update UI or perform any necessary actions
                // For example:
                //  populateOrderInTable();
                // Set success message
                // pane4errorcode1.setText("Order added successfully");
            } else {
                // If order addition failed, handle the error
                //  pane4errorcode2.setText("Error adding order");
            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // Set error message
            //   pane4errorcode2.setText("Something went wrong");
        }
    }

    public void addOrderssIn(String orderinname, String supplierid, String orderindescription) {
        try {
            int maxOrderDetailsId = getMaxOrderDetailsId();
            // Create user JSON payload
            String orderdate = "2024-05-04 08:30:59";
            String jsonPayload = "{"
                    + "\"accountId\": 5,"
                    + "\"orderName\": \"" + orderinname + "\","
                    + "\"orderPrice\": 0,"
                    + "\"orderDescription\": \"" + orderindescription + "\","
                    + "\"orderDetailsId\": " + maxOrderDetailsId + ","
                    + "\"supplierId\": " + supplierid + ","
                    + "\"orderDate\": \"" + orderdate + "\""
                    + "}";

            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/orderin");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload to request body
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                wr.write(input, 0, input.length);
            }

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {

            } else {

            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void addOrderInfunc() {


        String Orderinname = pane5inputname.getText();
        String Orderdescription = pane5inputdescription.getText();
        String Supplierid = pane5suppliername.getText();

        addOrderssIn(Orderinname,Supplierid,Orderdescription);

    }

    @FXML
    private Label errordetector1;

    @FXML
    private TextField pane5itemname1;

    @FXML
    private TextArea pane5itemdescription1;

    @FXML
    private TextField pane5itemrecipient1;


    public void addOrderss(String Ordername,String recipient,String Orderdescription) {
        try {
            int maxOrderDetailsId = getMaxOrderDetailsId();
            // Create user JSON payload
            String orderdate = "2024-05-04 08:30:59";
            String jsonPayload = "{"
                    + "\"accountId\": 5,"
                    + "\"orderName\": \"" + Ordername + "\","
                    + "\"orderPrice\": 0,"
                    + "\"orderRecipient\": \"" + recipient + "\","
                    + "\"orderDescription\": " + Orderdescription + ","
                    + "\"orderDetailsId\": " + maxOrderDetailsId + ","
                    + "\"orderDate\": \"" + orderdate + "\""
                    + "}";

            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/orders");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload to request body
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                wr.write(input, 0, input.length);
            }

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {

            } else {

            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void addOrderOutfunc() {


        String Ordername = pane5itemname1.getText();
        String Orderdescription = pane5itemdescription1.getText();
        String recipient = pane5itemrecipient1.getText();

        addOrderss(Ordername,recipient,Orderdescription);
        //adddetailsview();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        supplieridtable.setCellValueFactory(new PropertyValueFactory<>("id"));
        suppliernametable.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierdescriptiontable.setCellValueFactory(new PropertyValueFactory<>("description"));
        populateSupplierTable();

        productidtable.setCellValueFactory(new PropertyValueFactory<>("id"));
        productnametable.setCellValueFactory(new PropertyValueFactory<>("name"));
        productdescriptiontable.setCellValueFactory(new PropertyValueFactory<>("description"));
        productpricetable.setCellValueFactory(new PropertyValueFactory<>("price"));
        populateItemTable();

        inventoryid.setCellValueFactory(new PropertyValueFactory<>("id"));
        inventorydescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        inventoryquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        inventoryproductid.setCellValueFactory(new PropertyValueFactory<>("productid"));


        populateInvetoryTable();
        o1.setCellValueFactory(new PropertyValueFactory<>("id"));
        o2.setCellValueFactory(new PropertyValueFactory<>("accountid"));
        o3.setCellValueFactory(new PropertyValueFactory<>("name"));
        o4.setCellValueFactory(new PropertyValueFactory<>("price"));
        o5.setCellValueFactory(new PropertyValueFactory<>("description"));
        o6.setCellValueFactory(new PropertyValueFactory<>("orderdetailsid"));
        o7.setCellValueFactory(new PropertyValueFactory<>("supplierid"));
        o8.setCellValueFactory(new PropertyValueFactory<>("orderdate"));

        populateOrderInTable();


        os1.setCellValueFactory(new PropertyValueFactory<>("id"));
        os2.setCellValueFactory(new PropertyValueFactory<>("accountid"));
        os3.setCellValueFactory(new PropertyValueFactory<>("name"));
        os4.setCellValueFactory(new PropertyValueFactory<>("price"));
        os5.setCellValueFactory(new PropertyValueFactory<>("description"));
        os6.setCellValueFactory(new PropertyValueFactory<>("orderdetailsid"));
        os7.setCellValueFactory(new PropertyValueFactory<>("orderrecipient"));
        os8.setCellValueFactory(new PropertyValueFactory<>("orderdate"));

        populateOrderOutTable();


        displayOrderInSum();
        displayOrderOutSum();
        displayTodaySalesSum();
        currentinventoryvalue();
        displayTodayinSum();


    }


    public void populateSupplierTable() {
        try {
            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/suppliers"); // Replace the URL with your API endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON manually
                    List<Supplier> supplierList = new ArrayList<>();
                    String responseBody = response.toString();
                    int start = responseBody.indexOf("[");
                    int end = responseBody.lastIndexOf("]");
                    if (start != -1 && end != -1) {
                        String suppliersJson = responseBody.substring(start, end + 1);
                        String[] suppliers = suppliersJson.split("\\},\\{");
                        for (String supplier : suppliers) {
                            String[] fields = supplier.split(",");
                            String id = fields[0].split(":")[1].replaceAll("\"", "").trim();
                            String name = fields[1].split(":")[1].replaceAll("\"", "").trim();
                            String description = fields[2].split(":")[1].replaceAll("\"", "").trim();
                            supplierList.add(new Supplier(id, name, description));
                        }
                    }

                    // Log the contents of supplierList
                    System.out.println("Supplier List Contents:");
                    for (Supplier supplier : supplierList) {
                        System.out.println(supplier.toString());
                    }

                    // Populate table
                    ObservableList<Supplier> observableSupplierList = FXCollections.observableArrayList(supplierList);
                    suppliertable.setItems(observableSupplierList);
                }
            } else {
                // Handle error response
                System.out.println("Failed to retrieve suppliers. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    double insum = 0;
    double outsum = 0;

    public void displayOrderInSum() {
        try {
            // Create the URL object
            URL url = new URL("http://localhost:8080/api/v1/orderin/sum");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // If response code is OK, read the response body
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response body
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseBody = in.readLine();

                // Parse the response body to double
                double sum = Double.parseDouble(responseBody);

                // Set the sum to inventoryin
                inventoryin.setText(String.valueOf(sum));
                insum = sum;
                // Close the BufferedReader
                in.close();
            } else {
                // Handle other response codes
                inventoryin.setText("No orders found or error occurred");
            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // You may want to show an error message to the user here
        }

    }


    public void displayTodaySalesSum() {
        try {
            // Get today's date
            LocalDate today = LocalDate.now();

            // Construct the URL for getting today's order out sum
            URL url = new URL("http://localhost:8080/api/v1/orders/sum?date=" + today);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // If response code is OK, read the response body
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response body
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseBody = in.readLine();

                // Parse the response body to double
                double sum = Double.parseDouble(responseBody);

                // Set the sum to inventoryout
                inventoryout.setText(String.valueOf(sum));

                // Close the BufferedReader
                in.close();
            } else {
                // Handle other response codes
                inventoryout.setText("No orders found or error occurred");
            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // You may want to show an error message to the user here
        }
    }

    public void displayTodayinSum() {
        try {
            // Get today's date
            LocalDate today = LocalDate.now();

            // Construct the URL for getting today's order in sum
            URL url = new URL("http://localhost:8080/api/v1/orderin/sum?date=" + today);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // If response code is OK, read the response body
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response body
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseBody = in.readLine();

                // Parse the response body to double
                double sum = Double.parseDouble(responseBody);

                // Set the sum to inventoryin
                inventoryin.setText(String.valueOf(sum));

                // Close the BufferedReader
                in.close();
            } else {
                // Handle other response codes
                inventoryin.setText("No orders found or error occurred");
            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // You may want to show an error message to the user here
        }
    }


    public void displayOrderOutSum() {
        try {
            // Create the URL object
            URL url = new URL("http://localhost:8080/api/v1/orders/sum");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // If response code is OK, read the response body
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response body
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String responseBody = in.readLine();

                // Parse the response body to double
                double sum = Double.parseDouble(responseBody);

                // Set the sum to inventoryout
                inventoryout.setText(String.valueOf(sum));
                outsum = sum;
                // Close the BufferedReader
                in.close();
            } else {
                // Handle other response codes
                inventoryout.setText("No orders found or error occurred");
            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // You may want to show an error message to the user here
        }
    }

    public void currentinventoryvalue() {
        double totsum = insum - outsum;
        currentinventoryvalue.setText(String.valueOf(totsum));

    }

    public void populateItemTable() {

        try {
            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/products"); // Replace the URL with your API endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON manually
                    List<Product> productList = new ArrayList<>();
                    String responseBody = response.toString();
                    int start = responseBody.indexOf("[");
                    int end = responseBody.lastIndexOf("]");
                    if (start != -1 && end != -1) {
                        String productsJson = responseBody.substring(start, end + 1);
                        String[] products = productsJson.split("\\},\\{");
                        for (String product : products) {
                            String[] fields = product.split(",");
                            String id = fields[0].split(":")[1].replaceAll("\"", "").trim();
                            String name = fields[1].split(":")[1].replaceAll("\"", "").trim();
                            String description = fields[3].split(":")[1].replaceAll("\"", "").trim();
                            String price = fields[4].split(":")[1].replaceAll("\"", "").trim();
                            productList.add(new Product(id, name, description, price));
                        }
                    }

                    // Log the contents of productList
                    System.out.println("Product List Contents:");
                    for (Product product : productList) {
                        System.out.println(product.toString());
                    }

                    // Populate table
                    ObservableList<Product> observableProductList = FXCollections.observableArrayList(productList);
                    producttable.setItems(observableProductList);
                }
            } else {
                // Handle error response
                System.out.println("Failed to retrieve products. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////pane5
/*
    public void addOrderin(){
        database connectNow = new database();
        Connection connectDB = connectNow.getConnection();

        String accountidd = "1";
        String ordername = pane5inputname.getText();
        String orderprice = "0";
        String supplierid = pane5suppliername.getText();
        String orderdescription = pane5inputdescription.getText();
        String orderdetailsid = "1";




        String insertFieldsS = "INSERT INTO orderin(productname , productdescription, productprice) VALUES";
        String insertValuesS = "('" + itemname + "','" + itemdescription + "','" + itemprice  + "')";
        String insertToRegisterS = insertFieldsS + insertValuesS;


        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegisterS);
            pane4errorcode1.setText("Item Added Successfully");
            pane4errorcode2.setText("");
        }catch (Exception e){
            pane4errorcode1.setText("");
            pane4errorcode2.setText("Something Went Wrong");
            e.printStackTrace();
            e.getCause();
        }

    }

*/


/////////////////////////////////////////////////////pane 7


    public void populateInvetoryTable() {
        try {
            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/inventory"); // Replace the URL with your API endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON manually
                    List<Inventory> inventoryList = new ArrayList<>();
                    String responseBody = response.toString();
                    int start = responseBody.indexOf("[");
                    int end = responseBody.lastIndexOf("]");
                    if (start != -1 && end != -1) {
                        String inventoryJson = responseBody.substring(start, end + 1);
                        String[] inventories = inventoryJson.split("\\},\\{");
                        for (String inventory : inventories) {
                            String[] fields = inventory.split(",");
                            String id = fields[0].split(":")[1].replaceAll("\"", "").trim();
                            String description = fields[1].split(":")[1].replaceAll("\"", "").trim();
                            String quantity = fields[2].split(":")[1].replaceAll("\"", "").trim();
                            String productId = fields[3].split(":")[1].replaceAll("\"", "").trim();
                            inventoryList.add(new Inventory(id, description, quantity, productId));
                        }
                    }

                    // Log the contents of inventoryList
                    System.out.println("Inventory List Contents:");
                    for (Inventory inventory : inventoryList) {
                        System.out.println(inventory.toString());
                    }

                    // Populate table
                    ObservableList<Inventory> observableInventoryList = FXCollections.observableArrayList(inventoryList);

                    inventorytables.setItems(observableInventoryList);
                }
            } else {
                // Handle error response
                System.out.println("Failed to retrieve inventory. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    /////////////////////pane orderinorderout


    @FXML
    private TableColumn<Orderin, String> o1;

    @FXML
    private TableColumn<Orderin, String> o2;

    @FXML
    private TableColumn<Orderin, String> o3;
    @FXML
    private TableColumn<Orderin, String> o4;

    @FXML
    private TableColumn<Orderin, String> o5;

    @FXML
    private TableColumn<Orderin, String> o6;

    @FXML
    private TableColumn<Orderin, String> o7;

    @FXML
    private TableView<Orderin> orderintable;

    public void populateOrderInTable() {

        try {
            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/orderin");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON manually
                    List<Orderin> orderList = new ArrayList<>();
                    String responseBody = response.toString();
                    int start = responseBody.indexOf("[");
                    int end = responseBody.lastIndexOf("]");
                    if (start != -1 && end != -1) {
                        String orderJson = responseBody.substring(start, end + 1);
                        String[] orders = orderJson.split("\\},\\{");
                        for (String order : orders) {
                            String[] fields = order.split(",");
                            String id = getValue(fields[0]);
                            String accountid = getValue(fields[1]);
                            String name = getValue(fields[2]);
                            String price = getValue(fields[3]);
                            String description = getValue(fields[4]);
                            String orderdetailsid = getValue(fields[5]);
                            String supplierid = getValue(fields[6]);
                            String orderdate = getValue(fields[7]);

                            orderList.add(new Orderin(id, accountid, name, price, description, orderdetailsid, supplierid, orderdate));
                        }
                    }

                    // Log the contents of orderList
                    System.out.println("Order List Contents:");
                    for (Orderin orderin : orderList) {
                        System.out.println(orderin.toString());
                    }

                    // Populate table
                    ObservableList<Orderin> observableOrderList = FXCollections.observableArrayList(orderList);
                    orderintable.setItems(observableOrderList);
                }
            } else {
                // Handle error response
                System.out.println("Failed to retrieve orders. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }


        private String getValue(String field) {
            return field.split(":")[1].replaceAll("\"", "").trim();
        }







    public void deleteSelectedOrder() {
        Orderin selectedOrder = orderintable.getSelectionModel().getSelectedItem();

        if (selectedOrder == null) {
            // If no row is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Row Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        } else {
            // Confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this Order?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        try {
                            // Set up HTTP connection
                            URL url = new URL("http://localhost:8080/api/v1/orderin/" + selectedOrder.getId());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("DELETE");
                            connection.setRequestProperty("Content-Type", "application/json");

                            // Get response from server
                            int responseCode = connection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {

                                orderintable.getItems().remove(selectedOrder);

                            } else {
                                orderintable.getItems().remove(selectedOrder);
                            }

                            // Close connection
                            connection.disconnect();
                        } catch (IOException e) {
                            // Handle IOException
                            // Show error message or log the exception
                            e.printStackTrace();
                        }
                    });
        }
    }



    /////////////////////////////////////////////////////////////////////





    @FXML
    private TableColumn<Orders, String> os1;

    @FXML
    private TableColumn<Orders, String> os2;

    @FXML
    private TableColumn<Orders, String> os3;
    @FXML
    private TableColumn<Orders, String> os4;

    @FXML
    private TableColumn<Orders, String> os5;

    @FXML
    private TableColumn<Orders, String> os6;

    @FXML
    private TableColumn<Orders, String> os7;

    @FXML
    private TableColumn<Orders, String> os8;


    @FXML
    private TableView<Orders> orderstable;

    public void populateOrderOutTable() {

        try {
            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/orders");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Get response from server
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response body
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    // Parse JSON manually
                    List<Orders> orderList = new ArrayList<>();
                    String responseBody = response.toString();
                    int start = responseBody.indexOf("[");
                    int end = responseBody.lastIndexOf("]");
                    if (start != -1 && end != -1) {
                        String orderJson = responseBody.substring(start, end + 1);
                        String[] orders = orderJson.split("\\},\\{");
                        for (String order : orders) {
                            String[] fields = order.split(",");
                            String id = getValue(fields[0]);
                            String accountid = getValue(fields[1]);
                            String name = getValue(fields[2]);
                            String price = getValue(fields[3]);
                            String recipiant = getValue(fields[4]);
                            String orderdescription = getValue(fields[5]);
                            String orderdetailsid = getValue(fields[6]);

                            String orderdate = getValue(fields[7]);

                            orderList.add(new Orders(id, accountid, name, price, recipiant,orderdescription, orderdetailsid, orderdate));
                        }
                    }

                    // Log the contents of orderList
                    System.out.println("Order List Contents:");
                    for (Orders orders : orderList) {
                        System.out.println(orders.toString());
                    }

                    // Populate table
                    ObservableList<Orders> observableOrderList = FXCollections.observableArrayList(orderList);
                    orderstable.setItems(observableOrderList);
                }
            } else {
                // Handle error response
                System.out.println("Failed to retrieve orders. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }










    public void deleteSelectedOrders() {
        Orders selectedOrder = orderstable.getSelectionModel().getSelectedItem();

        if (selectedOrder == null) {
            // If no row is selected, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Row Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
        } else {
            // Confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this Order?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        try {
                            // Set up HTTP connection
                            URL url = new URL("http://localhost:8080/api/v1/orders/" + selectedOrder.getId());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("DELETE");
                            connection.setRequestProperty("Content-Type", "application/json");

                            // Get response from server
                            int responseCode = connection.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {

                                orderstable.getItems().remove(selectedOrder);

                            } else {
                                orderstable.getItems().remove(selectedOrder);
                            }

                            // Close connection
                            connection.disconnect();
                        } catch (IOException e) {
                            // Handle IOException
                            // Show error message or log the exception
                            e.printStackTrace();
                        }
                    });
        }
    }



}