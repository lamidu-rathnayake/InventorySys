/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JujubesInventoryManager.Frontend;
import JujubesInventoryManager.Backend.SQLManagerNew;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.SqlUpdateManagerTransaction;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.Transaction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class ChangeTransactionInfo extends javax.swing.JFrame{
    SqlUpdateManagerTransaction updateManager;
    Transaction transaction;
    List<Integer> stockIds;
    List<Integer> quantities;
    List<Double> amounts;
    
    /**
     * Creates new form changeTransactionInfo
     */
    public ChangeTransactionInfo() {
        initComponents();
        Image icon = new ImageIcon(this.getClass().getResource("letter-j.png")).getImage();    
        setIconImage(icon);
        
        updateManager = new SqlUpdateManagerTransaction();
        
        //setting the background color and border color for the cart table
        jScrollPane1.getViewport().setBackground(new java.awt.Color(34, 40, 49));
        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBorder(null);
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("stock");
        model.addColumn("quantity");
        model.addColumn("amount");
        JTableHeader header1 = jTable1.getTableHeader();
        header1.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(new java.awt.Color(118,171,174)); // Set your desired header background color
                return this;
            }
        });
        // Create a border for the table
        Border border2 = BorderFactory.createLineBorder(new java.awt.Color(118,171,174));
        // Set the border for the table
        jTable1.setBorder(border2);
        jProgressBar1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     *  
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextField28 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        jLabel1.setText("jLabel1");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Transaction Infomation");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBackground(new java.awt.Color(41, 49, 60));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Change transaction info");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 0);
        jPanel4.add(jLabel13, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(34, 40, 49));

        jButton5.setText("refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("transaction id ");

        jTextField16.setBackground(new java.awt.Color(34, 40, 49));
        jTextField16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(255, 255, 255));
        jTextField16.setCaretColor(null);

        jButton6.setText("search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setText("get last transaction");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17))
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton9))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 0);
        jPanel4.add(jPanel5, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(34, 40, 49));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(41, 49, 60));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(41, 49, 60));

        jTextField4.setBackground(new java.awt.Color(34, 40, 49));
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setCaretColor(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stock ID");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity");

        jTextField5.setBackground(new java.awt.Color(34, 40, 49));
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setCaretColor(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(41, 49, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total amount :");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Rs 0.0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Items :");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(" ");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 40, 49)));

        jProgressBar1.setForeground(new java.awt.Color(0, 255, 51));

        jLabel9.setBackground(new java.awt.Color(34, 40, 49));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 169;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 20, 30, 30);
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(34, 40, 49));

        jTextField28.setBackground(new java.awt.Color(34, 40, 49));
        jTextField28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField28.setForeground(new java.awt.Color(255, 255, 255));
        jTextField28.setCaretColor(null);

        jTextField17.setBackground(new java.awt.Color(34, 40, 49));
        jTextField17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(255, 255, 255));
        jTextField17.setCaretColor(null);

        jTextField29.setBackground(new java.awt.Color(34, 40, 49));
        jTextField29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField29.setForeground(new java.awt.Color(255, 255, 255));
        jTextField29.setCaretColor(null);

        jTextField18.setBackground(new java.awt.Color(34, 40, 49));
        jTextField18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(255, 255, 255));
        jTextField18.setCaretColor(null);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("customer contact");

        jButton7.setText("submit chaneges");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField30.setBackground(new java.awt.Color(34, 40, 49));
        jTextField30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField30.setForeground(new java.awt.Color(255, 255, 255));
        jTextField30.setCaretColor(null);

        jButton8.setText("cancel changes");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("customer e-mail");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("selling date");

        jTextField15.setBackground(new java.awt.Color(34, 40, 49));
        jTextField15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));
        jTextField15.setCaretColor(null);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("customer name");

        jTextField24.setBackground(new java.awt.Color(34, 40, 49));
        jTextField24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField24.setForeground(new java.awt.Color(255, 255, 255));
        jTextField24.setCaretColor(null);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("customer address");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 30, 30, 0);
        jPanel4.add(jPanel7, gridBagConstraints);

        getContentPane().add(jPanel4);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    search (manual)
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // SEARCH EVENT
        try{
            if(SQLManagerNew.getLastTid()>0 && Integer.parseInt(jTextField16.getText())>0){
                updateManager.initializeTransaction(Integer.parseInt(jTextField16.getText()));
                transaction = updateManager.getTransactionObj();
                if (transaction!=null){
                    jLabel8.setText("");
                    initializeArrays();
                    initializeTextFields();
                    initializeTable();
                    initializeLabels();
                } else{
                    jLabel8.setForeground(Color.RED);
                    jLabel8.setText("transaction id is not exist");
                }
            } else{
                jLabel8.setForeground(Color.RED);
                jLabel8.setText("no transactions exist");
            }   
        } catch(NumberFormatException ex){
            jLabel8.setForeground(Color.RED);
            jLabel8.setText("Invalid transaction id");
        } catch(Exception ex){
            jLabel8.setForeground(Color.RED);
            jLabel8.setText(ex.getMessage());
        } 
    }//GEN-LAST:event_jButton6ActionPerformed

