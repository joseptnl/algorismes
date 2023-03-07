package practica1.vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;
import practica1.EventType;
import practica1.Main;

/**
 *
 * @author usuario
 */
public class Grafica extends JPanel {
    private BufferedImage bima;
    private Main main;
    private int n;
    private int firstX, firstY;
    private long maxTime = 1;
    
    public Grafica(Main main) {
        this.main = main;
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
        
        /*
        int width = this.getWidth();
        int height = this.getHeight();
        
        gr.setColor(new Color(230,230,230));
        gr.fillRect(0, 0, width, height);
        super.paint(gr);

        gr.drawLine(0, 0, 0, height);
        gr.drawLine(0, height-1, width, height-1);
        */
        
        Graphics2D g2 = (Graphics2D) gr;
        g2.setStroke(new BasicStroke(2F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Set<EventType> algs = llista.keySet();

        for(EventType alg : algs){
            g2.setColor(alg.getColor());

            ArrayList<Long> times = llista.get(alg);

            for(int i = 0; i<times.size();i++){
                int lastX = transformX(i * 10);
                int lastY = transformY(i == 0 ? 0 : times.get(i -1), maxTime);

                int X = transformX((i+1) * 10);
                int Y = transformY(times.get(i), maxTime);
                
                g2.drawLine(lastX, lastY, X, Y);
            }
        }

    }
    
    public void refresGrafica(VistaEvent event) {
        this.maxTime = event.maxTime;
        this.repaint();
        /*int x = transformX(event.iteration);
        int y = transformY(event.time, event.maxTime);
        
        ConcurrentHashMap<EventType, ArrayList<Long>> llista = main.getModel().getTimes();

        
        ArrayList<Long> times = llista.get(event.type);
        if (event.type == EventType.ARRAY) {
            System.out.println("ARRAY - Iteration: "+event.iteration+". Time: "+event.time+"\tX : " + x + " Y : " + y);
        }
        if (event.type == EventType.HASH) {
            System.out.println("HASH - Iteration: "+event.iteration+". Time: "+event.time+"\tX : " + x + " Y : " + y);
        }
        if (event.type == EventType.VECTORIAL) {
            System.out.println("VECTORIAL - Iteration: "+event.iteration+". Time: "+event.time+"\tX : " + x + " Y : " + y);
        }*/
    }

    
    public int transformX(int x) {
        //return ((x-2)/(this.n-2)*100)*this.getWidth()/100;
        return ((x-2)*this.getWidth())/(this.n-2);
    }
    
    public int transformY(long y, long maxY) {
        //return Math.abs((int) (y * 417/ maxY) - 417);
        long p1 = y * this.getHeight();
        int val = (int) (p1/maxY);
        int result = this.getHeight() - val;
        return result;
        //return this.getHeight() - ((int) (y * this.getHeight()/ maxY));
    }
}
