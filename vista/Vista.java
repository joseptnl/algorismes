package practica1.vista;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import practica1.Event;
import practica1.EventListener;
import practica1.EventType;
import practica1.Main;
import practica1.control.ControlEvent;
import practica1.model.ModelEvent;

/**
 *
 * @author usuario
 */
public class Vista extends javax.swing.JFrame implements EventListener {
    private Main main;
    private List<EventType> alg;
    private int n;

    /**
     * Creates new form Vista
     */
    public Vista(Main main) {
        this.main = main;
        initComponents();
        this.alg = new ArrayList<EventType>();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.n = 1000;
        this.setTitle("Asymptotic complexity");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bar = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelGrafica = new Grafica(this.main, this.bar);
        jPanel2 = new javax.swing.JPanel();
        buttonArray = new javax.swing.JButton();
        buttonHash = new javax.swing.JButton();
        buttonProducte = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        buttonStart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nNumber = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bar.setForeground(new java.awt.Color(51, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel5.setText("N");

        jLabel6.setText("Time");

        panelGrafica.setBackground(new java.awt.Color(230, 230, 230));

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel5)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        buttonArray.setBackground(new java.awt.Color(0, 102, 102));
        buttonArray.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        buttonArray.setForeground(new java.awt.Color(255, 255, 255));
        buttonArray.setText("n*log(n)");
        buttonArray.setBorderPainted(false);
        buttonArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonArrayActionPerformed(evt);
            }
        });

        buttonHash.setBackground(new java.awt.Color(0, 102, 102));
        buttonHash.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        buttonHash.setForeground(new java.awt.Color(255, 255, 255));
        buttonHash.setText("n");
        buttonHash.setBorderPainted(false);
        buttonHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHashActionPerformed(evt);
            }
        });

        buttonProducte.setBackground(new java.awt.Color(0, 102, 102));
        buttonProducte.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        buttonProducte.setForeground(new java.awt.Color(255, 255, 255));
        buttonProducte.setText("n²");
        buttonProducte.setBorderPainted(false);
        buttonProducte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProducteActionPerformed(evt);
            }
        });

        buttonReset.setBackground(new java.awt.Color(0, 102, 102));
        buttonReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonReset.setText("RESET");
        buttonReset.setBorderPainted(false);
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        buttonStart.setBackground(new java.awt.Color(0, 102, 102));
        buttonStart.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonStart.setForeground(new java.awt.Color(255, 255, 255));
        buttonStart.setText("START");
        buttonStart.setBorderPainted(false);
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("N");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ALGORITHMS");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Click to draw");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Click to remove draw");

        nNumber.setValue(1000);
        nNumber.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nNumberStateChanged(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Insert & Enter :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonProducte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonArray, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonHash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(nNumber)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHash, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonArray, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonProducte, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(buttonStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReset))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonArrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonArrayActionPerformed
        if (!this.alg.contains(EventType.ARRAY)) {
            this.alg.add(EventType.ARRAY);
            buttonArray.setBackground(new Color(0,102,102).darker());
        }
    }//GEN-LAST:event_buttonArrayActionPerformed

    private void buttonHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHashActionPerformed
        if (!this.alg.contains(EventType.HASH)) {
            this.alg.add(EventType.HASH);
            buttonHash.setBackground(new Color(0,102,102).darker());
        }
    }//GEN-LAST:event_buttonHashActionPerformed

    private void buttonProducteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProducteActionPerformed
        if (!this.alg.contains(EventType.VECTORIAL)) {
            this.alg.add(EventType.VECTORIAL);
            buttonProducte.setBackground(new Color(0,102,102).darker());
        }
    }//GEN-LAST:event_buttonProducteActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        buttonProducte.setBackground(new Color(0,102,102));
        buttonArray.setBackground(new Color(0,102,102));
        buttonHash.setBackground(new Color(0,102,102));
        buttonStart.setEnabled(true);
        this.main.reset();
        this.alg.clear();
        panelGrafica.reset();
    }//GEN-LAST:event_buttonResetActionPerformed

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
       if (!this.alg.isEmpty()) {
           buttonStart.setEnabled(false);
           panelGrafica.setN(this.n);
           this.main.notify(new ModelEvent(this.n));
           this.main.notify(new ControlEvent(this.alg, true));
       }
    }//GEN-LAST:event_buttonStartActionPerformed

    private void nNumberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nNumberStateChanged
        // Canviam la n màxima per fer els càlculs
        int number = (int) nNumber.getValue();
        if (number < 10) {
            this.n = 10;
            nNumber.setValue(10);
        } else {
            this.n = number;
        }
    }//GEN-LAST:event_nNumberStateChanged


    @Override
    public void notify(Event e) {
        VistaEvent event = (VistaEvent) e;
        
        System.out.println(event.type.toString() +" TIME : " + event.time);
        panelGrafica.refreshGrafica(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JButton buttonArray;
    private javax.swing.JButton buttonHash;
    private javax.swing.JButton buttonProducte;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner nNumber;
    /*
    private javax.swing.JPanel panelGrafica;
    */
    Grafica panelGrafica;
    // End of variables declaration//GEN-END:variables
}
