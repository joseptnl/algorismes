package practica1.control;

import java.util.Arrays;
import java.util.Hashtable;
import practica1.Event;
import practica1.EventListener;
import practica1.Prova;
import practica1.model.Model;

/**
 *
 * @author usuario
 */

public class Control extends Thread implements EventListener {
    
    private Prova prova;
    private int moda;
    private int repModa;
    private int resultA, resultB;

    private static boolean doHash = false, doArray = false;
    
    public Control(Prova prova) {
        this.prova = prova;
    }
    
    @Override
    public void run() {
        Model model = prova.getModel();
        
        long temps; 
        if (doArray) {
            doArray = false;
            temps = System.nanoTime();
            modaWithArray(temps, model.vector);
        }
        
        if (doHash) {
            doHash = false;
            temps = System.nanoTime();
            modaWithHash(temps, model.vector);
        }
    }
    
    private void modaWithArray(long temps, int [] vector) {
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
        temps = System.nanoTime() - temps;
        resultA = moda;

        System.out.println("Per executar array he tardat\t"
                + temps + " ns. Moda = " + resultA);
        
    }
    
    private void modaWithHash(long temps, int [] vector) {
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
        temps = System.nanoTime() - temps;
        resultB = moda;
        
        System.out.println("Per executar hash he tardat\t"
                + temps + " ns. Moda = " + resultB);
    }

    @Override
    public void notify(Event e) {
        ControlEvent event = (ControlEvent) e;
        if (event.type.equals("ARRAY")) {
            doArray = true;
        }
        
        if (event.type.equals("HASH")) {
            doHash = true;
        }
        
        (new Thread(this)).start();
    }

}
