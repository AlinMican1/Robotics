import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EdgeReached implements Behavior {
	private static int SPEED = 100;
	private static int ANGLE = 90;
	private static int TIME = 700;
	private float[] level =  new float[1];
	
	private MovePilot pilot;//Uses move pilot and Colour sensor
	private EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S3);
	private SampleProvider colour = sensor.getRedMode();
	private boolean suppressed = false;
	
	
	public EdgeReached(MovePilot p){//Constructor passes a pilot
		this.pilot = p;
		
	}
	
	public void action() {//Rotates the car 90 degrees at a steady speed.
		suppressed = false;
		pilot.backward();
		pilot.setLinearSpeed(SPEED);
		pilot.backward();
    	Delay.msDelay(TIME);
		pilot.setAngularSpeed(SPEED);
		pilot.rotate(ANGLE);
        
	}
	
	public boolean takeControl() {//Returns true if the colour sensor returns 0.75
		colour.fetchSample(level, 0);
		
		return(level[0] > 0.75);
		
		
	}
	
	public void suppress() {//Suppresses action.
		suppressed = true;
		
	}
}
