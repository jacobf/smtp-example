package smtp.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class OutgoingSmtpGatewaySpring {

    @Autowired
    private MessageChannel outboundMail;

    @Autowired
    private Properties smtpProperties;

    public void send(String s) {
        outboundMail.send(MessageBuilder.withPayload(s).
                setHeader(MailHeaders.TO,smtpProperties.getProperty("mail.to"))
                        .setHeader(MailHeaders.FROM, smtpProperties.getProperty("mail.from"))
                        .setHeader(MailHeaders.SUBJECT, "spring-integration works as well")
                        .build());
    }
}
