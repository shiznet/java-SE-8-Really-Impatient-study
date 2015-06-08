package java8.chap1lambda;

/**
 * Created by shiznet3908@gmail.com on 15/5/24.
 */
public class Event {

    private String eventName;

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                '}';
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
