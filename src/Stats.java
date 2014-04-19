import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Stats extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 6876500906177935381L;
	static JLabel label;
	static JLabel label2;
	static JLabel timel;
	static double startTime;
	static public int logs = 0;
	
    private static void createWindow() {
    	       
       JFrame panel = new JFrame("Arrow Shaft Maker"); 
       panel.getContentPane().setLayout(
    		    new BoxLayout(panel.getContentPane(), BoxLayout.Y_AXIS)
    		);       
       panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       label = new JLabel("<html><center>You've chopped 0 logs so far<br>That's 0 arrow shafts</center></html>");
       label.setFont(new Font("Arial", Font.PLAIN, 14));
       label.setForeground(Color.black);
       label.setHorizontalAlignment( SwingConstants.CENTER );
       
       label2 = new JLabel("<html><center>Status: Starting...</center></html>");
       label2.setFont(new Font("Arial", Font.PLAIN, 14));
       label2.setForeground(Color.black);
       label2.setHorizontalAlignment( SwingConstants.CENTER );
       
       timel = new JLabel("<html><center>Time: 0<br>Logs per hour: 0</center></html>");
       timel.setFont(new Font("Arial", Font.PLAIN, 14));
       timel.setForeground(Color.black);
       timel.setHorizontalAlignment( SwingConstants.CENTER );
       
       panel.getContentPane().add(label);
       panel.getContentPane().add(timel);
       panel.getContentPane().add(label2);
       
       panel.setLocationRelativeTo(null);
       panel.pack();
       panel.setSize(300,350);
       panel.setVisible(true);
       
       
       Timer timer = new Timer();
       startTime = System.currentTimeMillis();
       
       timer.scheduleAtFixedRate(new TimerTask() {
    	   @Override
    	   public void run() {
    		   timel.setText("<html><center>Time: "
    				   + (float)Math.round((System.currentTimeMillis() - startTime)/10)/100
    				   + "<br>Logs per hour: " + (float)Math.round(logs / ((System.currentTimeMillis() - startTime) / 1000 / 60 / 60))
    				   + "</center></html>");
    	   }
    	 }, 100, 100);
       
    }
    
    public static void main(String[] args) {
        
    	//javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //public void run() {
            	createWindow();
            //}
       // });
    	
    	
    }
    
    public void addLog(int l){
    	
    	logs = l;
    	label.setText("<html><center>You've done chopped " + (logs - 1) + " logs so far<br>"
           		+ "That's " + ((logs * 15) - 15) + " arrow shafts</center></html>");
    
    }//update the logs counter
    
    public void setStatus(String s){
    	
    	label2.setText("<html><center>Status: " + s + "</center></html>");

    }//update the status
}