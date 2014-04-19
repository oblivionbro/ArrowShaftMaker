import java.util.ArrayList;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

@Manifest(name = "Jesh's Arrow Shaft Bot", description = "Cuts trees and turns them into arrow shafts.",properties = "client=6;")

public class ArrowShafts extends PollingScript<ClientContext> {
	
	public Stats statsWindow;
	private ArrayList<Task> taskList = new ArrayList<Task>();
	
	@Override
	public void start() {
		statsWindow = new Stats();
		
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
	
	@Override
	public void stop() {
		ctx.controller.stop();
		statsWindow.setVisible(false);
		Stats.logs = 0;
	}
		
} 

