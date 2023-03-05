package practica1.control;

import practica1.EventType;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import practica1.Event;
import practica1.EventListener;
import static practica1.EventType.*;
import practica1.Prova;
import practica1.model.Model;
import practica1.vista.VistaEvent;
import practica1.control.FunctionRef;

/**
 *
 * @author usuario
 */

public class Control extends Thread implements EventListener {
    private Prova prova;
    private int moda;
    private int repModa;
    private int resultA, resultB;
    
    static private boolean[] running;
    static private int[] vector;
    
    final static private EventType[] eventTypes = EventType.values(); 
    
    public Control(Prova prova) {
        this.prova = prova;
        this.running = new boolean[eventTypes.length];
        for (int i = 0; i < eventTypes.length; i++) running[i] = false;
    }
    
    @Override
    public void run() {
        Model model = prova.getModel();
        String threadName = Thread.currentThread().getName();
        EventType threadType = EventType.valueOf(threadName);
        vector = model.vector;
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
        for (int currentLength = 2; currentLength <= model.vector.length; currentLength++) {
            temps = System.nanoTime();
            algorithm.func();
            temps = System.nanoTime() - temps;
            prova.notify(new VistaEvent(temps, EventType.valueOf(threadName), currentLength));
        }
        
        synchronized (running) {
            running[EventType.valueOf(threadName).ordinal()] = false;
        }
    }
    
    private void modaWithArray() {
        Arrays.sort(vector);
       
        moda = -1;
        repModa = 0;
        int currentNum = -1;
        int repeticions = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] != currentNum) {
                currentNum = vector[i];
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
        resultA = moda;

        /*System.out.println("Per executar array he tardat\t"
                + temps + " ns. Moda = " + resultA);*/
    }
    
    /*
    Recibe como parámetro un vector de enteros y devuelve otro vector de enteros que 
    representa el producto vectorial del mismo vector por sí mismo. Para calcular cada 
    elemento del vector resultado, se realiza la suma de los productos
    */
    private void productoVectorial() {
        int n = vector.length;
        int[] resultado = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i] += vector[i] * vector[j];
            }
        }

        /*System.out.println("Per executar el producte vectorial he tardat\t"
                + temps + " ns.");*/
    }
        
    private void modaWithHash() {
        Hashtable<Integer,Integer> ht = new Hashtable<Integer,Integer>();
        repModa = 0;
        for (int i = 0; i < vector.length; i++) {
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
        }
        resultB = moda;
        
        /*System.out.println("Per executar hash he tardat\t"
                + temps + " ns. Moda = " + resultB);*/
    }

    @Override
    public void notify(Event e) {
        ControlEvent event = (ControlEvent) e;
        
        if (!event.isCorrupt()) {
            for (int i = 0; i < event.types.length; i++) {
                int eventid = event.types[i].ordinal();
                synchronized (running) {
                    if (running[eventid]) continue;
                    running[eventid] = true;
                }

                Thread thread = new Thread(this);
                thread.setName(event.types[i].toString());
                thread.start();
            }
        }
    }
}
