package practica1.control;

import practica1.Event;
import practica1.EventType;

/**
 *
 * @author usuario
 */
public class ControlEvent extends Event {
    public EventType type;
    
    public ControlEvent(int buttonNum) {
        super(EventOrigin.Control);
        switch (buttonNum) {
            case 1:
                type = EventType.VECTORIAL;
                break;
            case 2:
                type = EventType.ARRAY;
                break;
            case 3:
                type = EventType.HASH;
                break;
        }
    }
}
