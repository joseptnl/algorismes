package prova;

import mesurament.Mesurament;
import prova.control.Control;
import prova.model.Model;
import prova.vista.Vista;

/**
 *
 * @author usuario
 */
// Hola martin como estás ??
public class Prova implements EventListener {
    
    private Model model;
    private Vista vista;
    private Control control;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesurament.mesura();
        (new Prova()).init();
    }
    
    private void init() {
        model = new Model(1000, this);
        // Wait for button
        control = new Control(this);
        control.run();
    }

    @Override
    public void notify(Event e) {
        switch (e.getEventType()) {
            case Model:
                model.notify();
                break;
            case View:
                vista.notify();
                break;
            case Control:
                control.notify();
                break;
        }
    }
    
    public Model getModel() {
        return this.model;
    }
}
