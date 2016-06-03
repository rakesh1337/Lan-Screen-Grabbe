
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;
  

public class JButtonTable extends JFrame {
  
  private JTable  table;

public JButtonTable(){
    super( "User Details" );
     
    DefaultTableModel dm = new DefaultTableModel();
    
    ArrayList<Student> studLst=null;
    try {
		studLst=Student.getAllStudents();
    
		Object [][]studArr=new Object[studLst.size()][];
		
		int i=0;
		for(Student stud:studLst){
			studArr[i++]=new Object[]{stud.getUserId(),stud.getPassword()};
		}
		
		dm.setDataVector(studArr,
                     new Object[]{"User(Click to delete)","Password"});
                      
	    table = new JTable(dm);
	    table.getColumn("User(Click to delete)").setCellRenderer(new ButtonRenderer());
	    table.getColumn("User(Click to delete)").setCellEditor(new ButtonEditor(new JCheckBox(),this));
	    
	    
    } catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add( scroll );
    setSize( 400, 730 );
    setVisible(true);
    setLocationRelativeTo(null);
    setResizable(false);
  }
  
  public static void main(String[] args) {
    JButtonTable frame = new JButtonTable();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}