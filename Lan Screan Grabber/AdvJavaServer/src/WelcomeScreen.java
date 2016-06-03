
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class WelcomeScreen extends JFrame{
	
	private JButton btnStart;
	private JButton btnShow;
	private JButton btnAdd;
	private JCheckBox chkshare;
    private JLabel serverStatus1;
	private JLabel serverStatus2;
	private JLabel wLabel;
	private MyRemoteClass mrc;
	
	public WelcomeScreen()
	{	
		btnStart=new JButton("Start The Server");
		btnAdd=new JButton("Add Users to Database");
		btnShow=new JButton("View & Delete Users");
		chkshare = new JCheckBox("Share Screen");
		wLabel = new JLabel("Welcome to The Admin Panel of Lan Screen Grabber");
		wLabel.setAlignmentX(CENTER_ALIGNMENT);
		wLabel.setForeground(Color.BLUE);
		serverStatus1=new JLabel("Server Status:");
		serverStatus2=new JLabel("Not Connected");
		
		
		ImageIcon image = new ImageIcon ("images/lsgNew.png"); // Creates the image
		JLabel imglogo = new JLabel (image); 
		add(imglogo); 
		setSize(200,200);
		setVisible(true);
		
		
		serverStatus2.setForeground(Color.RED);
		add(chkshare);
		add(btnStart);
		add(btnAdd);
		add(btnShow);
		add(wLabel);
		add(serverStatus1);
		add(serverStatus2);
		
		setLayout(null);
		wLabel.setBounds(100,10,300,25);
		btnStart.setBounds(10,190,150,25);
		btnAdd.setBounds(170,190,170,25);
		btnShow.setBounds(350,190,150,25);
		serverStatus1.setBounds(10,235,180,25);
		serverStatus2.setBounds(100,235,180,25);
		imglogo.setBounds(150,20,200,200);
		chkshare.setBounds(350,231,180,25);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Admin Panel of Lan Screen Grabber");
		setSize(528,300);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		chkshare.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent es) {
				try {
					mrc.setShareScreen(chkshare.isSelected());
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
		});
		
		
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					mrc = new MyRemoteClass();
					LocateRegistry.createRegistry(1099);	
					Naming.bind("rmi://localhost/remoteObj",mrc);
					//System.out.println("RMI Server ready...");
					serverStatus1.setText("Server Status:");
					serverStatus2.setForeground(Color.GREEN);
					serverStatus2.setText("Connected");
					} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		

			btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AddStudent addstudent=new AddStudent();
					addstudent.setVisible(true);
					addstudent.addWindowListener(new WindowAdapter() {
				    
						public void windowClosing(WindowEvent e) {
				       
				      }
				    });
					
				} catch (Exception e3) {
					String msg=e3.getMessage();
					JOptionPane.showMessageDialog(null, msg.substring(0,msg.indexOf(";")));
					//e3.printStackTrace();
				}
				
			}
		});
		
			btnShow.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						JButtonTable frame = new JButtonTable();
					    frame.addWindowListener(new WindowAdapter() {
					      public void windowClosing(WindowEvent e) {
					       
					      }
					    });
						
					} catch (Exception e3) {
						String msg=e3.getMessage();
						JOptionPane.showMessageDialog(null, msg.substring(0,msg.indexOf(";")));
						//e3.printStackTrace();
					}
					
				}
			});	
			
		
		}

	public static void main(String args[]){
		new WelcomeScreen();
	}
}
