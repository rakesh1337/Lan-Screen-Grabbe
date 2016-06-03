import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
public class ClientMain extends JFrame implements Runnable{
	private JLabel label;
	private MyRemote mr;
	private boolean running;
	private Thread t;

	public ClientMain(MyRemote mr){
		label = new JLabel();
        JScrollPane p = new JScrollPane( label );
        getContentPane().add( p );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 1366, 768 );
        setVisible( true );
        this.mr=mr;
        start();
	}
	
	public void start() {
        t = new Thread( this );
        running = true;
        t.start();
    }
     
    public void run() {
        while ( running ) {
            try {
            		invalidate();
                    ImageIcon i = mr.getCurrentScreen();
                    label.setIcon( i );
                    validate();
                
            }        
            catch( Exception x ) {
                x.printStackTrace();
            }
        }
    }
     
    public void stop() {
        running = false;
    }
}
