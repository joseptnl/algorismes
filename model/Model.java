package practica1.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import practica1.Event;
import practica1.EventListener;
import practica1.EventType;
import practica1.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    
    private Main main;
    public int [] vector;
    private ConcurrentHashMap<EventType, ArrayList<Long>> llistaTime;
    
    public Model(Main main) {
        this.main = main;
        
        this.llistaTime = new ConcurrentHashMap<>();

        for (EventType alg : EventType.values()) {
            this.llistaTime.put(alg, new ArrayList<>());
        }
    }
    
    private void assignRandoms() {
        Random rnd = new Random();
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = rnd.nextInt(11);
        }
    }
    
    public void addTime(EventType alg, long time) {
        ArrayList<Long> timeValues = this.llistaTime.get(alg);

        timeValues.add(time);

        this.llistaTime.put(alg, timeValues);
    }
    
    public ConcurrentHashMap<EventType, ArrayList<Long>> getTimes() {
        return this.llistaTime;
    }
    
    public void reset() {
        for (EventType alg : EventType.values()) {
            this.llistaTime.put(alg, new ArrayList<>());
        }
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        this.vector = new int[event.length];
        
        assignRandoms();
    }
    
}
