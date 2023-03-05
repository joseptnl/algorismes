package practica1.control;

import practica1.Event;
import practica1.EventType;

/**
 *
 * @author usuario
 */
public class ControlEvent extends Event {
    final public EventType[] types;
    
    private boolean corruptData;
    
    public ControlEvent(int nexecutions, int[] executionstype) {
        super(EventOrigin.Control);
        types = new EventType[nexecutions];
        
        try {
            for (int i = 0; i < nexecutions; i++) {
                types[i] = EventType.values()[executionstype[i]];
            }
            corruptData = false;
        } catch (Exception e) {
            System.out.println("ERROR Vista: No s'ha notificat al controlador de manera correcta, revisi la crida.");
            corruptData = true;
        }
    }
    
    public boolean isCorrupt () {
        return corruptData;
    }
}
