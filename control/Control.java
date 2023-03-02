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
public class Control implements EventListener {
    
    private Prova prova;
    private int moda;
    private int repModa;
    private int resultA, resultB;
    
    public Control(Prova prova) {
        this.prova = prova;
    }
    
    public void run() {
        Model model = prova.getModel();
        
        long temps = System.nanoTime();
        
        Hashtable<Integer,Integer> ht = new Hashtable<Integer,Integer>();
        repModa = 0;
        for (int i = 0; i < model.vector.length; i++) {
            if (!ht.containsKey(model.vector[i])) {
                ht.put(model.vector[i], 1);
                if (1 > repModa) {
                    moda = model.vector[i];
                    repModa = 1;
                }
            } else {
                int prevValue = (int) ht.get(model.vector[i]);
                ht.replace(model.vector[i], prevValue+1);
                if (prevValue+1 > repModa) {
                    moda = model.vector[i];
                    repModa = prevValue+1;
                }
            }
        }
        temps = System.nanoTime() - temps;
        resultB = moda;
        
        System.out.println("Per executar hash he tardat\t"
                + temps + " ns. Moda = " + resultB);
        
        temps = System.nanoTime();
        
        Arrays.sort(model.vector);
        
        moda = -1;
        repModa = 0;
        int currentNum = -1;
        int repeticions = 0;
        for (int i = 0; i < model.vector.length; i++) {
            if (model.vector[i] != currentNum) {
                currentNum = model.vector[i];
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

    @Override
    public void notify(Event e) {

    }
    
}
