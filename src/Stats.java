import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	
	public Stats(){
	
		createWindow();
	
	}
	
    private static void createWindow() {
    	       
       JFrame panel = new JFrame("Arrow Shaft Maker"); 
       panel.getContentPane().setLayout( new BoxLayout(panel.getContentPane(), BoxLayout.Y_AXIS) );        

       label = new JLabel("<html><div style='width: 200px; text-align: center; margin: 20px 0 20px 0;'>You've chopped"
    		+ " <strong>0</strong> logs so far.<br>That's <strong>0</strong> arrow shafts.</div></html>");
       label.setFont(new Font("Arial", Font.PLAIN, 14));
       label.setForeground(Color.black);
       label.setHorizontalAlignment( SwingConstants.CENTER );
       
       label2 = new JLabel("<html><div style='width: 200px; text-align: center; margin-bottom: 20px;'>Status: Starting...</div></html>");
       label2.setFont(new Font("Arial", Font.PLAIN, 14));
       label2.setForeground(Color.black);
       label2.setHorizontalAlignment( SwingConstants.CENTER );
       
       timel = new JLabel("<html><div style='width: 200px; text-align: center; margin-bottom: 20px;'>Time(h:m:s): 0"
       		+ "<br>Logs per hour: 0</div></html>");
       timel.setFont(new Font("Arial", Font.PLAIN, 14));
       timel.setForeground(Color.black);
       timel.setHorizontalAlignment( SwingConstants.CENTER );
       
       panel.getContentPane().add(label);
       panel.getContentPane().add(timel);
       panel.getContentPane().add(label2);
       
       panel.setLocation(150,150);
       panel.pack();
       panel.setVisible(true);
         
       Timer timer = new Timer();
       startTime = System.currentTimeMillis();
       
       timer.scheduleAtFixedRate(new TimerTask() {
    	   @Override
    	   public void run() {
    		   
    		   	long longVal = (long) (System.currentTimeMillis() - startTime) / 1000;

    		    int hours = (int) longVal / 3600;
    		    int remainder = (int) longVal - hours * 3600;
    		    int mins = remainder / 60;
    		    remainder = remainder - mins * 60;
    		    int secs = remainder;
    		   
    		    timel.setText("<html><div style='width: 200px; text-align: center; margin-bottom: 20px;'>Time(h:m:s): "
    				   + hours + ":" + mins + ":" + secs
    				   + "<br>Logs per hour: " + (float)Math.round(logs / ((System.currentTimeMillis() - startTime) / 1000 / 60 / 60)) 
    				   + "</div></html>");
    	   }
    	 }, 100, 100);
       
    }
    
    /*public static void main(String[] args) {
        
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createWindow();
            }
        });
	
    }*/
    
    public void addLog(int l){
    	
    	logs = l;
    	label.setText("<html><div style='width: 200px; text-align: center; margin: 20px 0 20px 0;'>You've chopped <strong>"
    		+ logs + "</strong> logs so far.<br>That's <strong>" + (logs * 15) + "</strong> arrow shafts.</div></html>");
    
    }//update the logs counter
    
    public void setStatus(String s){
    	
    	label2.setText("<html><div style='width: 200px; text-align: center; margin-bottom: 20px;'>Status: " + s + "</div></html>");

    }//update the status
}