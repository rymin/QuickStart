package quickstart.microservises.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class MessageListener {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @StreamListener(ConsumerChannels.BROADCASTS)
    public void demoMessage(String message) {
        logger.info("Directed: " + message);
    }
}
