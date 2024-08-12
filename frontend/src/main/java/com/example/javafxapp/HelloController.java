package com.example.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import java.net.URL;

public class HelloController implements Initializable{
    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label loginmessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

            }
    public void close(){
        System.exit(0);
    }



    public void loginAction(ActionEvent event){

        if(username.getText().isBlank() == false && password.getText().isBlank() == false){
            validatelogin();

        }else{
            loginmessage.setText("Please enter username and password");
        }
    }

    public void validatelogin() {
        try {
            // Make GET request to the specified URL
            URL url = new URL("http://localhost:8080/api/v1/accounts/username/" + username.getText());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extract username and password from JSON response manually
            String responseBody = response.toString().trim();
            String[] parts = responseBody.split(",");
            String retrievedUsername = null;
            String retrievedPassword = null;
            for (String part : parts) {
                if (part.contains("\"username\":")) {
                    retrievedUsername = part.substring(part.indexOf(":") + 1).replaceAll("\"", "").trim();
                }
                if (part.contains("\"password\":")) {
                    retrievedPassword = part.substring(part.indexOf(":") + 1).replaceAll("\"", "").trim();
                }
            }

            // Validate login using retrieved username and password
            boolean isValid = retrievedUsername != null && retrievedPassword != null &&
                    retrievedUsername.equals(username.getText()) && retrievedPassword.equals(password.getText());
            if (isValid) {
                System.out.println("Congratulations");
                mainapp();
            } else {
                System.out.println("Invalid Username or Password");
                loginmessage.setText("Invalid Username or Password");
            }

            // Close connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void mainapp(){
    try{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainview.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Main Window");
        stage.resizableProperty().setValue(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();



    }catch(Exception e){
        e.printStackTrace();
        e.getCause();
    }
}
 public void createaccout(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
           Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            stage.setTitle("Register Window");
            stage.resizableProperty().setValue(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
     }
 }

    @FXML
    protected void onHelloButtonClick() {

    }
}