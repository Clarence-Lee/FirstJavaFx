package com.charles.address;

import com.charles.address.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        } catch (IOException e) {
            e.printStackTrace();
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
}
