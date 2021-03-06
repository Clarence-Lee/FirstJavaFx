package com.charles.address;

import com.charles.address.model.Person;
import com.charles.address.model.PersonListWrapper;
import com.charles.address.view.PersonEditDialogController;
import com.charles.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * Created by Administrator on 2017/9/9.
 */
public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("First Java Fx 8");

        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            //load root layout from builded fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            //load person overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            //set person overview into the center of the root layout
            //rootLayout.setCenter(personOverview);
            rootLayout.setLeft(personOverview);

            /* Give the controllers access to the main app.
            连接 MainApp 和 PersonOverviewController
            setMainApp(...) 必须被 MainApp 类调用. 这让我们可以访问MainApp对象并得到Persons的列表和其他东西.
            用以下代码替换showPersonOverview() 方法. 它包含了新增的两行
             */
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Open a dialog to edit person detail for the specified person. if click ok, the change are saved into the perovded
    person object and true is return.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            //Loading the fxml and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            //create dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            //TODO 解释initModality
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            //dialogStage.initStyle(StageStyle.DECORATED);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            //Set the person into the controller
            PersonEditDialogController personController = loader.getController();
            personController.setDialogStage(dialogStage);
            personController.setPerson(person);

            dialogStage.showAndWait();
            return personController.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /*
    The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /*
    Constructor
     */
    public MainApp() {
        //Add some sample data
        personData.add(new Person("Liu", "Dehua"));
        personData.add(new Person("Zhang", "Manyu"));
        personData.add(new Person("Zhang", "Xueyou"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Liu", "Ruoying"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /*
    Returns the data as an observable list of Persons
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
    Return the person file preference, i.e. the file that was opened.
    The preference is read from the OS specific registry. if no one return null.
     */

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /*
    Sets the file path of the currectly loaded file.
    @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            //Update the stage title.
            primaryStage.setTitle("AddressApp" + file.getName());
        } else {
            prefs.remove("filePath");

            //Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    /*
    Loads person data from the specified file. The current person data will be replaced.
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            //Reading the XML from the file and unmarshalling
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            //Save to the file path to the registry
            setPersonFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data from file: \n" + file.getPath());
            alert.showAndWait();

        }
    }

    /*
    Save the current person data to the specified file.
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Wrapping our data
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            //Marshalling and saving to registry
            mar.marshal(wrapper, file);

            //Save the file path to the registry
            setPersonFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data to the file: \n" + file.getPath());
        }
    }
}
