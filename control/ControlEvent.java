package practica1.control;

import java.util.List;
import practica1.Event;
import practica1.EventType;

/**
 *
 * @author usuario
 */
public class ControlEvent extends Event {
    public EventType[] types;
        
    public boolean operationType;   // If true means start executions, if false
                                    // means delete execution
             
    private boolean corruptData;
    
    public ControlEvent(List<EventType> executiontypes, boolean operationType) {
        super(EventOrigin.Control);
        this.operationType = operationType;
        this.types = new EventType[executiontypes.size()];
        
        try {
            for (int i = 0; i < executiontypes.size(); i++) {
                types[i] = executiontypes.get(i);
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
