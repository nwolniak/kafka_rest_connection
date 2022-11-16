package kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proto.model.RUNNING_STATE;

@RestController
@RequestMapping(value = "/kafka")
@RequiredArgsConstructor
public class Controller {

    private final SimulationStateChangeProducer simulationStateChangeProducer;
    private final VisualizationStateChangeProducer visualizationStateChangeProducer;
    private final SimulationNewNodesProducer simulationNewNodesProducer;

    @PostMapping(value = "/simulation/start")
    public void sendStartSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STARTED);
    }

    @PostMapping(value = "/simulation/resume")
    public void sendResumeSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.RESUMED);
    }

    @PostMapping(value = "/simulation/stop")
    public void sendStopSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STOPPED);
    }

    @PostMapping(value = "/simulation/close")
    public void sendCloseSimulationStateChangeMessage() {
        simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.CLOSED);
    }

    @PostMapping(value = "/visualization/start")
    public void sendStartVisualizationStateChangeMessage() {
        visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STARTED);
    }

    @PostMapping(value = "/visualization/resume")
    public void sendResumeVisualizationStateChangeMessage() {
        visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.RESUMED);
    }

    @PostMapping(value = "/visualization/stop")
    public void sendStopVisualizationStateChangeMessage() {
        visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STOPPED);
    }

    @PostMapping(value = "/visualization/close")
    public void sendCloseVisualizationStateChangeMessage() {
        visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.CLOSED);
    }

    @PostMapping(value = "/simulation/new-nodes")
    public void sendSimulationNewNodesTransferMessage() {
        simulationNewNodesProducer.sendSimulationNotOsmNodesTransferMessage();
    }
}
