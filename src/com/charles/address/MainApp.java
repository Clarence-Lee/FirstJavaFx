package com.charles.address;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2017/9/9.
 */
public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        ShowPersonOverview();
    }

    public void initRootLayout() {

    }
}
