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
    static private long maxTime = 1;
    private int currentPoint, n;
    static private int increaseRate = 10;
    private final int N_PUNTS = 50, MS_SLEEP = 200, TIME_LIMIT = 53341000;
    private JProgressBar bar;
    private boolean stopped;
    
    public Grafica(Main main, JProgressBar bar) {
        this.main = main;
        this.bar = bar;
        this.currentPoint = 0;
        this.stopped = false;
    }
    
    public void setN(int n) {
        this.n = n;
    }
    
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
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
        long temps = 0;

        for(EventType alg : algs){
            g2.setColor(alg.getColor());

            ArrayList<Long> times = llista.get(alg);
            
            for (int i = 0; i<times.size();i++) {
                int lastX = transformX(i * N_PUNTS);
                int lastY = transformY(i == 0 ? 0 : times.get(i-1), maxTime);

                int X = transformX((i+1) * N_PUNTS);
                temps = times.get(i);
                int Y = transformY(temps, maxTime);
                
                g2.drawLine(lastX, lastY, X, Y);
                /*if (temps > TIME_LIMIT) {
                    this.stopped = true;
                    break;
                }*/
            }            
        }
        
        //bar.setValue(this.currentPoint * 10);
        this.currentPoint++;
        try {
            Thread.sleep(MS_SLEEP);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refreshGrafica(VistaEvent event) {
        if (!this.stopped) {
            Grafica.maxTime = event.maxTime;
        }
        this.repaint();
    }
    
    public void reset() {
        Grafica.maxTime = 1;
        this.currentPoint = 0;
        this.stopped = false;
        this.repaint();
    }
    
    public int transformX(int x) {
        return (x*this.getWidth())/(this.n);
    }
    
    public int transformY(long y, long maxY) {
        return Math.abs((int) (y * this.getHeight()/ maxY) - this.getHeight());
    }
}
