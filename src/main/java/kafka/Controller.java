package kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proto.model.VisualizationStateChangeMessage;

import static proto.model.RUNNING_STATE.*;

@RestController
@RequestMapping(value = "/kafka")
@RequiredArgsConstructor
public class Controller {

    private final SimulationStateChangeProducer simulationStateChangeProducer;
    private final VisualizationStateChangeProducer visualizationStateChangeProducer;
    private final SimulationNewNodesProducer simulationNewNodesProducer;

    @PostMapping(value = "/simulation/start")
    public void sendStartSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(STARTED);
    }

    @PostMapping(value = "/simulation/resume")
    public void sendResumeSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(RESUMED);
    }

    @PostMapping(value = "/simulation/stop")
    public void sendStopSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(STOPPED);
    }

    @PostMapping(value = "/simulation/close")
    public void sendCloseSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(CLOSED);
    }

    @PostMapping(value = "/visualization/start")
    public void sendStartVisualizationStateChangeMessage() {
        VisualizationStateChangeMessage visualizationStateChangeMessage = VisualizationStateChangeMessage.newBuilder()
                .setStateChange(STARTED)
                .setRoiRegion(VisualizationStateChangeMessage.ROIRegion.newBuilder().build())
                .setZoomLevel(VisualizationStateChangeMessage.ZOOM_LEVEL.CARS)
                .setVisualizationSpeed(50)
                .build();
        visualizationStateChangeProducer.sendStateChangeMessage(visualizationStateChangeMessage);
    }

    @PostMapping(value = "/visualization/resume")
    public void sendResumeVisualizationStateChangeMessage() {
        VisualizationStateChangeMessage visualizationStateChangeMessage = VisualizationStateChangeMessage.newBuilder()
                .setStateChange(RESUMED)
                .build();
        visualizationStateChangeProducer.sendStateChangeMessage(visualizationStateChangeMessage);
    }

    @PostMapping(value = "/visualization/stop")
    public void sendStopVisualizationStateChangeMessage() {
        VisualizationStateChangeMessage visualizationStateChangeMessage = VisualizationStateChangeMessage.newBuilder()
                .setStateChange(STOPPED)
                .build();
        visualizationStateChangeProducer.sendStateChangeMessage(visualizationStateChangeMessage);
    }

    @PostMapping(value = "/visualization/close")
    public void sendCloseVisualizationStateChangeMessage() {
        VisualizationStateChangeMessage visualizationStateChangeMessage = VisualizationStateChangeMessage.newBuilder()
                .setStateChange(CLOSED)
                .build();
        visualizationStateChangeProducer.sendStateChangeMessage(visualizationStateChangeMessage);
    }

    @PostMapping(value = "/simulation/new-nodes")
    public void sendSimulationNewNodesTransferMessage() {
        simulationNewNodesProducer.sendSimulationNotOsmNodesTransferMessage();
    }
}
