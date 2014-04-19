import java.awt.Point;
import java.util.ArrayList;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

@Manifest(name = "Josh's Test", description = "don't take the kittens outside",properties = "client=6;")

public class WoodCutter extends PollingScript<ClientContext> {
	
	public Stats statsWindow;
	private ArrayList<Task> taskList = new ArrayList<Task>();
	
	@Override
	public void start() {
		
		statsWindow = new Stats();
		statsWindow.setVisible(true);
		statsWindow.setLocation(new Point(100,100));
		
		taskList.add(new Chop(ctx, statsWindow));
		taskList.add(new Craft(ctx, statsWindow));
		
		
	}
	
	@Override
	public void poll() {
		for(Task task : taskList) {
		    if(task.activate()) {
		        task.execute();
		    }
		}
	}
		
} 

