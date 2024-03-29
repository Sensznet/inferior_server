package GUI;


import Threads.MonsterThread;
import Servers.VerteilerMain;
import Servers.VerteilerMonster;
import Servers.VerteilerLogin;
import Servers.VerteilerUpdater;
import Servers.VerteilerChat;
import Threads.WorldThread;

/*
 * Oberflaeche.java
 *
 * Created on 21. Januar 2009, 18:10
 */



/**
 *
 * @author  Derok
 */
public class Oberflaeche extends javax.swing.JFrame{
    private final VerteilerMain vtm;
    private final VerteilerLogin vtl;
    private final VerteilerChat vtc;
    private final VerteilerUpdater vtu;
    private final VerteilerMonster vtmob;
    private final OnlineChecker checker;
    private final MonsterThread MT;
    private final WorldThread WT;
    private int p=1;
    public Oberflaeche() {
        initComponents();
        MT = new MonsterThread();
        WT = new WorldThread();
        vtm = new VerteilerMain(3114, this);
        vtl = new VerteilerLogin(3115, this);
        vtc = new VerteilerChat(3116, this);
        vtu = new VerteilerUpdater(3117, this);
        vtmob = new VerteilerMonster(3118, this);
        checker = new OnlineChecker(this);
    }
    public void setText(String a)
    {
        jTextArea1.append(a);
        jTextArea1.append("\n");
    }
    public void setText2(String a, String b, String c)
    {
        TAPlayers.append(a);
        TAPlayers.append("\t");
        TAPlayers.append(b);
        TAPlayers.append("\t");
        TAPlayers.append(c);
        TAPlayers.append("\n");
    }
    public void resetText2()
    {
        TAPlayers.setText("");
    }
    
    public void setTextMonsters(String a, String b, String c) {
        TAMonsters.append(a);
        TAMonsters.append("\t");
        TAMonsters.append(b);
        TAMonsters.append("\t");
        TAMonsters.append(c);
        TAMonsters.append("\n");
    }
    
    public void resetTextMonsters()
    {
        TAMonsters.setText("");
    }
    /** Creates new form Oberflaeche */
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        btsbeenden = new javax.swing.JButton();
        btsstart = new javax.swing.JButton();
        btsneustart = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TAPlayers = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TAMonsters = new javax.swing.JTextArea();

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel3.setText("Members Online:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btsbeenden.setText("Server Beenden");
        btsbeenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsbeendenActionPerformed(evt);
            }
        });

        btsstart.setText("Server Starten");
        btsstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsstartActionPerformed(evt);
            }
        });

        btsneustart.setText("Server Neustarten");
        btsneustart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsneustartActionPerformed(evt);
            }
        });

        TAPlayers.setColumns(20);
        TAPlayers.setRows(5);
        jScrollPane2.setViewportView(TAPlayers);

        jLabel1.setText("Players Online:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Allgemein:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setText("Monsters:");

        TAMonsters.setColumns(20);
        TAMonsters.setRows(5);
        jScrollPane4.setViewportView(TAMonsters);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btsstart)
                                .addGap(18, 18, 18)
                                .addComponent(btsneustart)
                                .addGap(18, 18, 18)
                                .addComponent(btsbeenden))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsstart)
                    .addComponent(btsneustart)
                    .addComponent(btsbeenden))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btsbeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsbeendenActionPerformed
    System.exit(0);
}//GEN-LAST:event_btsbeendenActionPerformed

private void btsstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsstartActionPerformed
    MT.start();
    WT.start();
    vtm.start();
    vtl.start();
    vtc.start();
    vtu.start();
    vtmob.start();
    jTextArea1.setText("Listening on port " + vtm.getPort() + ", " + 
                vtl.getPort() + ", " + vtc.getPort() + ", " 
                + vtu.getPort()+ ", " + vtmob.getPort());
    jTextArea1.append("\n");
}//GEN-LAST:event_btsstartActionPerformed

private void btsneustartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsneustartActionPerformed
    
}//GEN-LAST:event_btsneustartActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Oberflaeche().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TAMonsters;
    public javax.swing.JTextArea TAPlayers;
    private javax.swing.JButton btsbeenden;
    private javax.swing.JButton btsneustart;
    private javax.swing.JButton btsstart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables

}
