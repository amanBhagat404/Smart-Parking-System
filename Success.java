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

public class Success extends JFrame implements ActionListener{
	    Success(int success,int id) {
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
			if(success==1) {
				JLabel l1=new JLabel("You have succesfully ");
				l1.setForeground(Color.white);
				l1.setFont(new Font("",Font.BOLD,40));
				l1.setBounds(200, 150, 800, 50);
				add(l1);
				if(id!=-1) {
					JLabel l2=new JLabel("Parked Your Vehicle");
					l2.setForeground(Color.white);
					l2.setFont(new Font("",Font.BOLD,40));
					l2.setBounds(200, 200, 800, 50);
					add(l2);
					JLabel l3=new JLabel("Your slot id :"+id);
					l3.setForeground(Color.black);
					l3.setFont(new Font("",Font.BOLD,40));
					l3.setBounds(200, 250, 800, 50);
					add(l3);
				}
				else {
					JLabel l2=new JLabel("Withdrawn Your Vehicle");
					l2.setForeground(Color.white);
					l2.setFont(new Font("",Font.BOLD,40));
					l2.setBounds(200, 200, 800, 50);
					add(l2);
				}
			}
			else {
				JLabel l1=new JLabel("Wrong slot or Wrong password");
				l1.setForeground(Color.white);
				l1.setFont(new Font("",Font.BOLD,40));
				l1.setBounds(200, 200, 800, 50);
				add(l1);
			}
			JButton b3=new JButton("EXIT");
			b3.setBackground(Color.blue);
			b3.setForeground(Color.white);
			b3.setBounds(400, 400,150, 50);
			b3.addActionListener(this);
			add(b3);
			setVisible(true);
		}
	    public void actionPerformed(ActionEvent ae) {
	    	System.exit(0);
	    }
}
