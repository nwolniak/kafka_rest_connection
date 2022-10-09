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

    @PostMapping(value = "/simulation/start")
    public void sendStartSimulationStateChangeMessage() {
        this.simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STARTED);
    }

    @PostMapping(value = "/simulation/resume")
    public void sendResumeSimulationStateChangeMessage() {
        this.simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.RESUMED);
    }

    @PostMapping(value = "/simulation/stop")
    public void sendStopSimulationStateChangeMessage() {
        this.simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STOPPED);
    }

    @PostMapping(value = "/simulation/close")
    public void sendCloseSimulationStateChangeMessage() {
        this.simulationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.CLOSED);
    }

    @PostMapping(value = "/visualization/start")
    public void sendStartVisualizationStateChangeMessage() {
        this.visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STARTED);
    }

    @PostMapping(value = "/visualization/resume")
    public void sendResumeVisualizationStateChangeMessage() {
        this.visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.RESUMED);
    }

    @PostMapping(value = "/visualization/stop")
    public void sendStopVisualizationStateChangeMessage() {
        this.visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.STOPPED);
    }

    @PostMapping(value = "/visualization/close")
    public void sendCloseVisualizationStateChangeMessage() {
        this.visualizationStateChangeProducer.sendStateChangeMessage(RUNNING_STATE.CLOSED);
    }
}
