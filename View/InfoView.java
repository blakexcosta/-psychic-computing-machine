package View;

import Model.MySQLDatabase;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.Observable;

/**
 * Blake Costa, Gavin Drabik, Matthew Turczmanovicz, Oswaldo Rosete-Garcia, and Quinn Bissen
 * Group 11
 * ISTE-330
 * Professor Floeser
 * December 18th, 2017
 */

// TODO: 12/8/17 Document and Fix indentation -Blake 
public class InfoView extends Observable {
    //both of these are in ALL the view classes
    private MySQLDatabase msdb = MySQLDatabase.getInstance(); //there is only one instance of the database.
    private MasterView mv;//this is passed in through the constructor

    private String[][] sqlData;
    private Label userInfoHeaderLabel,labName, labUserName, labDepartment, labGradDate, labMajor, labRole;
    private GridPane gp;
    private String[][] returnData;

    public InfoView(MasterView _mv) {
        this.mv = _mv;
    }

    //TODO: Build out student info view (make it look nice. also add the information in loadStudentDBInfo)
    public void makeStudentView() {
        //this scene object comes from masterView. it has a border pane in it. The top object of the border pane has been set to the nav bar
        //the borderpane can be referenced by casting an object seen below
        Scene sc = mv.getBaseScene();
        //add the style sheet to the Scene
        sc.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());//add css files
        BorderPane bp = (BorderPane) sc.getRoot();
        gp = new GridPane();
        //add the grid pane to the border pane
        bp.setCenter(gp);

        //set margins between cells
        gp.setHgap(5);
        gp.setVgap(10);
        //center the grid pane on the page
        gp.setAlignment(Pos.CENTER);

        //make the main header
        userInfoHeaderLabel = new Label("User Information");//Bigger header on top
        //style the header with css
        userInfoHeaderLabel.getStyleClass().add("mainHeader");
        userInfoHeaderLabel.setAlignment(Pos.CENTER);

        //add the header to the gird pane
        gp.add(userInfoHeaderLabel, 0, 0, 2, 1);
        labName = new Label("Name: ");
        labName.getStyleClass().add("infoLabel");

        labUserName = new Label("User Name: ");
        labUserName.getStyleClass().add("infoLabel");

        labDepartment = new Label("Department: ");
        labDepartment.getStyleClass().add("infoLabel");

        labGradDate = new Label("Graduation Date: ");
        labGradDate.getStyleClass().add("infoLabel");

        labMajor = new Label("Major: ");
        labMajor.getStyleClass().add("infoLabel");

        labRole = new Label("Role: ");
        labRole.getStyleClass().add("infoLabel");

        gp.add(labName, 0, 1);
        gp.add(labUserName, 0, 2);
        gp.add(labDepartment, 0, 3);
        gp.add(labGradDate, 0, 4);
        gp.add(labMajor, 0, 5);
        gp.add(labRole, 0, 6);
        gp.gridLinesVisibleProperty().setValue(true);

        loadStudentDBInfo();//this sets the labels to the proper info using the local class instance of MSDB
        //loop through each of the user fields that we have and put them next to the label. They come in in the correct order
        setChanged();
        notifyObservers(sc);
    }

    /**
     * gets all of the data to be put next to the labels in the grid.
     */
    public void loadStudentDBInfo() {
        String[] userNameAL = {msdb.getUserName()};
        returnData = msdb.getData("SELECT CONCAT(FirstName, ' ',  LastName) as 'Name' , UserName, Department, GraduationDate,Major, Role FROM user where UserName in (?)", userNameAL);
        int rowCount = 0;
        for (String str : returnData[1]){
            Label lab = new Label(str);
            lab.getStyleClass().add("infoDataLabel");
            gp.add(lab,1,++rowCount);

        }
    }

    //TODO: Build out staff info view
    public void makeStaffView() {
        //this scene object comes from masterView. it has a border pane in it. The top object of the border pane has been set to the nav bar
        //the borderpane can be referenced by casting an object seen below
        Scene sc = mv.getBaseScene();
        BorderPane bp = (BorderPane) sc.getRoot();

        //After the scene is made completely these two methods run which will update the master view to our new view
        setChanged();
        notifyObservers(sc);
    }

    //TODO: Build out faculty info view
    public void makeFacultyView() {
        //this scene object comes from masterView. it has a border pane in it. The top object of the border pane has been set to the nav bar
        //the borderpane can be referenced by casting an object seen below
        Scene sc = mv.getBaseScene();
        BorderPane bp = (BorderPane) sc.getRoot();

        //After the scene is made completely, these two methods run which will update the master view to our new view
        setChanged();
        notifyObservers(sc);
    }


}