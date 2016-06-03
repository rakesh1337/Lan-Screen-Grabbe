import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class IPPanel extends JFrame{
	private JLabel lb1;
	private JTextField tbIP;
	private JButton btn1;
	private JLabel wlabel;

	public IPPanel() {
		lb1=new JLabel("Server IP:");
		tbIP=new JTextField();
		btn1=new JButton("Connect");
		wlabel = new JLabel("Welcome to Client Panel");
		
		add(lb1);
		add(tbIP);
		add(btn1);
		add(wlabel);
	
		wlabel.setForeground(Color.BLUE);
		ImageIcon image = new ImageIcon ("images/lsgNew.png");
		JLabel imglogo = new JLabel (image);
		add(imglogo);
		setSize(200,200);
		setVisible(true);
		setResizable(false);
		
		setLayout(null);
		lb1.setBounds(38,250,100,25);
		tbIP.setBounds(38,280,150,25);
		btn1.setBounds(38,320,150,25);
		imglogo.setBounds(18,45,200,200);
		wlabel.setBounds(40,10,150,25);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MyRemote mr=(MyRemote)Naming.lookup("rmi://"+tbIP.getText()+"/remoteObj");
					LoginPanel loginPanel=new LoginPanel(mr);
					loginPanel.setVisible(true);
					IPPanel.this.dispose();						
					
					
				} catch (Exception e3) {
					String msg=e3.getMessage();
					JOptionPane.showMessageDialog(null, msg.substring(0,msg.indexOf(";")));
					//e3.printStackTrace();
				}
				
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("IP Panel");
		setSize(245,400);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new IPPanel();
	}
}
