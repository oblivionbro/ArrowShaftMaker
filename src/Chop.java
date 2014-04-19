import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

public class Chop extends Task{
	
	ClientContext c;
	private int[] treeIds = {38787, 38783, 38788, 38782, 38760, 38785, 45798, 47598};
	private int logs = 0;
	private int logId = 1511;
	private int lastCheck;
	private Stats statsWindow;
	
	public Chop(ClientContext ctx, Stats s) {
		super(ctx);
		c = ctx;
		lastCheck = c.backpack.id(logId).count();
		statsWindow = s;
	}

	@Override
	public boolean activate() {
		if(!(c.backpack.select().count() < 28)){
			return false;
		}
		else if(c.objects.select().id(treeIds).isEmpty()){
			return false;
		}
		else if(c.players.local().animation() != -1){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public void execute() {
		
		if(c.backpack.id(logId).count() > lastCheck){
			lastCheck = c.backpack.id(logId).count();
			logs++;
			statsWindow.addLog(logs);
		}//update the log counter
		else if(c.backpack.id(logId).count() != lastCheck){
			lastCheck = c.backpack.id(logId).count();
		}//check if inventory was emptied
		
		GameObject tree = c.objects.nearest().poll();
		if (tree.inViewport() && tree.id() != 38731) {
            tree.interact("Chop");
            statsWindow.setStatus("Chopping trees.");
            
            pause(2000);
        } else {
        	statsWindow.setStatus("Walking...");
            c.movement.step(tree);
            c.camera.turnTo(tree);
            
            pause(200);
            
            int z = c.camera.z();
            int x = c.camera.x();
            
            if(c.camera.z() < -4000){ z = c.camera.z() + rand(20, 400); }
            else{ z = c.camera.z() - rand(20, 400); }
                        
            if(c.camera.z() > -2200){ z = c.camera.z() + rand(20, 400); }
            else{ z = c.camera.z() - rand(20, 400); }
            
            if(rand(0,10) > 5){ x = c.camera.x() + rand(20, 400); }
            else{ x = c.camera.x() - rand(20, 400); }
            
            if(rand(0,10) >= 5) c.camera.pitch(z);
            if(rand(0,10) >= 5) c.camera.angle(x);//put in some weird camera movements sometimes
            
            pause(800);
            
        }
		
		
		
		
	}

}
