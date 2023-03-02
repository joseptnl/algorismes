package practica1.vista;

import practica1.Event;
import practica1.EventType;


/**
 *
 * @author usuario
 */
public class VistaEvent extends Event {
    public long time;
    public int iteration;
    public EventType type;
    
    public VistaEvent(long time, EventType typeOp, int iteration) {
        super(EventOrigin.Vista);
        this.time = time;
        this.type = typeOp;
        this.iteration = iteration;
    }
}
