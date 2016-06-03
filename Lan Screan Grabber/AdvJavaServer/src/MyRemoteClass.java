
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyRemoteClass extends UnicastRemoteObject implements MyRemote {

	private Robot robot;
	private boolean shareScreen;
	protected MyRemoteClass() throws RemoteException, AWTException {
		this.robot=new Robot();
		setShareScreen(false);
	}

	@Override
	public boolean login(String id,String password) {
		boolean loginSuccess=false;
		try {
			String sql="select * from student where userid=? and password=?";
			Connection con=DataConnection.connect();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				loginSuccess=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginSuccess;
	}

	@Override
	public ImageIcon getCurrentScreen() throws RemoteException {
		if(isShareScreen()){
			ImageIcon imageIcon = new ImageIcon( getScreen() );
			return imageIcon;
		}else{
			ImageIcon image = new ImageIcon ("images/lsgClient.png");
			return image;
		}
	}
	
	public BufferedImage getScreen() {
        Dimension screenSize  = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screen = new Rectangle( screenSize );
        BufferedImage i = robot.createScreenCapture( screen );
        return i;
    }
     
    public void saveScreen( String fileName ) throws IOException {
        File file = new File( fileName );
        file.createNewFile();
        ImageIO.write( getScreen(), "JPEG", file );
    }

	@Override
	public void exit() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public boolean isShareScreen() {
		return shareScreen;
	}

	public void setShareScreen(boolean shareScreen) {
		this.shareScreen = shareScreen;
	}
}

