package kaliandapet100;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import kaliandapet100.model.Ticket;
import kaliandapet100.model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Rog
 */
public class FormCheckout extends javax.swing.JFrame {

    /**
     * Creates new form Checkout
     */
    NumberFormat formatter = NumberFormat.getInstance(Locale.US);
    private String username;
    private Ticket currentTicket;
    private int subTotal;
    private int price;
    private int quantity;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    /**
     * Constructor that takes ticketId and username
     *
     * @param ticketId The ID of the ticket to checkout
     * @param username The username of the current user
     */
    public FormCheckout(String ticketId, String username) {
        initComponents();
        this.username = username;
        labelUsername.setText(username);

        // Fetch ticket data from server
        try {
            this.currentTicket = fetchTicketFromServer(ticketId);

            if (currentTicket != null) {
                txtID.setText(currentTicket.getId());
                labelTitle.setText(currentTicket.getTitle());
                labelPrice.setText("Rp. " + String.valueOf(formatter.format(currentTicket.getPrice())));
                // You could also display the description somewhere if needed
                // For example: add a label for description
            } else {
                JOptionPane.showMessageDialog(this,
                        "Unable to load ticket information.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                dispose(); // Close form if ticket can't be found
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error connecting to server: " + e.getMessage(),
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            dispose(); // Close form on error
        }
    }

    public FormCheckout() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Fetch ticket information from server
     *
     * @param ticketId The ID of the ticket to fetch
     * @return The Ticket object or null if not found
     */
    private Ticket fetchTicketFromServer(String ticketId) throws IOException, ClassNotFoundException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Request all tickets
            output.writeObject("GET_TICKETS");
            output.flush();

            // Get ticket list
            ArrayList<Ticket> tickets = (ArrayList<Ticket>) input.readObject();

            // Find the requested ticket
            for (Ticket ticket : tickets) {
                if (ticket.getId().equals(ticketId)) {
                    return ticket;
                }
            }

            return null;
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imageEvent = new javax.swing.JLabel();
        id_label = new javax.swing.JLabel();
        labelPrice = new javax.swing.JLabel();
        labelForm = new javax.swing.JLabel();
        labelQty = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        txtIQty = new java.awt.TextField();
        txtID = new java.awt.TextField();
        labelSubtotal = new javax.swing.JLabel();
        txtISubtotal = new java.awt.TextField();
        btnCheckOut = new javax.swing.JButton();
        btnCountSubtotal = new javax.swing.JButton();
        labelUsername = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(200, 169, 139));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageEvent.setBackground(new java.awt.Color(255, 255, 255));
        imageEvent.setForeground(new java.awt.Color(74, 52, 46));
        imageEvent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/brunoMars.jpg"))); // NOI18N
        jPanel1.add(imageEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 148, 220));

        id_label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        id_label.setForeground(new java.awt.Color(255, 255, 255));
        id_label.setText("ID : ");
        jPanel1.add(id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        labelPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelPrice.setForeground(new java.awt.Color(255, 255, 255));
        labelPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPrice.setText("a");
        jPanel1.add(labelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 150, -1));

        labelForm.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labelForm.setForeground(new java.awt.Color(255, 255, 255));
        labelForm.setText("Ticket Checkout");
        jPanel1.add(labelForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 280, -1));

        labelQty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelQty.setForeground(new java.awt.Color(255, 255, 255));
        labelQty.setText("Quantity :");
        jPanel1.add(labelQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, -1));

        labelTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Title : ");
        jPanel1.add(labelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 150, -1));

        txtIQty.setBackground(new java.awt.Color(245, 232, 212));
        txtIQty.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtIQty.setForeground(new java.awt.Color(74, 52, 46));
        jPanel1.add(txtIQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 170, -1));

        txtID.setBackground(new java.awt.Color(245, 232, 212));
        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtID.setForeground(new java.awt.Color(74, 52, 46));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 80, -1));

        labelSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelSubtotal.setForeground(new java.awt.Color(255, 255, 255));
        labelSubtotal.setText("Subtotal :");
        jPanel1.add(labelSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, -1, -1));

        txtISubtotal.setBackground(new java.awt.Color(245, 232, 212));
        txtISubtotal.setEditable(false);
        txtISubtotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtISubtotal.setForeground(new java.awt.Color(74, 52, 46));
        jPanel1.add(txtISubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 170, -1));

        btnCheckOut.setBackground(new java.awt.Color(0, 204, 153));
        btnCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCheckOut.setForeground(new java.awt.Color(255, 249, 240));
        btnCheckOut.setText("Check Out");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });
        jPanel1.add(btnCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 120, 38));

        btnCountSubtotal.setBackground(new java.awt.Color(228, 123, 46));
        btnCountSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCountSubtotal.setForeground(new java.awt.Color(255, 249, 240));
        btnCountSubtotal.setText("Count Subtotal");
        btnCountSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountSubtotalActionPerformed(evt);
            }
        });
        jPanel1.add(btnCountSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 140, 40));

        labelUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelUsername.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUsername.setText("Username");
        jPanel1.add(labelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 130, -1));

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 249, 240));
        jButton3.setText("<");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 38, 38));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 800, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCountSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountSubtotalActionPerformed
        try {
            quantity = Integer.parseInt(txtIQty.getText());

            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            output.writeObject("COUNT_SUBTOTAL");
            output.writeObject(currentTicket.getId());
            output.writeObject(quantity);
            output.flush();

            Object response = input.readObject();
            if (response instanceof Integer) {
                subTotal = (Integer) response;
                if (subTotal >= 0) {
                    txtISubtotal.setText("Rp. " + formatter.format(subTotal));
                } else {
                    JOptionPane.showMessageDialog(this, "Ticket not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid response from server.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            socket.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnCountSubtotalActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            output.writeObject("CHECKOUT_TICKET");
            output.writeObject(currentTicket.getId());
            output.writeObject(quantity);
            output.flush();

            Object response = input.readObject();

            if (response instanceof Integer) {
                int updatedStock = (Integer) response;
                if (updatedStock >= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Checkout berhasil!\nStock saat ini = " + updatedStock,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); // Tutup FormCheckout

                    // Refresh list ticket
                    FormListTicket listTicketForm = new FormListTicket();
                    listTicketForm.setVisible(true);
                } else if (updatedStock == -1) {
                    JOptionPane.showMessageDialog(this,
                            "Checkout gagal!\nStok tidak mencukupi.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Checkout gagal!\nTicket tidak ditemukan.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Checkout gagal! Respon tidak valid dari server.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            socket.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnCheckOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCheckout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCheckout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCheckout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCheckout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormCheckout().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnCountSubtotal;
    private javax.swing.JLabel id_label;
    private javax.swing.JLabel imageEvent;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelForm;
    private javax.swing.JLabel labelPrice;
    private javax.swing.JLabel labelQty;
    private javax.swing.JLabel labelSubtotal;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelUsername;
    private java.awt.TextField txtID;
    private java.awt.TextField txtIQty;
    private java.awt.TextField txtISubtotal;
    // End of variables declaration//GEN-END:variables
}
