package practica1.vista;

import practica1.Event;
import practica1.EventType;


/**
 *
 * @author usuario
 */
public class VistaEvent extends Event {
    public long maxTime;
    public int iteration;
    public EventType type;
    public long time;
    
    public VistaEvent(long time, long maxTime, EventType typeOp) {
        super(EventOrigin.Vista);
        this.maxTime = maxTime;
        this.type = typeOp;
        this.time = time;
    }
}
