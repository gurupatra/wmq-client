package com.prag.client.service;

import com.ibm.msg.client.jms.DetailedIllegalStateRuntimeException;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.prag.model.MQClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import static com.prag.ui.MQClientApp.APPLICATION_NAMESPACE;

public class JMSMQMessageSender {
    private final static Logger LOG = LoggerFactory.getLogger(APPLICATION_NAMESPACE);

    public String send(MQClientConfig clientConfig){
        LOG.debug("establishing connection ...");

        JMSContext context = null;
        Destination destination = null;
        JMSProducer producer = null;
        String status = null;

        try {
            // Create a connection factory
            JmsFactoryFactory jmsFactoryFactory = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
            JmsConnectionFactory jmsConnectionFactory = jmsFactoryFactory.createConnectionFactory();

            // Set connection properties
            jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_HOST_NAME, clientConfig.getHostName());
            jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_PORT, Integer.valueOf(clientConfig.getPort()));
            jmsConnectionFactory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
            jmsConnectionFactory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, clientConfig.getQueueManager());


            // Create JMS objects
            context = jmsConnectionFactory.createContext();
            destination = context.createQueue("queue:///" + clientConfig.getQueueName());

            TextMessage message = context.createTextMessage(clientConfig.getMessageText());

            producer = context.createProducer();
            producer.send(destination, message);
            status = "Message sent successfully.";
            LOG.debug(status);
        } catch (JMSException jmsException) {
            LOG.error(String.format("Error occurred while sending message %s", jmsException.getMessage()));
        } catch(JMSRuntimeException expr) {
            status = String.format("Error occurred while sending message %s", expr.getMessage());
            LOG.error(status);
        }
        return status;
    }
}
