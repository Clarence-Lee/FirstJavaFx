package com.charles.address.view;

import com.charles.address.Util.DateUtil;
import com.charles.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2017/9/11.
 * Dialog to edit details of a person
 */
public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /*
    Initializes the controller class. This method is automatically called
    after the fxml files loaded.
     */
    @FXML
    private void initialize() {}

    /*
    Sets the stage of this dialog.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /*
    set the person that to be edited in the dialog.
    @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
    }

    /*
    Return the okCliked is true
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /*
    Called when the user clickes ok
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /*
    Called when the user clicks cancel
     */
    @FXML
    private void handleCancel () {
        dialogStage.close();
    }

    /*
    Validates the user input in the text fields
     */

    private boolean isInputValid() {
        String errorMessage = "";

    }


}
