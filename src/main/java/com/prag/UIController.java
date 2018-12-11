package com.prag;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.prag.MQClientApp.APPLICATION_NAMESPACE;


public class UIController {

    private static final Logger LOG = LoggerFactory.getLogger(APPLICATION_NAMESPACE);
    @FXML
    private TextField hostName;


    @FXML
    public void handleSubmitButtonAction() {
        System.out.println("Hello  " + hostName.getText());
    }
}
