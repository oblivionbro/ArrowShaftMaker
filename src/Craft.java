import java.awt.Point;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Hud;
import org.powerbot.script.rt6.Item;

public class Craft extends Task{
	
	//item ids
	private int logId = 1511;
	private int arrowShaftId = 52;
	private int featherId = 314;
	//item ids
	
	ClientContext c;
	private Stats statsWindow;
	
	public Craft(ClientContext ctx, Stats s) {
		super(ctx);
		c = ctx;
		statsWindow = s;
	}

	@Override
	public boolean activate() {
		return c.backpack.select().count() == 28;//only start crafting if the inventory is full #efficiency
	}

	@Override
	public void execute() {
		
		if(!c.hud.opened(Hud.Window.BACKPACK)){
			c.hud.open(Hud.Window.BACKPACK);
		}//open the backpack if not already open
		
		Item[] tt = c.backpack.items();
		
		for(int i = 0; i < tt.length; i++){
			if(tt[i].id() != logId && tt[i].id() != arrowShaftId && tt[i].id() != featherId){
				for(String n : tt[i].groundActions()){
					if(n != null){
						if(n.contains("Drop")) tt[i].interact("Drop");
						else if(n.contains("Destroy")){
							tt[i].interact("Destroy");
							pause(1000);
							goClickWidget(1183, 16, -1);
						}
					}
				}//iterate through options/actions
			}
		}//drop stuff that you don't want
		
		Item i = c.backpack.itemAt(c.backpack.indexOf(logId));
	    i.interact("Craft");//click craft on the logs
	    
	    pause(900);
	    
	    if(c.widgets.component(1179, 33).visible()){
	    	goClickWidget(1179, 33, -1);
	    }//choose the knife if not already selected
	    
	    pause(800);
	    	    
	    if(c.widgets.component(1371, 44).visible()){
	    	goClickWidget(1371, 44, 0);
	    }//make sure its on arrow shaft
	    
	    pause(1000);
	    
	    goClickWidget(1370, 20, -1);//click the fletch button
	    
	    statsWindow.setStatus("Fletching arrows.");

	    pause(38000);//wait for fletching to finish
	    
	    int t = rand(0, c.game.dimensions().height);
	    int t2 = rand(0, c.game.dimensions().width);
	    
	    if(rand(0,10) >= 5)
	    	c.mouse.drag(new Point(t, t2), false);//randomly move the mouse because why not
	    
	    pause(10000);//continue waiting for fletching to finish
	    
	    

	}
	
}
