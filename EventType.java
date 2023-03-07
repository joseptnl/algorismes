package practica1;

import java.awt.Color;

/**
 *
 * @author usuario
 */
public enum EventType {
    VECTORIAL,
    ARRAY,
    HASH;
    
    public Color getColor() {
        switch (this) {
            case VECTORIAL -> {
                return Color.red;
            }
            case ARRAY -> {
                return Color.magenta;
            }
            case HASH -> {
                return Color.orange;
            }
        }
        return null;
    }
}
