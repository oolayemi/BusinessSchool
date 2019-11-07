package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {


    @FXML
    private JFXTextField firstNameTextField;
    @FXML
    private JFXTextField lastNameTextField;
    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXPasswordField passwordTextField;
    @FXML
    private JFXButton signupButton;
    @FXML
    private JFXButton cancelbutton, cancelbutton1;
    @FXML
    private JFXButton btn_signin, btn_signup;
    @FXML
    private AnchorPane pn_signin, pn_signup;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton signinButton;
    @FXML
    private JFXButton labelExit, labelExit1;


    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == labelExit || event.getSource() == cancelbutton)
        {
            System.exit(0);
        }
        if (event.getSource() == labelExit1 || event.getSource() == cancelbutton1){
            System.exit(0);
        }
        if(event.getSource() == btn_signup )
        {
            pn_signup.toFront();
        }
        else
        if(event.getSource() == btn_signin)
        {
            pn_signin.toFront();
        }
    }


    @FXML
    protected void simpleSubmitButtonAction(ActionEvent event) throws Exception, Throwable{
        Window owner = signinButton.getScene().getWindow();

        try {
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Yemi/Documents/businessschool.accdb");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * from business";
            ResultSet rset = stmt.executeQuery(sql);


            while(rset.next()){
                String name = rset.getString("Firstname");
                String email = rset.getString("Email");
                String password2 = rset.getString("password");

                if (username.getText().equals(email) && password.getText().equals(password2)){
                    System.out.println("Login successful");
                    owner.hide();

                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
                    Scene scene = new Scene(root);
                    scene.setFill(Color.BLUEVIOLET);
                    stage.setScene(scene);
                    stage.show();

                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner,  "Confirmed", "Welcome " + name);
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(username.getText().isEmpty() || password.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please fill your correct username and password!");
        }
    }

    @FXML
    protected void signupButtonAction (ActionEvent event) throws Exception{
        Window owner = signupButton.getScene().getWindow();

        if(firstNameTextField.getLength() <1 || lastNameTextField.getLength() <1 || emailTextField.getLength()<1 || passwordTextField.getLength() < 1) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please make sure all forms are filled correctly");
        }else{
            try {
                Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Yemi/Documents/businessschool.accdb");
                PreparedStatement st = conn.prepareStatement("INSERT INTO " + "business(Firstname, Lastname, Email, Password)" +  "VALUES(?, ?, ?, ?)");

                String firstn = firstNameTextField.getText();
                st.setString(1, firstn);
                String lastn = lastNameTextField.getText();
                st.setString(2, lastn);
                String emailn = emailTextField.getText();
                st.setString(3, emailn);
                String passn = passwordTextField.getText();
                st.setString(4, passn);

                st.executeUpdate();

            }catch (SQLException s){
                s.getErrorCode();
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection();
        /*Notifications notificationBuilder = Notifications.create()
                .title("Confirmed")
                .text("Login Successful")
                .graphic(null)
                .hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Hello, Welcome");
                    }
                });
        notificationBuilder.showConfirm();*/
    }

    static void DBConnection(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Yemi/Documents/businessschool.accdb");
            System.out.println("Connection successful");

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
