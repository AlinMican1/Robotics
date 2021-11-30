import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Onwards implements Behavior {
	private static int SPEED = 100;
	private static int TIME = 2000;
	
	private boolean suppressed = false;
	private MovePilot movePilot;

	
	public Onwards(MovePilot movePilot) {//Constructor making a new movepilot
		this.movePilot = movePilot;
		
	}
	
	public boolean takeControl() {//always returns true
		
		return true;
	}
	
	public void action() {//makes the robot go forward at a steady speed.
		suppressed = false;
		if(!movePilot.isMoving()) {
			movePilot.setLinearSpeed(SPEED);
			movePilot.forward();
		}
		
	}
	
	public void suppress() {//suppresses the action.
		suppressed = true;
	}

}

	