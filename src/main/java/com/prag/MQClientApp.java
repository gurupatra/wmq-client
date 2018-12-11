package com.prag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQClientApp extends Application {

    public static final String APPLICATION_NAMESPACE = "MQ Client APP";
    private static final Logger LOG = LoggerFactory.getLogger(APPLICATION_NAMESPACE);

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mq_client_ui.xml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
        LOG.debug("Application Started ...");
    }

    public static void main(String[] args) {
	  launch(args);

    }
}
