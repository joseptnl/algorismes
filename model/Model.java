package practica1.model;

import java.util.Random;
import practica1.Event;
import practica1.EventListener;
import practica1.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    
    private Main main;
    public int [] vector;
    
    public Model(int n, Main main) {
        this.vector = new int[n];
        this.main = main;
        
        assignRandoms();
    }
    
    private void assignRandoms() {
        Random rnd = new Random();
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = rnd.nextInt(11);
        }
    }

    @Override
    public void notify(Event e) {

    }
    
}
