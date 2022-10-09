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

    private final Producer producer;

    @PostMapping(value = "/start")
    public void sendStartMessage() {
        this.producer.changeState(RUNNING_STATE.STARTED);
    }

    @PostMapping(value = "/resume")
    public void sendResumeMessage() {
        this.producer.changeState(RUNNING_STATE.RESUMED);
    }

    @PostMapping(value = "/stop")
    public void sendStopMessage() {
        this.producer.changeState(RUNNING_STATE.STOPPED);
    }

    @PostMapping(value = "/close")
    public void sendCloseMessage() {
        this.producer.changeState(RUNNING_STATE.CLOSED);
    }
}
