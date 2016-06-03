
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;


public class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private String label;
	private boolean isPushed;
	private JButtonTable frm;

	public ButtonEditor(JCheckBox checkBox,JButtonTable frm) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
		this.frm=frm;
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			try {
				Connection con=DataConnection.connect();
				String sql="delete from student where userid=?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, label);
				
				int xx=ps.executeUpdate();
				
				String msg="";
				if(xx>0){
					msg=String.format("%s deleted successfully", label);
					
					frm.dispose();
					
					JButtonTable jte=new JButtonTable();
				}else{
					msg="delete failed...";
				}
				
				JOptionPane.showMessageDialog(null,msg);
				
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
			
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
