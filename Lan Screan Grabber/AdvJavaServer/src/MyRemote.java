
import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;

public interface MyRemote extends Remote{
	public boolean login(String id,String password)throws RemoteException;
	public ImageIcon getCurrentScreen()throws RemoteException;
	void exit() throws RemoteException;
	
}

