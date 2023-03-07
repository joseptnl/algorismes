package practica1.control;

import practica1.EventType;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica1.Event;
import practica1.EventListener;
import static practica1.EventType.*;
import practica1.Main;
import practica1.model.Model;
import practica1.vista.VistaEvent;

/**
 *
 * @author usuario
 */

public class Control extends Thread implements EventListener {
    private Main main;
    private int moda;
    private int repModa;
    
    static private boolean[] running;
    static private int[] vector;
    static private long maxTime = 0;
    static private int increaseRate;
    static private long sleepTime;
    
    private int currentIter;
    private Thread[] threadsRef;
    
    final static private EventType[] eventTypes = EventType.values(); 
    
    public Control(Main main) {
        this.main = main;
        this.running = new boolean[eventTypes.length];
        for (int i = 0; i < eventTypes.length; i++) running[i] = false;
        threadsRef = new Thread[eventTypes.length];
        
    }
    
    /**
     * Funcions sobreescrites de les interfícies.
     */
    
    /**
     * Reb un objecte event enviat des de la vista, l'interpreta i fa l'acció
     * correponent com a resposta
     */
    @Override
    public void notify(Event e) {
        ControlEvent event = (ControlEvent) e;
        
        if (!event.isCorrupt()) {
            if (event.operationType) {
                for (int i = 0; i < event.types.length; i++) {
                    int eventid = event.types[i].ordinal();
                    synchronized (running) {
                        if (running[eventid]) continue;
                        running[eventid] = true;
                    }
                    threadsRef[eventid] = new Thread(this);
                    threadsRef[eventid].setName(eventTypes[eventid].toString());
                    threadsRef[eventid].start();
                }
            } else {
                for (int i = 0; i < event.types.length; i++) {
                    int eventid = event.types[i].ordinal();
                    synchronized (running) {
                        if (!running[eventid]) continue;
                        running[eventid] = false;
                    }
                    //threadsRef[eventid].stop();
                    threadsRef[eventid].interrupt();
                }
            }
        }
    }
    
    /**
     * Executa el codi corresponent a cada thread segons el seu nom
     */
    @Override
    public void run() {
        Model model = main.getModel();
        String threadName = Thread.currentThread().getName();
        EventType threadType = EventType.valueOf(threadName);
        vector = model.vector;
        increaseRate = vector.length / model.N_PUNTS;
        sleepTime = model.getSleepTime(); 
        FunctionRef algorithm = null;
        
        switch(threadType) {
            case ARRAY:
                algorithm = () -> {modaWithArray();};
                break;
            case HASH:
                algorithm = () -> {modaWithHash();};
                break;
            case VECTORIAL:
                algorithm = () -> {productoVectorial();};
                break;
        }

        long temps;
        for (int currentLength = 2; currentLength <= model.vector.length; currentLength+=increaseRate) {
            this.currentIter = currentLength;
            temps = System.nanoTime();
            algorithm.func();
            temps = System.nanoTime() - temps;
            
            if (temps > maxTime) maxTime = temps;
            model.addTime(threadType, temps);
            main.notify(new VistaEvent(maxTime, EventType.valueOf(threadName)));
        }
        
        synchronized (running) {
            running[EventType.valueOf(threadName).ordinal()] = false;
        }
    }
    
    /**
     * ALGORISMES PER A REPRESENTAR EL COST ASINTÒTIC
     * 
     * Prenen el vector d'enters proporcionat per el model i li apliquen certes 
     * operacions, cada un simulant un cost d'execució distint.
    */
    
    /**
     * Calcula la moda del vector de la següent manera:
     *  - Ordena el vector
     *  - Compte el nombre d'aparicions consecutives de cada un i establint com
     *  a moda el nombre que més vegades aparegui consecutivament
     */
    private void modaWithArray() {
        int [] copy = Arrays.copyOf(vector, currentIter);
        Arrays.sort(copy);
       
        moda = -1;
        repModa = 0;
        int currentNum = -1;
        int repeticions = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != currentNum) {
                currentNum = copy[i];
                repeticions = 1;
            } else {
                repeticions++;
            }

            if (repeticions > repModa && currentNum != moda) {
                moda = currentNum;
                repModa = repeticions;
            } else if (repeticions > repModa) {
                repModa = repeticions;
            }
        }
    }
    
    /**
     * Realitza un producte vectorial del vector amb ell mateix per a simular
     * un cost exponencial
     */
    private void productoVectorial() {
        int n = currentIter;
        int[] resultado = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i] += vector[i] * vector[j];
                
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Calcula la moda del vector de la següent manera:
     *  - Recorre el vector
     *  - Per a cada element que no estigui dins el hash l'inserta com a clau i
     *  posa el seu valor(representat el nombre de vegades que apareix dins aquest)
     *  a 1, si l'element ja està dins el hash actualitzarà el seu valor sumant 1
     * 
     *  NOTA: En cada moment emmagatzema quin és la clau amb el valor més alt per 
     *  a l'hora de trobar la moda no haver de recórrer tota l'estructura
     */    
    private void modaWithHash() {
        Hashtable<Integer,Integer> ht = new Hashtable<Integer,Integer>();

        repModa = 0;
        for (int i = 0; i < currentIter; i++) {
            if (!ht.containsKey(vector[i])) {
                ht.put(vector[i], 1);
                if (1 > repModa) {
                    moda = vector[i];
                    repModa = 1;
                }
            } else {
                int prevValue = (int) ht.get(vector[i]);
                ht.replace(vector[i], prevValue+1);
                if (prevValue+1 > repModa) {
                    moda = vector[i];
                    repModa = prevValue+1;
                }
            }
            
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
