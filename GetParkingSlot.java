import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GetParkingSlot extends JFrame implements ActionListener {
	public JButton home,submit;
	int emptySlot;
	JTextField name,pass;
	Connection con;
	GetParkingSlot(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","aman");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select id from slots where booked=-1");
			 emptySlot=-1;
			while(rs.next()) {
				emptySlot=rs.getInt(1);
				break;
			}
			try {
				rs.close();
				stmt.close();
			}catch(Exception e) {
				System.out.println(e);
			}
			setBounds(400, 150, 1000, 600);
			getContentPane().setBackground(Color.WHITE);
			Image i=null;
			try {
				i=ImageIO.read((new File("C:\\Users\\Aman\\eclipse-workspace-development\\parking system\\src\\images\\home.jpg")));
			}catch(IOException e) {
				System.out.println(e);
			}
			i=i.getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
			ImageIcon ic=new ImageIcon(i);
			setContentPane(new JLabel(ic));
			setLayout(null);
			JLabel l1=new JLabel("PARKING");
			l1.setForeground(Color.white);
			l1.setFont(new Font("",Font.BOLD,40));
			l1.setBounds(350, 130, 800, 50);
			add(l1);
			if(emptySlot==-1) {
				JLabel jl=new JLabel("No Parking Slot Available!!!");
				jl.setBounds(200, 250, 800, 50);
				jl.setFont(new Font("",Font.BOLD,50));
				jl.setForeground(Color.red);
				add(jl);
			}
			else {
				JLabel jl=new JLabel("Your Name :");
				jl.setBounds(100, 200, 200, 50);
			    jl.setFont(new Font("",Font.BOLD,30));
			    jl.setForeground(Color.BLACK);
				add(jl);
				name=new JTextField();
				name.setFont(new Font("",Font.ITALIC,20));
				name.setBounds(300,200,400,50);
				add(name);
				
				JLabel _jl=new JLabel("password:");
				_jl.setBounds(100, 300, 200, 50);
			    _jl.setFont(new Font("",Font.BOLD,30));
			    _jl.setForeground(Color.BLACK);
				add(_jl);
			    pass=new JTextField();
				pass.setBounds(300,300,400,50);
				pass.setFont(new Font("",Font.PLAIN,20));
				add(pass);
				
				submit=new JButton("submit");
				submit.setBackground(Color.blue);
				submit.setForeground(Color.white);
				submit.setBounds(400, 380,150, 50);
				submit.addActionListener(this);
				add(submit);
				
			}
			    home=new JButton("go to home");
				home.setBackground(Color.blue);
				home.setForeground(Color.white);
				home.setBounds(400, 450,150, 50);
				home.addActionListener(this);
				add(home);
			
		}catch(Exception e) {
			System.out.println(e);
		}	
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==home) {
			setVisible(false);
			try {
				con.close();
			}catch(Exception e) {
				System.out.println(e);
			}
			new Home();
		}
		else if(ae.getSource()==submit) {
			String _name=name.getText();
			String _pass=pass.getText();
		    String sql="update slots set name= ?,pass=?,booked=? where id=?";
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,_name);
				ps.setString(2,_pass);
				ps.setInt(3, 1);
				ps.setInt(4, emptySlot);
				ps.executeUpdate();
				ps.close();
				con.close();
				setVisible(false);
				new Success(1,emptySlot);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}
