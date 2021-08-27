import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends JFrame implements ActionListener{
	 JButton b1,b2,b3;
	Home(){
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
		JLabel l1=new JLabel("WELCOME TO THE SMART PARKING ");
		l1.setForeground(Color.white);
		l1.setFont(new Font("",Font.BOLD,40));
		l1.setBounds(100, 170, 800, 50);
		add(l1);
		
	    b1=new JButton("Park a vehicle");
		b1.setBackground(Color.blue);
		b1.setForeground(Color.white);
		b1.setBounds(100, 400,150, 50);
		b1.addActionListener(this);
		add(b1);
		
	    b2=new JButton("Withdraw a vehicle");
		b2.setBackground(Color.blue);
		b2.setForeground(Color.white);
		b2.setBounds(400, 400,150, 50);
		b2.addActionListener(this);
		add(b2);
		
	    b3=new JButton("EXIT");
		b3.setBackground(Color.blue);
		b3.setForeground(Color.white);
		b3.setBounds(700, 400,150, 50);
		b3.addActionListener(this);
		add(b3);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			setVisible(false);
		   	new GetParkingSlot();
		}
		else if(e.getSource()==b2) {
			setVisible(false);
			new Withdraw();
		}
		else {
			System.exit(0);
		}
	}
	public static void main(String args[]) {
		new Home();
	}
}
