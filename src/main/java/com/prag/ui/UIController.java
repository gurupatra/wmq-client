package com.prag.ui;

import com.prag.client.service.JMSMQMessageSender;
import com.prag.model.MQClientConfig;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.prag.ui.MQClientApp.APPLICATION_NAMESPACE;


public class UIController {

    private static final Logger LOG = LoggerFactory.getLogger(APPLICATION_NAMESPACE);
    @FXML
    private TextField hostName;

    @FXML
    private TextField port;

    @FXML
    private TextField queueName;

    @FXML
    private TextField queueManager;

    @FXML
    private TextArea messageText;

    @FXML
    private Text actiontarget;


    @FXML
    public void handleSendButtonAction() {
        LOG.debug("Populate the model");
        MQClientConfig config = new MQClientConfig();
        config.setHostName(hostName.getText());
        config.setPort(port.getText());
        config.setQueueManager(queueManager.getText());
        config.setQueueName(queueName.getText());
        config.setMessageText(messageText.getText());
        JMSMQMessageSender messageSender = new JMSMQMessageSender();
        String sentStatus = messageSender.send(config);
        actiontarget.setText(sentStatus);
    }

    @FXML
    public void handleResetButtonAction() {
        messageText.setText("");
        actiontarget.setText("");
    }
}
