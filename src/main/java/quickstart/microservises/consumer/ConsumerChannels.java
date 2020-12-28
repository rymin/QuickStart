package quickstart.microservises.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {

    String BROADCASTS = "myDemoBroadcast";

    @Input(BROADCASTS)
    SubscribableChannel broadcastChannel();

}