//    search (last)
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // SEARCH LAST TRANSACTION EVENT
        try{
            if(SQLManagerNew.getLastTid()>0){
                updateManager.initializeTransaction(SQLManagerNew.getLastTid());
                transaction = updateManager.getTransactionObj();
                if (transaction!=null){
                    jLabel8.setText("");
                    initializeArrays();
                    initializeTextFields();
                    initializeTable();
                    initializeLabels();
                }
                else{
                    jLabel8.setForeground(Color.RED);
                    jLabel8.setText("transaction id is not exist");
                }
                //text field 
                jTextField16.setText(String.valueOf(updateManager.getTransactionId()));
            }
            else{
                jLabel8.setForeground(Color.RED);
                jLabel8.setText("no transactions exist");
            }
        }
        catch(Exception ex){
            jLabel8.setForeground(Color.RED);
            jLabel8.setText(ex.getMessage());
        } 
    }//GEN-LAST:event_jButton9ActionPerformed

//    refresh
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton5ActionPerformed

//    add items
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // add stock and quantity
        try{
            if(validateQuantity()==1){
                double amount = updateManager.getSellPrice(Integer.parseInt(jTextField4.getText()))*Integer.parseInt(jTextField5.getText());
                stockIds.add(Integer.parseInt(jTextField4.getText()));
                quantities.add(Integer.parseInt(jTextField5.getText()));
                amounts.add(amount);
                jLabel8.setText("");
                updateTable();
                initializeLabels();
            }
        }
        catch(NumberFormatException ex){
            jLabel8.setForeground(Color.RED);
            jLabel8.setText("Invalid stock_id or quantity");
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
            jLabel8.setForeground(Color.RED);
            jLabel8.setText("Invalid action before initialization");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            jLabel8.setForeground(Color.RED);
            jLabel8.setText("Invalid action before initialization");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed
//    remove items
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // remove last stock id and quantity
        try{
            if(stockIds.size()>0 && quantities.size()>0 && amounts.size()>0){
                int[] selectedRows = jTable1.getSelectedRows();
                if(selectedRows.length>0){
                    int removedItemsCount=0;
                    for(int row : selectedRows){
                        stockIds.remove(row-removedItemsCount);
                        quantities.remove(row-removedItemsCount);
                        amounts.remove(row-removedItemsCount);
                        removedItemsCount++;
                    }
                    updateTable();
                    initializeLabels();
                }else{
                    stockIds.removeLast();
                    quantities.removeLast();
                    amounts.removeLast();
                    
                    updateTable();
                    initializeLabels();
                }
            }
            else{
                jLabel8.setForeground(Color.RED);
                jLabel8.setText("Table is empty");
            }
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
            jLabel8.setForeground(Color.RED);
            jLabel8.setText("Invalid action before initialization");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            jLabel8.setForeground(Color.RED);
            jLabel8.setText("Invalid action before initialization");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

//    submit
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        submit();
    }//GEN-LAST:event_jButton7ActionPerformed

//    cancel changes
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cancelChanges();
    }//GEN-LAST:event_jButton8ActionPerformed

  
    //initializing text fields and labels
    private void initializeTextFields(){
        jTextField24.setText(updateManager.getCname());
        jTextField28.setText(updateManager.getCaddress());
        jTextField29.setText(String.valueOf(updateManager.getCcontact()));
        jTextField30.setText(updateManager.getCemail());
        
        //setting up date
        String date = updateManager.getTdate();
        String[] ymd = date.split("-");
        jTextField15.setText(ymd[0]);
        jTextField17.setText(ymd[1]);
        jTextField18.setText(ymd[2]);
    }
    
    //calculate total quantities
    private void initializeLabels(){
        int items = 0;
        double totalAmount = 0;
        int i = 0;
        for(int quantity : quantities){
            items += quantity;
            totalAmount += amounts.get(i);
            i++;
        }
        jLabel3.setText("Rs " + String.valueOf(totalAmount));
        jLabel7.setText(String.valueOf(items));
    }
    
    //TABLE FUNCTIONALITIES
    private void initializeTable(){
        Thread newThread = new Thread(()->{
            jProgressBar1.setVisible(true);
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
            jTable1.setModel(model);
            model.addColumn("stock");
            model.addColumn("quantity");
            model.addColumn("amount");
            
            int progressU = 90/stockIds.size();
            int progressC = 10; 
            for(int i=0; i<stockIds.size(); i++){
                model.addRow(new Object[]{stockIds.get(i),quantities.get(i),amounts.get(i)});
                progressC += progressU;
                jProgressBar1.setValue(progressC);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TransactionHistoryViewer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println(ex.getMessage());
                    jLabel8.setText("Invalid action is encounted");
                }
            }
            jProgressBar1.setVisible(false);
        });
        newThread.start();
    }
    private void updateTable(){
        Thread newThread = new Thread(()-> {
            try{
                jProgressBar1.setVisible(true);
                javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
                jTable1.setModel(model);
                model.addColumn("stock");
                model.addColumn("quantity");
                model.addColumn("amount");

                if(stockIds==null && quantities==null && amounts==null){
                    jLabel8.setText("Invalid action before initialization");
                }else{
                    int progressU = 90/stockIds.size();
                    int progressC = 10;
                    for(int i=0; i<stockIds.size(); i++){
                        model.addRow(new Object[]{stockIds.get(i),quantities.get(i),amounts.get(i)});
                        progressC += progressU;
                        jProgressBar1.setValue(progressC);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TransactionHistoryViewer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ArrayIndexOutOfBoundsException ex){
                            System.out.println(ex.getMessage());
                            jLabel8.setText("Invalid action is encounted");
                        } 
                    }
                }
            } catch (ArithmeticException e){
                jLabel8.setText("");
            } catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                jLabel8.setText("Invalid action is encounted");
            }
            jProgressBar1.setVisible(false);  
        });
        newThread.start();
    }
    
    //ARRAYS
    private void initializeArrays(){
        stockIds = updateManager.getSids();
        quantities = updateManager.getSQuantities();
        amounts = updateManager.getAmounts();
    }
   
    //REFRESH
    private void refresh(){
        //clean textfields
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField24.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
        jTextField30.setText("");
        
        //clean labels
        jLabel3.setText("");
        jLabel7.setText("");
        jLabel8.setText("");
        
        //clean tables
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("stock");
        model.addColumn("quantity");
        
        //clean arrays
        stockIds = null;
        quantities = null;
        amounts = null;
        
        //clean objects
        updateManager.resetObjects();
    }
    
    //submit changes
    private void submit(){
        //upadte objects
        try{
            long contact = Long.parseLong(jTextField29.getText());
            
            //updating
            String date = jTextField15.getText() + "-" + jTextField17.getText() + "-" + jTextField18.getText();
            updateManager.changeDateOfTransaction(date);
            updateManager.changeCustomerInfo(jTextField24.getText(),contact, jTextField28.getText(), jTextField30.getText());       
            updateManager.changeStockAndQuantities(stockIds, quantities, amounts);

            //submiting
            updateManager.updateTransactionTable();
            updateManager.updateCustomerTable();
            updateManager.updateTransactionItemTable();

            updateManager.resetObjects();
            refresh();
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
            jLabel8.setText("Invalid action before initialization");
        }
        catch(NumberFormatException ex){
            jLabel8.setText("invalid contact number");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            jLabel8.setText("invalid action");
        }
    }
    
    //cancel changes
    private void cancelChanges(){
        try{
            updateManager.initializeTransaction(updateManager.getTransactionId());
            jLabel8.setText("");
            initializeArrays();
            initializeTextFields();
            initializeTable();
            initializeLabels();
            
            //text field 
            jTextField16.setText(String.valueOf(updateManager.getTransactionId()));
        }
        catch(NumberFormatException ex){
            jLabel8.setText("Invalid user input");
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
            jLabel8.setText("Invalid action before initialization");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            jLabel8.setText("invalid action");
        }
    }
    
    
    
    private int validateQuantity() {
        try {
            int result;
            String stockIdStr = jTextField4.getText();
            boolean sidStatus = stockIdStr.isEmpty();

            if (!sidStatus) {  
                int stockId = Integer.parseInt(stockIdStr);
                int quantity = Integer.parseInt(jTextField5.getText());
                
                //cheking if the user has already reached the available items in inventory
                int totalStockQuantity = 0;
                totalStockQuantity += quantity;
                
                // adding the quantities that already added to the card with the same stock id
                int index = 0;
                for(int sid : stockIds){
                    if(sid == stockId){
                        totalStockQuantity += quantities.get(index);
                    } 
                    index++;
                }
                result = SQLManagerNew.isItemAvailable(stockId, totalStockQuantity);
                
                switch (result) {
                    case 1:
                        jLabel8.setText("");
                        return result;
                    case -1:
                        jLabel8.setText("Out of limit");
                        return -1;
                    case -2:
                        jLabel8.setText("Stock id doesn't exist");
                        return -1;
                    default:
                        return -1;
                }
            } 
            else {
                jLabel8.setText("Enter the stock ID");
                return -1;
            }
        } 
        catch (NumberFormatException exc) {
            System.out.println(exc.getMessage());
            jLabel8.setText("Invalid stock ID or quantity"); //for invalid number
            return -1;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            jLabel8.setText("invalid action");
            return -1;
        }
}
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
