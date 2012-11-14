package smtp.example.spring;

import org.springframework.stereotype.Component;

@Component
public class OutgoingSmtpGatewaySpring {

    public void send(String s) {
        System.out.println("spring works correctly");
    }
}
