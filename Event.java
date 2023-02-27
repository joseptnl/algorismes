package practica1;

/**
 *
 * @author usuario
 */
public abstract class Event {
    
    public enum EventType {
        Model,
        View,
        Control
    }
    
    private final EventType TYPE;
    
    public Event(EventType type) {
        this.TYPE = type;
    }
    
    public EventType getEventType() {
        return TYPE;
    };
}
