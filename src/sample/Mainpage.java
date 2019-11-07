package sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Mainpage implements Initializable {

    @FXML
    private JFXTextField Firstname;

    @FXML
    private JFXTextField Lastname;

    @FXML
    private JFXTextField Email;

    @FXML
    private JFXTextField phonenumber;

    @FXML
    private JFXDatePicker dob;

    public void Save(ActionEvent event){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
