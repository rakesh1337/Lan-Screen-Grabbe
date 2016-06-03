
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AddStudent extends JFrame{
	private JLabel lb1,lb2;
	private JTextField tbUser;
	private JTextField tbPassword;
	private JButton btnAdd;	
	
	public AddStudent() {
		
		lb1=new JLabel("User Name:");
		lb2=new JLabel("Password:");		
		tbUser=new JTextField();
		tbPassword=new JTextField();btnAdd=new JButton("Add User");
		
		setLayout(null);
		lb1.setBounds(10,10,70,25);
		tbUser.setBounds(90,10,140,25);
		lb2.setBounds(10,40,70,25);
		tbPassword.setBounds(90,40,140,25);
		btnAdd.setBounds(90,70,100,25);
		setResizable(false);
		
		add(lb1);
		add(lb2);
		add(tbUser);
		add(tbPassword);
		add(btnAdd);
		
		setTitle("User Entry");
		setSize(300,200);
		setVisible(true);
		setLocationRelativeTo(null);

		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				if(tbUser.getText().equals("") || tbPassword.getText().equals("")){
					JOptionPane.showMessageDialog(null, "User/Password not Entered");
					return;
				}
				
				String sql="insert into student(userid,password) values(?,?)";
				try {
					Connection con=DataConnection.connect();
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1, tbUser.getText());
					ps.setString(2, tbPassword.getText());
					
					
					int xx=ps.executeUpdate();	
					if(xx>0){
						JOptionPane.showMessageDialog(null, "Details added successfully...");
					}
					
				} catch (SQLException e1) {
					if(e1.getMessage().indexOf("Table 'STUDENT' does not exist")!=1){
						createAndInsert(tbUser.getText(),tbPassword.getText());
					}else{
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		
		});
	}
	
	
	public void createAndInsert(String user,String password){
		try {
			String sql="create table STUDENT(userid varchar(30) primary key,password varchar(30))";
			Connection con=DataConnection.connect();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="insert into student(userid,password) values(?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, password);
			
			int xx=ps.executeUpdate();
			
			if(xx>0){
				JOptionPane.showMessageDialog(null, "Details added successfully...");
			}
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new AddStudent();
	}
}
