package practica1.model;

import practica1.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int length;

    public ModelEvent(int n) {
        super(EventOrigin.Model);
        this.length = n;
    }
}
