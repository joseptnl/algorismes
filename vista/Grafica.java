package practica1.vista;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import practica1.EventType;
import practica1.Main;

/**
 *
 * @author usuario
 */
public class Grafica extends JPanel {
    private BufferedImage bima;
    private Main main;
    private long maxTime;
    // currentPoint es fa servir per actualitzar sa barra de progres
    private int currentPoint, n;
    // N_PUNTS representa el nombre de punts a visualitzar
    // TIME_LIMIT es un temps limit definit per aturar la visualitzacio del algorisme que creix mes rapidament
    private final int N_PUNTS = 60, MS_SLEEP = 200, TIME_LIMIT = 73341000;
    private JProgressBar bar;
    // stopped ajuda a saber si el limit ha estat sobrepassat
    private boolean stopped;
    
    public Grafica(Main main, JProgressBar bar) {
        this.main = main;
        this.bar = bar;
        this.currentPoint = 0;
        this.stopped = false;
        this.maxTime = 1;
    }
    
    public void setN(int n) {
        this.n = n;
    }
    
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
    // metode paint que fa servir la tecnica de doble buffering
    // uneix els punts dels algorisme, obtenint una grafica
    public void paint(Graphics gr) {
        ConcurrentHashMap<EventType, ArrayList<Long>> llista = main.getModel().getTimes();
        
        if (bima == null) {
            if (this.getWidth() > 0) {
                bima = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D  bima_graphics = bima.createGraphics();
                bima_graphics.setColor(new java.awt.Color(191, 209, 231));
                bima_graphics.fillRect(0, 0, bima.getWidth(), bima.getHeight());
            }
        }
        gr.drawImage(bima, 0, 0, this);
        
        Graphics2D g2 = (Graphics2D) gr;
        g2.setStroke(new BasicStroke(2F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Set<EventType> algs = llista.keySet();

        int lastPoint = N_PUNTS+1;
        boolean exceeded = false;
        for(EventType alg : algs){
            long temps = 0;
            g2.setColor(alg.getColor());

            ArrayList<Long> times = llista.get(alg);
            int size = times.size();
            
            if (0 < size && size < lastPoint) {
                lastPoint = size;
            }
            
            for (int i = 0; i < size; i++) {
                int lastX = transformX(i);
                int lastY = transformY(i == 0 ? 0 : times.get(i-1), this.maxTime);

                int X = transformX(i+1);
                temps = times.get(i);
                int Y = transformY(temps, this.maxTime);
                
                if (temps > TIME_LIMIT && alg.equals(EventType.VECTORIAL)) {
                    if (!this.stopped) {
                        exceeded = true;
                        this.stopped = true;   
                    }
                    break;
                }
                g2.drawLine(lastX, lastY, X, Y);
            }            
        }
        
        // si el algorisme mes costos supera el limit, es visualitza un missatge
        if (exceeded) {
            JOptionPane.showMessageDialog(null, 
                                  "It will not be drawn, but it is executing in background", 
                                   "n^2 algorithm exceeds the execution time limit",
                               JOptionPane.WARNING_MESSAGE);
        }
        updateProgressBar(lastPoint == N_PUNTS+1 ? 0 : lastPoint);
        
        try {
            Thread.sleep(MS_SLEEP);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshGrafica(VistaEvent event) {
        if (((event.type.equals(EventType.VECTORIAL) && event.time < TIME_LIMIT && !this.stopped) 
                || !event.type.equals(EventType.VECTORIAL)) && this.maxTime < event.time) {
            this.maxTime = event.time;
        }

        this.repaint();
    }
    
    public void reset() {
        this.maxTime = 1;
        this.currentPoint = 0;
        this.stopped = false;

        this.repaint();
    }
    
    private int transformX(int x) {
        return (x*this.getWidth())/N_PUNTS;
    }
    
    private int transformY(long y, long maxY) {
        return Math.abs((int) (y * this.getHeight()/ maxY) - this.getHeight());
    }
    
    private void updateProgressBar(int state) {
        if (state > this.currentPoint) {
            this.currentPoint = state;
        }
        bar.setValue((this.currentPoint * 100) / this.N_PUNTS);
    }
}
