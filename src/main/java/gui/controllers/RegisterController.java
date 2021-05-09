package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import repository.UserRepository;

public class RegisterController {
    @FXML
    private Label fullNameError;
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;
    @FXML
    private Label rePasswordError;
    @FXML
    private Label emailError;
    @FXML
    private Label phoneNumberError;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField rePasswordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField bioTextField;
    @FXML
    private DatePicker birthdayTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button registerButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final UserRepository userRepository = new UserRepository();


    public void registerButtonClicked(ActionEvent actionEvent) {
        String fullName = fullNameTextField.getText();
        if (fullName.isEmpty()) {
            fullNameError.setText("you must enter your fullName");
            return;
        }
        String username = usernameTextField.getText();
        if(username.isEmpty()){
            usernameError.setText("you must enter your username");
            return;
        }
        else{
            if(!isUsernameAvailable(username)) {
                usernameError.setText("username already exists");
                return;
            }
        }
        String password = passwordTextField.getText();
        if(password.isEmpty()){
            passwordError.setText("you must enter your password");
            return;
        }
        String rePassword = rePasswordTextField.getText();
        if(rePassword.isEmpty()){
            rePasswordError.setText("you must re-enter your password");
            return;
        }
        else{
            if(!password.equals(rePassword)){
                rePasswordError.setText("passwords don't match");
                return;
            }
        }
        String email = emailTextField.getText();
        if(username.isEmpty()){
            System.out.println("you must enter your email");
            return;
        }
        else{
            if(!isEmailAvailable(email)){
                emailError.setText("email already exists");
                return;
            }
        }
        String phoneNumber = phoneNumberTextField.getText();
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            phoneNumberError.setText("phone number should be only numbers");
        }
        String bio = bioTextField.getText();
        String birthday = birthdayTextField.toString();
    }


    public void backButtonClicked(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean isUsernameAvailable(String username) {
        User user = userRepository.getByUsername(username);
        return (user == null);
    }

    public boolean isEmailAvailable(String email) {
        User user = userRepository.getByEmail(email);
        return user == null;
    }

    public boolean isPhoneNumberAvailable(String phoneNumber) {
        User user = userRepository.getByPhoneNumber(phoneNumber);
        return user == null;
    }
}