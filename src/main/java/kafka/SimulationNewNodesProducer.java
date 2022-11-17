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
import proto.model.Coordinates;
import proto.model.Node;
import proto.model.SimulationNewNodesTransferMessage;

import java.util.List;

import static kafka.TopicConfiguration.SIMULATION_NEW_NODES_TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulationNewNodesProducer {

    private final KafkaTemplate<String, SimulationNewNodesTransferMessage> kafkaTemplate;

    private static final List<Node> nodeList = List.of(
            Node.newBuilder().setNodeId("1").setCoordinates(Coordinates.newBuilder().setLongitude(30.0).setLatitude(60.0)).build(),
            Node.newBuilder().setNodeId("2").setCoordinates(Coordinates.newBuilder().setLongitude(31.0).setLatitude(61.0)).build(),
            Node.newBuilder().setNodeId("3").setCoordinates(Coordinates.newBuilder().setLongitude(32.0).setLatitude(62.0)).build(),
            Node.newBuilder().setNodeId("4").setCoordinates(Coordinates.newBuilder().setLongitude(33.0).setLatitude(63.0)).build(),
            Node.newBuilder().setNodeId("5").setCoordinates(Coordinates.newBuilder().setLongitude(34.0).setLatitude(64.0)).build(),
            Node.newBuilder().setNodeId("6").setCoordinates(Coordinates.newBuilder().setLongitude(35.0).setLatitude(65.0)).build()
    );

    public void sendSimulationNotOsmNodesTransferMessage() {
        final SimulationNewNodesTransferMessage simulationNotOsmNodesTransferMessage =
                SimulationNewNodesTransferMessage.newBuilder().addAllNodes(nodeList).build();

        var record = new ProducerRecord<String, SimulationNewNodesTransferMessage>(SIMULATION_NEW_NODES_TOPIC,
                simulationNotOsmNodesTransferMessage);

        ListenableFuture<SendResult<String, SimulationNewNodesTransferMessage>> future = kafkaTemplate.send(record);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, SimulationNewNodesTransferMessage> result) {
                log.info("SimulationNewNodesTransferMessage send {} new nodes",
                        simulationNotOsmNodesTransferMessage.getNodesCount());
            }

            @Override
            public void onFailure(@NotNull Throwable ex) {
                log.info("Error while sending message: {}", ex.getMessage());
            }
        });
    }

}