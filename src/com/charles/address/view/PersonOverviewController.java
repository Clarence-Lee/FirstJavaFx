package com.charles.address.view;

import com.charles.address.MainApp;
import com.charles.address.Util.DateUtil;
import com.charles.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

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

    /*Called when the user clicks on the delete button.
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        personTable.getItems().remove(selectedIndex);
    }
    */
    //fixed delete button.
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        }else {
            //when personTable is empty.
            Dialogs.create().title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

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
        //TODO Lambdas 特性 -> ;
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        //Clear person details.重设个人详情
        showPersonDetails(null);

        //Listen for selection changes and show the person details when changed
        //在JavaFX中有一个接口称为ChangeListener，带有一个方法changed()。该方法有三个参数：observable, oldValue和newValue。
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
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

    /*
    Fills all text fields to show details about the person.
    If the specified person is null, all text fields are cleared.
    @parm person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            //Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            //TODO: we need a way to convert the birthday to the string
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        }else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }


}
