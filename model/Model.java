package prova.model;

import java.util.Random;
import prova.Event;
import prova.EventListener;
import prova.Prova;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    
    private Prova prova;
    public int [] vector;
    
    public Model(int n, Prova prova) {
        this.vector = new int[n];
        this.prova = prova;
        
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
