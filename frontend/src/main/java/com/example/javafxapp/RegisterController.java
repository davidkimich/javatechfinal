package com.example.javafxapp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class RegisterController {

    @FXML
    private Button closebutton;

    @FXML
    private PasswordField comfirmpassword;

    @FXML
    private Text errorcode1;

    @FXML
    private Text errorcode2;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField subscriptionkey;

    @FXML
    private Button registerbutton;

    @FXML
    private TextField username;

    public void close() {
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }

    public void registerbuttonaction() {
        if (true) {
            if (password.getText().equals(comfirmpassword.getText()) && !password.getText().isEmpty()) {
                errorcode1.setText("");
                registeruser();
            } else {
                errorcode2.setText("");
                errorcode1.setText("Passwords Don't Match");
            }
        } else {
            errorcode1.setText("Enter A valid Subscription Key");
        }
    }

    public void registeruser() {
        try {
            // Create user JSON payload
            String jsonPayload = "{"
                    + "\"firstName\": \"" + firstname.getText() + "\","
                    + "\"lastName\": \"" + lastname.getText() + "\","
                    + "\"username\": \"" + username.getText() + "\","
                    + "\"password\": \"" + password.getText() + "\""
                    + "}";

            // Set up HTTP connection
            URL url = new URL("http://localhost:8080/api/v1/accounts");
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
                errorcode2.setText("User has Been Registered Successfully");
            } else {
                errorcode2.setText("");
                errorcode1.setText("Failed to register user. Response code: " + responseCode);
            }

            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            errorcode2.setText("");
            errorcode1.setText("Something Went Wrong");
            e.printStackTrace();
        }
    }
}
