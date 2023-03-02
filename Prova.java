package practica1;

import mesurament.Mesurament;
import practica1.control.Control;
import practica1.model.Model;
import practica1.vista.Vista;

/**
 *
 * @author usuario
 */
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
        model = new Model(100000, this);
        // Wait for button
        control = new Control(this);
        vista = new Vista(this);
        //control.run();
    }

    @Override
    public void notify(Event e) {
        switch (e.getEventType()){
            case Model -> {
                model.notify(e);
            }
            case View -> {
                vista.notify(e);
            }
            case Control -> {
                control.notify(e);
            }
        }
    }
    
    public Model getModel() {
        return this.model;
    }
}
