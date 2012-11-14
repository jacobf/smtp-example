package epost.examples;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

public class OutgoingSmtpGateway {

    private final ProducerTemplate producerTemplate;
    private final CamelContext context;
    private final Properties properties = new Properties();
    private final String userDir = System.getProperty("user.home");
    private final String separator = System.getProperty("file.separator");
    private static final String SMTP_PROPERTIES_FILE_NAME = ".smtpproperties";
    private final String smtpPropertiesPath = userDir+separator+ SMTP_PROPERTIES_FILE_NAME;
    private static final String SMTP_CONNECTION = "smtp.connection";
    private static final String TO = "mail.to";
    private static final String FROM = "mail.from";

    public OutgoingSmtpGateway() throws Exception {
        properties.load(new FileInputStream(smtpPropertiesPath));
        context = new DefaultCamelContext();

        RouteBuilder builder = new RouteBuilder() {
            public void configure() {
                from("jms-queue:queue:mail.queue").to(properties.getProperty(SMTP_CONNECTION));
            }
        };

        context.addRoutes(builder);
        context.addComponent("jms-queue", activeMQComponent("vm://localhost?broker.persistent=false"));
        producerTemplate = context.createProducerTemplate();
    }

    public void send(String s) throws Exception {
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("from", properties.getProperty(FROM));
        headers.put("to", properties.getProperty(TO));
        headers.put("subject", "Apache Camel just works");

        context.start();
        producerTemplate.sendBodyAndHeaders("jms-queue:queue:mail.queue", s, headers);
        context.stop();
    }


}
