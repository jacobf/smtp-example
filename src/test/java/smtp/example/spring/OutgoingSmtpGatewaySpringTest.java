package smtp.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = "classpath:application-context.xml")
public class OutgoingSmtpGatewaySpringTest extends AbstractTestNGSpringContextTests{

    @Autowired
    OutgoingSmtpGatewaySpring outgoingSmtpGatewaySpring;

    public void testSendingStringAsMail() throws Exception {
        outgoingSmtpGatewaySpring.send("my simple mail-conent");
    }
}
