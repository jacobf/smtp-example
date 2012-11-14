package smtp.example.camel;

import org.testng.annotations.Test;

@Test
public class OutgoingSmtpGatewayTest {

    public void testSendingStringAsMail() throws Exception {
        new OutgoingSmtpGateway().send("my simple mail-conent");
    }
}
