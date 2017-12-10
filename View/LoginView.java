package View;

import Model.MySQLDatabase;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.util.Observable;

/**
 * Blake Costa, Gavin Drabik, Matthew Turczmanovicz, Oswaldo Rosete-Garcia, and Quinn Bissen
 * Group 11
 * ISTE-330
 * Professor Floeser
 * December 18th, 2017
 */

/**
 * LoginView class serves as the default template that holds all the functionality of the jpanels
 * individual components
 */

public class LoginView extends Observable {
    //both of these are in ALL the view classes
    private MySQLDatabase msdb = MySQLDatabase.getInstance(); //there is only one instance of the database.
    private MasterView mv;//this is passed in through the constructor

    private TextField userNameField;
    private PasswordField passwordField;
    private GridPane gp;
    private Button loginButton;

    public LoginView(MasterView _mv) {
        this.mv = _mv;
    }

    public void makeLoginView() {
        //make gridpane
        gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(5);
        gp.setAlignment(Pos.CENTER);
        //make user name text field
        userNameField = new TextField();
        userNameField.setPromptText("User Name");
        //make password text field
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        //Add User Name label / Textfield to grid pane
        gp.add(new Label("User Name:"), 0, 0);
        gp.add(userNameField, 1, 0);
        //Add Password label / Textfield to grid pane
        gp.add(new Label("Password:"), 0, 1);
        gp.add(passwordField, 1, 1);
        //add button to grid pane
        loginButton = new Button("Log In");
        gp.add(loginButton, 1, 3);
        gp.setHalignment(loginButton, HPos.LEFT);//position to the left

        addControllers();
        //Login button click functionality
        Scene loginScene = new Scene(gp, 1366, 768);
        setChanged();
        notifyObservers(loginScene);
    }

    private void addControllers(){
        loginButton.setOnAction(e -> {
            if (msdb.login(userNameField.getText(), passwordField.getText())) {//if login was successful
                if (msdb.getRole().equals("student")) {
                    mv.getStudentInfoView().makeView();
                }
            }
        });
    }


}