package Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormController {
    @FXML
    public TextField FirstnameTextField;
    public TextField LastnameTextField;
    public TextField UsernameTextFeild;
    public TextField passwordTextField;
    public TextField CPasswordTextField;
    public JFXButton Submitbutton;
    public JFXButton canclebutton;
    @FXML
    private Button closeButton;

    @FXML
    public void CloseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) canclebutton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    public void registerButtonOnAction(ActionEvent event) {
        registerUser();
    }



    private void registerUser() {
        String firstname = FirstnameTextField.getText();
        String Lastname = LastnameTextField.getText();
        String username = UsernameTextFeild.getText();
        String password = passwordTextField.getText();

        // Use prepared statement to avoid SQL injection
        String INSERT_USERS_SQL = "INSERT INTO user_account(firstname, lastname, username, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, Lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();

            // Provide feedback to the user, e.g., show a success message
            Platform.runLater(() -> {
                // Update UI or show an alert for successful registration
            });

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or show an error message to the user
        }
    }
}

