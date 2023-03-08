package practica1.model;

import practica1.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int length;
    /*public boolean reset = false;
    
    public ModelEvent() {
        super(EventOrigin.Model);
        this.reset = true;
    }*/
    
    public ModelEvent(int n) {
        super(EventOrigin.Model);
        this.length = n;
    }
}
