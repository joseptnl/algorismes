package practica1.vista;

import practica1.Event;
import practica1.EventType;


/**
 *
 * @author usuario
 */
public class VistaEvent extends Event {
    public EventType type;
    public long time;
    
    public VistaEvent(long time, EventType typeOp) {
        super(EventOrigin.Vista);
        this.type = typeOp;
        this.time = time;
    }
}
