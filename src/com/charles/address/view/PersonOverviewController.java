package com.charles.address.view;

import com.charles.address.MainApp;
import com.charles.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Administrator on 2017/9/9.
 * the class should put in the same path with PersonOverview.fxml
 */
public class PersonOverviewController {
    /*所有fxml文件需要访问的属性和方法必须加上 @FXML 注解.
    实际上,只有在私有的情况下才需要, 但是让它们保持私有并且用注解标记的方式更好!
     */
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    //Reference to the main application
    private MainApp mainApp;

    /*
    The constructor
    The constructor is called before initial() method
     */

    public PersonOverviewController() {

    }

    /*
    Initializes the controller class.
    initialize() 方法在fxml文件完成载入时被自动调用. 那时, 所有的FXML属性都应已被初始化.
     */
    @FXML
    private void initialize() {
        //Lambdas 特性 -> ;
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }


}