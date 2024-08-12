/*

package com.example.javafxapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdddetailsController implements Initializable {






    @FXML
    private Button checkitem;

    @FXML
    private Button closebutton;

    @FXML
    private Text errorcode1;

    @FXML
    private Text errorcode2;

    @FXML
    private Label itemdescription;

    @FXML
    private TextField itemid;

    @FXML
    private Label itemname;

    @FXML
    private Label itemprice;

    @FXML
    private TextField itemquantity;

    @FXML
    private Button registerbutton;


    @FXML
    private TableView<Orderdetails> orderdetailstable;


    @FXML
    private TableColumn<Orderdetails, String> orderdetailsid;



    @FXML
    private TableColumn<Orderdetails, String> price;

    @FXML
    private TableColumn<Orderdetails, String> productid;

    @FXML
    private TableColumn<Orderdetails, String> description;

    @FXML
    private TableColumn<Orderdetails, String> quantity;


    public void showitem(){
        try {
            // Establish database connection
            Connection connectDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/davidapp", "root", "root");

            String productid = itemid.getText();
            // Execute SQL query to get the sum of order prices
            String query = "SELECT * from product where productid =" + productid;
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // If result is found, display the sum
            if (resultSet.next()) {

                String name =  resultSet.getString("productname");
                String description = resultSet.getString("productdescription");
                String price = resultSet.getString("productprice");
                itemname.setText(name);
                itemdescription.setText(description);
                itemprice.setText(price);
            } else {
                // Handle case where no orders are found
               itemname.setText("NA");
                itemdescription.setText("NA");
                itemprice.setText("NA");
            }

            // Close resources
            resultSet.close();
            statement.close();
            connectDB.close();
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
            // You may want to show an error message to the user here
        }
    }


    public void populateorderdetailstable() {
        ObservableList<Orderdetails> orderdetailsList = FXCollections.observableArrayList();

        try {
            database connectNow = new database();
            Connection connectDB = connectNow.getConnection();

            // Prepare the statement with a placeholder for the parameter
            String query = "SELECT * from orderdetails where orderid = (SELECT MAX(orderid) AS highest FROM orderin)";
            PreparedStatement statement = connectDB.prepareStatement(query);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("orderdetailsid");
                String productid = resultSet.getString("productid");
                String quantity = resultSet.getString("quantity");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");

                Orderdetails orderdetails = new Orderdetails(id, productid, quantity, price, description);
                orderdetailsList.add(orderdetails);
            }

            // Log the contents of supplierList
            System.out.println("Orderdetails List Contents:");
            for (Orderdetails orderdetails: orderdetailsList) {
                System.out.println(orderdetails.toString());
            }

            // Assuming orderdetailstable is your TableView variable
            // Set the items in the TableView
            orderdetailstable.setItems(orderdetailsList);

            // Close resources
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            // Handle SQLException, e.g., show an error message to the user
            e.printStackTrace();
        }
    }


    public void deleteSelectedRow() {
        // Get the selected item from the TableView
        Orderdetails selectedOrder = orderdetailstable.getSelectionModel().getSelectedItem();

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
            alert.setContentText("Are you sure you want to delete this order?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        // Delete the selected row from the database
                        try {
                            database connectNow = new database();
                            Connection connectDB = connectNow.getConnection();
                            String query = "DELETE FROM orderdetails WHERE orderdetailsid = ?";
                            PreparedStatement statement = connectDB.prepareStatement(query);
                            statement.setString(1, selectedOrder.getOrderid());
                            statement.executeUpdate();

                            // Remove the selected item from the TableView
                            orderdetailstable.getItems().remove(selectedOrder);
                            // Optionally, you can refresh the TableView if needed
                            // orderdetailstable.refresh();

                            // Close resources
                            statement.close();
                            connectDB.close();
                            populateorderdetailstable();
                        } catch (SQLException e) {
                            // Handle SQLException
                            e.printStackTrace();
                        }
                    });
        }
    }


    public void addItemtoOrder() {
        try {
            // Establish database connection
            database connectNow = new database();
            Connection connectDB = connectNow.getConnection();

            // Get the highest order ID
            String query = "SELECT orderid FROM orderin WHERE orderid = (SELECT MAX(orderid) AS highest FROM orderin)";
            PreparedStatement statement1 = connectDB.prepareStatement(query);
            ResultSet resultSet = statement1.executeQuery();
            String highestOrderId = null;
            if (resultSet.next()) {
                highestOrderId = resultSet.getString("orderid");
            }
            resultSet.close();
            statement1.close();

            // Get the item ID and quantity from the text fields
            String itemId = itemid.getText();
            String quantity = itemquantity.getText();

            // Insert the item into the order details table
            String insertFieldsS = "INSERT INTO orderdetails(orderid, productid, quantity) VALUES (?, ?, ?)";
            PreparedStatement statement2 = connectDB.prepareStatement(insertFieldsS);
            statement2.setString(1, highestOrderId);
            statement2.setString(2, itemId);
            statement2.setString(3, quantity);
            statement2.executeUpdate();

            statement2.close();
            connectDB.close();

            // Populate the order details table
            populateorderdetailstable();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }



    public void close(){
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderdetailsid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        productid.setCellValueFactory(new PropertyValueFactory<>("productid"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        populateorderdetailstable();
    }

}



 */