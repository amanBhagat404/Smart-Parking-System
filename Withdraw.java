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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Withdraw extends JFrame implements ActionListener {
	public JButton home,submit;
	int emptySlot;
	JTextField id,pass;
	Connection con;
	Withdraw(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","aman");
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
		JLabel l1=new JLabel("WITHDRAW");
		l1.setForeground(Color.white);
		l1.setFont(new Font("",Font.BOLD,40));
		l1.setBounds(350, 130, 800, 50);
		add(l1);
		
		JLabel jl=new JLabel("Slot id :");
		jl.setBounds(100, 200, 200, 50);
	    jl.setFont(new Font("",Font.BOLD,30));
	    jl.setForeground(Color.BLACK);
		add(jl);
		id=new JTextField();
		id.setFont(new Font("",Font.ITALIC,20));
		id.setBounds(300,200,400,50);
		add(id);
		
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
		
		home=new JButton("go to home");
		home.setBackground(Color.blue);
		home.setForeground(Color.white);
		home.setBounds(400, 450,150, 50);
		home.addActionListener(this);
		add(home);
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
		else {
			int _id=Integer.parseInt(id.getText());
			String _pass=pass.getText();
			String sql="select * from slots where id=?";
		    try {
		    	PreparedStatement ps=con.prepareStatement(sql);
		    	ps.setInt(1, _id);
		    	ResultSet rs=ps.executeQuery();
		    	String password="";
		    	while(rs.next()) {
		    		password=rs.getString(4);
		    	}
		    	if(password.equals(_pass)) {
		    		sql="update slots set booked=? where id=?";
		    		ps=con.prepareStatement(sql);
		    		ps.setInt(1, -1);
		    		ps.setInt(2, _id);
		    		ps.executeUpdate();
		    		ps.close();
			    	rs.close();
			    	setVisible(false);
			    	new Success(1,-1);
		    	}
		    	else {
		    		setVisible(false);
		    		new Success(-1,-1);
		    	}
		    }catch(Exception e) {
		    	System.out.println(e);
		    }
		    try {
				con.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}
