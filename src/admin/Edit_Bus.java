package admin;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Edit_Bus extends javax.swing.JFrame {

    /**
     * Creates new form Add_Bus
     */
    public Edit_Bus() {
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        this.setTitle("CITY TRAVELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    String bus_no, new_bn, new_d_time, new_dt, from, to, date, d_time;
    int seats, price;
    
    public Edit_Bus(String bus_no, String from, String to, String date, String d_time, int seats, int price){
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        this.setTitle("CITY TRAVELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        this.bus_no = bus_no;
        this.from = from;
        this.to = to;
        this.date = date;
        this.d_time = d_time;
        this.seats = seats;
        this.price = price;
        
        setData();
    }
    
    private Connection makeCon() throws IOException{
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
        if (input != null) {
            props.load(input);
        } else {
            throw new FileNotFoundException("Property file not found");
        }

        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            return null;
        }
    }
    
    private void updateData(Connection con){
        try {
            String q = "update bus_schedule set bus_no=?, f=?, t=?, dt=?, departure_time=?, available_seats=?, price=? where bus_no=? and dt=? and departure_time=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, new_bn);
            ps.setString(2, from);
            ps.setString(3, to);
            ps.setString(4, new_dt);
            ps.setString(5, new_d_time);
            ps.setInt(6, seats);
            ps.setInt(7, price);
            
            ps.setString(8, bus_no);
            ps.setString(9, date);
            ps.setString(10, d_time);
            
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Edit_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setData(){
        txt_edit_from.setText(from);
        txt_edit_to.setText(to);
        txt_edit_time.setText(d_time);
        txt_edit_busno.setText(bus_no);
        txt_edit_seats.setText(String.valueOf(seats));
        txt_edit_price.setText(String.valueOf(price));
        StringTokenizer st = new StringTokenizer(date, "/");
        String[] s = {"", "", ""};
        int i = 0;
        while(st.hasMoreTokens()){
            s[i] = st.nextToken();
            i++;
        }
        edit_date.setSelectedItem(s[0]);
        edit_month.setSelectedItem(s[1]);
        edit_year.setSelectedItem(s[2]);
    }
    
    private void getData() throws IOException{
        from = txt_edit_from.getText();
        to = txt_edit_to.getText();
        new_bn = txt_edit_busno.getText();
        new_d_time = txt_edit_time.getText();
        String dt = edit_date.getSelectedItem().toString();
        String month = edit_month.getSelectedItem().toString();
        String year = edit_year.getSelectedItem().toString();
        new_dt = dt + "/" + month + "/" + year;
        if(from.isBlank()|| to.isBlank()|| new_bn.isBlank() || new_d_time.isBlank() || txt_edit_seats.getText().isBlank() || txt_edit_price.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Please fill required field!!");
        }
        else{
            seats = Integer.parseInt(txt_edit_seats.getText());
            price = Integer.parseInt(txt_edit_price.getText());
            int i = JOptionPane.showConfirmDialog(null, "Do you really want to update?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(i == 0){
                Connection con = makeCon();
                updateData(con);
                JOptionPane.showMessageDialog(null, "Bus details updated successfully!");
                dispose();
                EDelete_Bus edb = new EDelete_Bus();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_edit_from = new javax.swing.JTextField();
        txt_edit_to = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_edit_time = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_edit_busno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_edit_seats = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_edit_price = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        edit_back = new javax.swing.JButton();
        edit_date = new javax.swing.JComboBox<>();
        edit_month = new javax.swing.JComboBox<>();
        edit_year = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 1, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/edit_Img.png"))); // NOI18N
        jLabel1.setText(" Edit Bus");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 18, 835, 54));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("From :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 155, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("To :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 155, 37, -1));

        txt_edit_from.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        getContentPane().add(txt_edit_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 152, 229, -1));

        txt_edit_to.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        getContentPane().add(txt_edit_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 152, 224, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Date :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 252, 50, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Departurn Time :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 252, -1, -1));

        txt_edit_time.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        getContentPane().add(txt_edit_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 249, 135, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Bus No. :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 356, -1, -1));

        txt_edit_busno.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        getContentPane().add(txt_edit_busno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 353, 101, -1));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 353, 37, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("No. of seats :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 356, -1, -1));

        txt_edit_seats.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_edit_seats.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_edit_seatsKeyTyped(evt);
            }
        });
        getContentPane().add(txt_edit_seats, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 353, 95, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Price :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 356, -1, -1));

        txt_edit_price.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_edit_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_edit_priceKeyTyped(evt);
            }
        });
        getContentPane().add(txt_edit_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 353, 100, -1));

        btn_update.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 441, 106, -1));

        edit_back.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        edit_back.setText("Back");
        edit_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_backActionPerformed(evt);
            }
        });
        getContentPane().add(edit_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 441, 101, -1));

        edit_date.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        edit_date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        edit_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_dateActionPerformed(evt);
            }
        });
        getContentPane().add(edit_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 249, 60, -1));

        edit_month.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        edit_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        getContentPane().add(edit_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 249, -1, -1));

        edit_year.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        edit_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025" }));
        getContentPane().add(edit_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 249, -1, -1));

        jPanel1.setBackground(new java.awt.Color(238, 78, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 837, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 837, 90));

        jPanel2.setBackground(new java.awt.Color(252, 237, 218));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 837, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 837, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edit_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_dateActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        try {
            getData();
        } catch (IOException ex) {
            Logger.getLogger(Edit_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void edit_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_backActionPerformed
        // TODO add your handling code here:
        dispose();
        EDelete_Bus edb = new EDelete_Bus();
    }//GEN-LAST:event_edit_backActionPerformed

    private void txt_edit_seatsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_edit_seatsKeyTyped
        // TODO add your handling code here:
        char TestChar = evt.getKeyChar();
        if(!(Character.isDigit(TestChar))){
            evt.consume();
        }
    }//GEN-LAST:event_txt_edit_seatsKeyTyped

    private void txt_edit_priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_edit_priceKeyTyped
        // TODO add your handling code here:
        char TestChar = evt.getKeyChar();
        if(!(Character.isDigit(TestChar))){
            evt.consume();
        }
    }//GEN-LAST:event_txt_edit_priceKeyTyped

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
            java.util.logging.Logger.getLogger(Edit_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edit_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edit_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit_Bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Edit_Bus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_update;
    private javax.swing.JButton edit_back;
    private javax.swing.JComboBox<String> edit_date;
    private javax.swing.JComboBox<String> edit_month;
    private javax.swing.JComboBox<String> edit_year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_edit_busno;
    private javax.swing.JTextField txt_edit_from;
    private javax.swing.JTextField txt_edit_price;
    private javax.swing.JTextField txt_edit_seats;
    private javax.swing.JTextField txt_edit_time;
    private javax.swing.JTextField txt_edit_to;
    // End of variables declaration//GEN-END:variables
}
