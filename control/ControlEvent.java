package practica1.control;

import practica1.Event;

/**
 *
 * @author usuario
 */
public class ControlEvent extends Event {
    public ControlEventType type;
    
    public ControlEvent(int buttonNum) {
        super(EventType.Control);
        switch (buttonNum) {
            case 1:
                type = ControlEventType.VECTORIAL;
                break;
            case 2:
                type = ControlEventType.ARRAY;
                break;
            case 3:
                type = ControlEventType.HASH;
                break;
        }
    }
    
    enum ControlEventType {
        VECTORIAL,
        ARRAY,
        HASH
    }
}
