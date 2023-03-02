package practica1.control;

import practica1.EventType;
import java.util.Arrays;
import java.util.Hashtable;
import practica1.Event;
import practica1.EventListener;
import static practica1.EventType.*;
import practica1.Prova;
import practica1.model.Model;
import practica1.vista.VistaEvent;

/**
 *
 * @author usuario
 */
public class Control extends Thread implements EventListener {
    private Prova prova;
    private int moda;
    private int repModa;
    private int resultA, resultB;
    private int currentLength;

    private static boolean doHash = false, doArray = false, doProducte = false;
    
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
        
        if (doProducte) {
            doProducte = false;
            temps = System.nanoTime();
            productoVectorial(temps, model.vector);
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
            prova.notify(new VistaEvent(temps, EventType.ARRAY, currentLength));
        }
        temps = System.nanoTime() - temps;
        resultA = moda;

        System.out.println("Per executar array he tardat\t"
                + temps + " ns. Moda = " + resultA);
    }
    
    /*
    Recibe como parámetro un vector de enteros y devuelve otro vector de enteros que 
    representa el producto vectorial del mismo vector por sí mismo. Para calcular cada 
    elemento del vector resultado, se realiza la suma de los productos
    */
    private void productoVectorial(long temps, int [] vector) {
        int n = vector.length;
        int[] resultado = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i] += vector[i] * vector[j];
                prova.notify(new VistaEvent(temps, EventType.VECTORIAL, currentLength));
            }
        }

        System.out.println("Per executar el producte vectorial he tardat\t"
                + temps + " ns.");
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
            prova.notify(new VistaEvent(temps, EventType.HASH, currentLength));
        }
        temps = System.nanoTime() - temps;
        resultB = moda;
        
        System.out.println("Per executar hash he tardat\t"
                + temps + " ns. Moda = " + resultB);
    }

    @Override
    public void notify(Event e) {
        ControlEvent event = (ControlEvent) e;
        currentLength = 2;
        if (event.type.equals(ARRAY)) {
            doArray = true;
        }
        
        if (event.type.equals(HASH)) {
            doHash = true;
        }
        
        if (event.type.equals(VECTORIAL)) {
            doProducte = true;
        }
        
        (new Thread(this)).start();
    }

}
