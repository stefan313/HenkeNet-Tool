import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.*;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nick
 */
public class TransactionHistoryView extends javax.swing.JFrame {
    private MainControl parentControl;
    private User currentUser;
    private DataLink dataSource;
    private final static Logger LOG = Logger.getLogger("*");
    
    /**
     * Creates new form TransactionHistory
     */
    public TransactionHistoryView(DataLink dataLink, User user) {
        this.dataSource = dataLink;
        this.currentUser = user;
        initComponents();
        
        initTransactionTable(dataSource.lookupTransactions(currentUser));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTransactionHistory = new javax.swing.JTable();

        jTableTransactionHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Operator", "Cash Balance", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTransactionHistory);
        if (jTableTransactionHistory.getColumnModel().getColumnCount() > 0) {
            jTableTransactionHistory.getColumnModel().getColumn(0).setResizable(false);
            jTableTransactionHistory.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTableTransactionHistory.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableTransactionHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableTransactionHistory.getColumnModel().getColumn(3).setResizable(false);
            jTableTransactionHistory.getColumnModel().getColumn(3).setPreferredWidth(450);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTransactionHistory;
    // End of variables declaration//GEN-END:variables

    private void initTransactionTable(List<Transaction> list) {
        DefaultTableModel model = (DefaultTableModel)jTableTransactionHistory.getModel();
        for (int i = 0; i < model.getRowCount(); i++) model.removeRow(i);
        DecimalFormat df = new DecimalFormat("0.00"); 
        for (Transaction t : list) {
            String amount_paid = df.format(t.getAmountPaid()/100)+ " €";
            model.addRow(new Object[]{t.getDate(), t.getDbUser(), amount_paid, t.getDescription()});
        }
    }
}
