import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;

public abstract class Task extends ClientContext{
	
	ClientContext c;
	
    public Task(ClientContext ctx) {
		super(ctx);
		c = ctx;
	}
    
	public abstract boolean activate();
	
    public abstract void execute();
    
	public void pause(int minTime){
		int upper = (int) (minTime * 1.2);
		int randTime = rand(minTime, upper);
		
		try {
			Thread.sleep(randTime);
		} catch (InterruptedException e1) { e1.printStackTrace(); }
	}//pauses from anywhere between the time input and 20% more than the time
	
	public void goClickWidget(int widget, int component, int childComponent){
		
		int x,y,w,h;
		
		if(childComponent == -1){
			x = c.widgets.component(widget, component).viewportRect().x;
		    y = c.widgets.component(widget, component).viewportRect().y;
		    w = c.widgets.component(widget, component).width();
		    h = c.widgets.component(widget, component).height();
		}
		else{
			x = c.widgets.component(widget, component).component(childComponent).viewportRect().x;
		    y = c.widgets.component(widget, component).component(childComponent).viewportRect().y;
		    w = c.widgets.component(widget, component).component(childComponent).width();
		    h = c.widgets.component(widget, component).component(childComponent).height();
		}
			
		int rx = rand(x, x + w);
		int ry = rand(y, y + h);
		
		c.mouse.drag(new Point(rx, ry), false);
	    c.mouse.click(new Point(rx, ry), true);
	    
	}//clicks between the bounds
	
	public int rand(int a, int b){
	
		return Random.nextInt(a, b);
			
	}//random number between two numbers
	
	
	
}