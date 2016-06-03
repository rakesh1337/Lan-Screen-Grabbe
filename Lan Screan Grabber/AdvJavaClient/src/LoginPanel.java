import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPanel extends JFrame{
	private JLabel lb1,lb2;
	private JTextField tbUser;
	private JPasswordField tbPassword;
	private JButton btnLogin;
	private MyRemote mr;
	
	public LoginPanel(final MyRemote mr) {
		
		this.mr=mr;
		
		lb1=new JLabel("User Name:");
		lb2=new JLabel("Password:");
		
		tbUser=new JTextField();
		tbPassword=new JPasswordField();
		tbPassword.setEchoChar('*');
		btnLogin=new JButton("Login");
		
		add(lb1);
		add(lb2);
		add(tbUser);
		add(tbPassword);
		add(btnLogin);
		
		setLayout(null);
		setResizable(false);
		lb1.setBounds(10,10,70,25);
		tbUser.setBounds(90,10,140,25);
		lb2.setBounds(10,40,70,25);
		tbPassword.setBounds(90,40,140,25);
		btnLogin.setBounds(90,70,100,25);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login");
		setSize(300,200);
		setVisible(true);
		setLocationRelativeTo(null);

		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String user=tbUser.getText();
				String password=new String(tbPassword.getPassword());
				
				try {
					boolean success=mr.login(user, password);
					if(success){
						ClientMain cm=new ClientMain(mr);
						cm.setVisible(true);
						LoginPanel.this.dispose();
						
					}else{
						JOptionPane.showMessageDialog(null, "Userid/password incorrect");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}
}