package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cancellation extends javax.swing.JFrame {

    private static String username;
    private static String path;
    private static String []str_parts = {"", "", "", "", "", ""};
    /**
     * Creates new form Cancellation
     */
    
    Connection con;
    
    public Cancellation(String username) {
        initComponents();
        
        this.username = username;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Bus-Favicon.png")));
        this.setTitle("CITY TRAVELS");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);       
        
        TableHeader();
        setTableValue();
        try {
            con = makeCon();
        } catch (IOException ex) {
            Logger.getLogger(Cancellation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txt_cancel_from.setEditable(false);
        txt_cancel_to.setEditable(false);
        txt_cancel_busno.setEditable(false);
        txt_cancel_time.setEditable(false);
  
    }
    
    public Cancellation() {
        initComponents();
        
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
    
    private void TableHeader()
    {
        JTableHeader thead = tbl_cancel.getTableHeader();
        thead.setBackground(Color.BLACK);
        thead.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        TableColumn col1 = tbl_cancel.getColumnModel().getColumn(0);
        col1.setPreferredWidth(70);
        TableColumn col2 = tbl_cancel.getColumnModel().getColumn(1);
        col2.setPreferredWidth(127);
        TableColumn col3 = tbl_cancel.getColumnModel().getColumn(2);
        col3.setPreferredWidth(127);
        TableColumn col4 = tbl_cancel.getColumnModel().getColumn(3);
        col4.setPreferredWidth(90);
        TableColumn col5 = tbl_cancel.getColumnModel().getColumn(4);
        col5.setPreferredWidth(120);
        TableColumn col6 = tbl_cancel.getColumnModel().getColumn(5);
        col6.setPreferredWidth(101);
        TableColumn col7 = tbl_cancel.getColumnModel().getColumn(6);
        col7.setPreferredWidth(128);
        TableColumn col8 = tbl_cancel.getColumnModel().getColumn(7);
        col8.setPreferredWidth(91);
        TableColumn col9 = tbl_cancel.getColumnModel().getColumn(8);
        col9.setPreferredWidth(84);
        
    }
    
    
    private static String recursion(String fna, File ff, String []insideFile)
    {
	if(ff.isDirectory())
	{
            int j;
            insideFile = ff.list();
			
            for(j = 0; j<insideFile.length; j++)
            {
		if(fna.equals(insideFile[j]))
		{
                    System.out.print("\n File Path inner : "+ff.getAbsolutePath());
                    path = ff.getAbsolutePath();
                    return path;
                    //System.exit(0);
		}
		else 
		{
                    File ff2 = new File(ff,insideFile[j]);
                    if(ff2.isDirectory())
                    {
			path = recursion(fna, ff2, insideFile);
                    }
		}
            }
	}
        return path;
    }
    
    public void setTableValue()
    {
        try
        {
            FileReader fr = new FileReader("c:\\City Bus\\User Bookings\\User_"+username+"\\BookingTimes.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            String data = "";
            
            while(line != null)
            {
                line = br.readLine();
                
                if(line != null)
                {
                    data = line;
                    
                    String file = "Time_"+data+".txt";
                    
                    // TO FIND OUT FILE PROCCESS...
                    try {
                        int i;
			File f1 = new File("c:\\City Bus\\User Bookings\\User_"+username);
		
			String[] mainFile = f1.list();
			String[] insideFile = null;
		
			for(i = 0; i<mainFile.length; i++)
			{
                            File ff = new File(f1, mainFile[i]);
				
                            /*if(file.equals(mainFile[i]))
                            {
				
                                path = ""+ff.getAbsolutePath();
                                //System.out.print("\n File Path : "+path);
				break;
                            }*/
			
                            try {
				if(ff.isDirectory())
				{
                                    path = recursion(file, ff, insideFile);
                                }
                            }
                            catch(Exception e) {
				System.out.print("\n RECURSION ERROR : "+e.getMessage());
                            }
			}
                    }
                    catch(Exception e) {
			System.out.print("\n ERROR 404 : "+e.getMessage());
                    }
                    
                    
                    StringTokenizer st1 = new StringTokenizer(path,"\\" );
                    
                    int i = -1;
                    while(st1.hasMoreTokens())
                    {
                        i++;
                        str_parts[i] = st1.nextToken();
                        System.out.print("\n Data : "+str_parts[i]);
                    }
                        
                    String []info = {"", "", "", "", "", "", "", "", ""};
                    try {
                    FileReader fread = new FileReader(""+str_parts[0]+"\\"+str_parts[1]+"\\"+str_parts[2]+"\\"+str_parts[3]+"\\"+str_parts[4]+"\\"+str_parts[5]+"\\Time_"+data+".txt");
                    BufferedReader bb = new BufferedReader(fread);
                    
                    int k = -1;
                    String s = "";
                    
                    while(s != null)
                    {
                        s = bb.readLine();
                        
                        if(s != null)
                        {
                            k++;
                            info[k] = s; 
                        }
                    }
                    bb.close();
                    
                    }
                    catch (Exception e)
                    {
                        System.out.print("\n READING DATA FROM IN PARTS ERROR : "+e.getMessage());
                    }
                    
                    
                    String []tableData = {info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8]};
                    
                    DefaultTableModel model = (DefaultTableModel) tbl_cancel.getModel();
                    model.addRow(tableData);
                                                           
                }           
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.print("\n DATA COPYING ERROR : "+e.getMessage());
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
        txt_cancel_busno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_cancel_to = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_cancel = new javax.swing.JButton();
        cancel_back = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_cancel_time = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_cancel = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txt_cancel_from = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_cancel_seats = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 1, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/cancel_icon(56x56).png"))); // NOI18N
        jLabel1.setText(" Cancellation");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 23, 1000, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Bus No. :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 229, -1, -1));

        txt_cancel_busno.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_cancel_busno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 229, 80, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("To :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, -1, -1));

        txt_cancel_to.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_cancel_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 250, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 226, 37, -1));

        btn_cancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 564, 92, -1));

        cancel_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancel_back.setText("Back");
        cancel_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_backActionPerformed(evt);
            }
        });
        getContentPane().add(cancel_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 564, 100, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("No. Of Seats :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 229, -1, -1));

        txt_cancel_time.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_cancel_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cancel_timeActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cancel_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 229, 132, -1));

        tbl_cancel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbl_cancel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus No.", "From", "To", "Date", "Departure Time", "Booked Date", "Time of Booking", "No of Seats", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_cancel.setRowHeight(25);
        tbl_cancel.getTableHeader().setReorderingAllowed(false);
        tbl_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cancelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_cancel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 331, 940, 200));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("From :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        txt_cancel_from.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        getContentPane().add(txt_cancel_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 150, 250, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Departure Time :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 229, -1, -1));

        txt_cancel_seats.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_cancel_seats.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cancel_seatsKeyTyped(evt);
            }
        });
        getContentPane().add(txt_cancel_seats, new org.netbeans.lib.awtextra.AbsoluteConstraints(824, 226, 86, -1));

        jPanel1.setBackground(new java.awt.Color(238, 78, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1002, 100));

        jPanel2.setBackground(new java.awt.Color(252, 237, 218));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("* Select bus and enter no. of seats to cancel booking");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel5)
                .addContainerGap(305, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1001, 526));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_backActionPerformed
        // TODO add your handling code here:
        dispose();
        User_Dashboard ud = new User_Dashboard(username);
    }//GEN-LAST:event_cancel_backActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        
        int index = tbl_cancel.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(null, "First Select your booking and enter seats!!!");
        }
        else{
            TableModel tm = tbl_cancel.getModel();
            String b_no = tm.getValueAt(index, 0).toString();
            int seats = Integer.parseInt(tm.getValueAt(index, 7).toString());
            int amount = Integer.parseInt(tm.getValueAt(index, 8).toString());
            String temp_str = tm.getValueAt(index, 6).toString();
            String book_date = tm.getValueAt(index, 5).toString();
            
            String d_time = tm.getValueAt(index, 4).toString();
            String dt = tm.getValueAt(index, 3).toString();
            
            if(txt_cancel_seats.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Please enter no of seats you want to cancel!");
            }
            else
            {
            int op = JOptionPane.showConfirmDialog(null, "Do you really want to cancel your seats of bus having bus no : " + b_no + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
            if(op == 0){
                
                if(Integer.parseInt(txt_cancel_seats.getText()) > seats){
                    JOptionPane.showMessageDialog(null, "Enter valid no of seats because you only booked " + seats + " seats!");
                }
                else if(Integer.parseInt(txt_cancel_seats.getText()) <= seats){
                    
                    // Code for parts of time of bookingfor (save Time_date-time file)... 
                    StringTokenizer stt = new StringTokenizer(temp_str,":");
                    
                    int k = -1;
                    String []str_time = {"", "", ""};
                    
                    while(stt.hasMoreTokens())
                    {
                        k++;
                        str_time[k] = stt.nextToken();
                        System.out.print("\n Parts of time : "+str_time[k]);
                    }
                    
                    String booking = "Time_"+book_date+"-"+str_time[0]+"_"+str_time[1]+"_"+str_time[2]+".txt";
                    
                    // TO FIND OUT FILE PROCCESS...
                    try {
                        int i;
			File f1 = new File("c:\\City Bus\\User Bookings\\User_"+username);
		
			String[] mainFile = f1.list();
			String[] insideFile = null;
		
			for(i = 0; i<mainFile.length; i++)
			{
                            File ff = new File(f1, mainFile[i]);
				
                            if(booking.equals(mainFile[i]))
                            {
				
                                path = ""+ff.getAbsolutePath();
                                //System.out.print("\n File Path : "+path);
				break;
                            }
			
                            try {
				if(ff.isDirectory())
				{
                                    path = recursion(booking, ff, insideFile);
                                }
                            }
                            catch(Exception e) {
				System.out.print("\n RECURSION ERROR : "+e.getMessage());
                            }
			}
                    }
                    catch(Exception e) {
			System.out.print("\n ERROR 404 : "+e.getMessage());
                    }
                    
                    // Bacause getAbsolutePath() gives not proper access formate to for class File...
                    StringTokenizer st_file_path = new StringTokenizer(path,"\\" );
                    
                    int j = -1;
                    String []file_part = {"", "", "", "", "", ""};
                    
                    while(st_file_path.hasMoreTokens())
                    {
                        j++;
                        file_part[j] = st_file_path.nextToken();
                    }
                    
                    try
                    {
                        File ff2 = new File(""+file_part[0]+"\\"+file_part[1]+"\\"+file_part[2]+"\\"+file_part[3]+"\\"+file_part[4]+"\\"+file_part[5]+"\\"+booking);
                        FileReader fileRead = new FileReader(ff2);
                        BufferedReader bbr = new BufferedReader(fileRead);
                        
                        int c = -1, r, limit = 0;
                        String newline = "", data = "";
                        
                        int cancel_seat = Integer.parseInt(txt_cancel_seats.getText());
                        String rem_amount = Integer.toString( (amount/seats)*(seats-cancel_seat) );
                        String rem_seat = Integer.toString(seats-cancel_seat);
                        
                        // delete the raw of booking...
                        if(cancel_seat == seats)
                        {
                            fileRead.close();
                            ff2.delete();
                            
                            FileReader fread = new FileReader("c:\\City Bus\\User Bookings\\User_"+username+"\\BookingTimes.txt");
                            BufferedReader br = new BufferedReader(fread);
                            
                            String line = "";
                            String full_data = "";
                            String bkd_time = book_date+"-"+str_time[0]+"_"+str_time[1]+"_"+str_time[2];
                            
                            while(line != null)
                            {
                                line = br.readLine();
                                
                                if(line != null && line.equals(bkd_time))
                                {
                                    continue;
                                }
                                else if(line != null)
                                {
                                    full_data = full_data + line + "\n";
                                }
                                else
                                {
                                    FileWriter filewrite = new FileWriter("c:\\City Bus\\User Bookings\\User_"+username+"\\BookingTimes.txt");
                                
                                    filewrite.write(full_data);
                                    filewrite.close();
                                }
                            }
                            fread.close();
                            deleteBooking(b_no, dt, d_time, seats);
                            updateBus(b_no, dt, d_time, cancel_seat);
                            JOptionPane.showMessageDialog(null, cancel_seat + " seats canceled successfully!");
                            dispose();
                            User_Dashboard udb = new User_Dashboard(username);
                            
                        }
                        // update the seats
                        else {       
                            while(newline != null)
                            {        
                                newline = bbr.readLine(); 
                                    
                                if(limit < 7) {    
                                    limit++;
                                    data = data + newline + "\n";
                                }
                                else {
                                    try
                                    {
                                        FileWriter filewrite = new FileWriter(ff2);
                                
                                
                                        filewrite.write(data+rem_seat+"\n"+rem_amount);
                                        filewrite.close();
                                    }
                                    catch (Exception e) {
                                        System.out.print("\n FILE OVER WRITTEN ERROR : "+e.getMessage());
                                    }
                                }
                                tbl_cancel.setValueAt(rem_seat, index, 7);
                                tbl_cancel.setValueAt(rem_amount, index, 8);
                                
                                txt_cancel_seats.setText("");
                            }
                            fileRead.close();
                            updateBooking(b_no, dt, d_time, seats-cancel_seat, Integer.parseInt(rem_amount));
                            updateBus(b_no, dt, d_time, cancel_seat);
                            JOptionPane.showMessageDialog(null, cancel_seat + " seats canceled successfully!");
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.print("\n FILE READ OVER WRITE ERROR : "+e.getMessage());
                    }
                    
                                                            
                } // close else if block
                
            } // close if block
                
            } // close else block
        
        } // close else block
        
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void updateBooking(String b_no, String dt, String d_time, int seat, int amount){
        try {
            String q = "update user_bookings set no_of_seats=?, price=? where username=? and bus_no=? and dt=? and departure_time=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, seat);
            ps.setInt(2, amount);
            ps.setString(3, username);
            ps.setString(4, b_no);
            ps.setString(5, dt);
            ps.setString(6, d_time);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Cancellation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteBooking(String b_no, String dt, String d_time, int seat){
        try {
            String q = "delete from user_bookings where username=? and bus_no=? and dt=? and departure_time=? and no_of_seats=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, username);
            ps.setString(2, b_no);
            ps.setString(3, dt);
            ps.setString(4, d_time);
            ps.setInt(5, seat);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Cancellation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int findSeats(String b_no, String dt, String d_time){
        try {
            String q = "select * from bus_schedule where bus_no=? and dt=? and departure_time=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, b_no);
            ps.setString(2, dt);
            ps.setString(3, d_time);
            ResultSet rs = ps.executeQuery();
            int seat = 0;
            while(rs.next()){
                seat = rs.getInt(6);
            }
            return seat;
        } catch (SQLException ex) {
            Logger.getLogger(Cancellation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    private void updateBus(String b_no, String dt, String d_time, int seat){
        try {
            int t_seats = findSeats(b_no, dt, d_time);
            int f_seats = t_seats + seat;
            String q = "update bus_schedule set available_seats=? where bus_no=? and dt=? and departure_time=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, f_seats);
            ps.setString(2, b_no);
            ps.setString(3, dt);
            ps.setString(4, d_time);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Cancellation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void txt_cancel_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cancel_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cancel_timeActionPerformed

    private void txt_cancel_seatsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cancel_seatsKeyTyped
        // TODO add your handling code here:
        char TestChar = evt.getKeyChar();
        if(!(Character.isDigit(TestChar))){
            evt.consume();
        }
    }//GEN-LAST:event_txt_cancel_seatsKeyTyped

    private void tbl_cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cancelMouseClicked
        // TODO add your handling code here:
        
        int index = tbl_cancel.getSelectedRow();
        TableModel tm = tbl_cancel.getModel();
        
        String bus_no = tm.getValueAt(index, 0).toString();
        String from = tm.getValueAt(index, 1).toString();
        String to = tm.getValueAt(index, 2).toString();
        String departure = tm.getValueAt(index, 4).toString();
        
        txt_cancel_busno.setText(bus_no);
        
        txt_cancel_from.setText(from);
        
        txt_cancel_to.setText(to);
        
        txt_cancel_time.setText(departure);
            
        JOptionPane.showMessageDialog(null, "Now, Enter no of seats you want to cancel.");
    }//GEN-LAST:event_tbl_cancelMouseClicked

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
            java.util.logging.Logger.getLogger(Cancellation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cancellation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cancellation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cancellation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cancellation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton cancel_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_cancel;
    private javax.swing.JTextField txt_cancel_busno;
    private javax.swing.JTextField txt_cancel_from;
    private javax.swing.JTextField txt_cancel_seats;
    private javax.swing.JTextField txt_cancel_time;
    private javax.swing.JTextField txt_cancel_to;
    // End of variables declaration//GEN-END:variables
}
