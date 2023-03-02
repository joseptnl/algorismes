package practica1;

/**
 *
 * @author usuario
 */
public abstract class Event {
    
    public enum EventOrigin {
        Model,
        Vista,
        Control
    }
    
    private final EventOrigin TYPE;
    
    public Event(EventOrigin type) {
        this.TYPE = type;
    }
    
    public EventOrigin getEventOrigin() {
        return TYPE;
    };
}
