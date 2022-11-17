package kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import proto.model.VisualizationStateChangeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisualizationStateChangeProducer {

    private final KafkaTemplate<String, VisualizationStateChangeMessage> visualizationKafkaTemplate;

    public void sendStateChangeMessage(VisualizationStateChangeMessage visualizationStateChangeMessage) {
        var record = new ProducerRecord<String, VisualizationStateChangeMessage>(TopicConfiguration.VISUALIZATION_STATE_CHANGE_TOPIC,
                visualizationStateChangeMessage);
        ListenableFuture<SendResult<String, VisualizationStateChangeMessage>> future = visualizationKafkaTemplate.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, VisualizationStateChangeMessage> result) {
                log.info("VisualizationStateChangeMessage send: stateChange={}, ROIRegion={}, ZoomLevel={}, VisualizationSpeed={}",
                        visualizationStateChangeMessage.getStateChange(), visualizationStateChangeMessage.getRoiRegion(),
                        visualizationStateChangeMessage.getZoomLevel(), visualizationStateChangeMessage.getVisualizationSpeed());
            }

            @Override
            public void onFailure(@NotNull Throwable ex) {
                log.info("Error while sending message: {}", visualizationStateChangeMessage + " " + ex.getMessage());
            }
        });
    }
}
