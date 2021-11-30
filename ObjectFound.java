
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;


public class ObjectFound implements Behavior {
	private BaseRegulatedMotor clawServo;
	private SampleProvider sensor;
	private MovePilot pilot;
	private State sharedState;
	private boolean grab = false;
	private static int STOP_TIME= 200; 
	private static int TIME = 1000;
	private static int  SPEED = 1000;
	public ObjectFound(BaseRegulatedMotor clawServo,  SampleProvider sensor, MovePilot pilot, State sharedState) {//Constructor.
		this.clawServo = clawServo;
		this.sensor = sensor;
		this.pilot = pilot;
		this.sharedState = sharedState;
		
		
	}
	
	
	private float[] distance = new float[1]; 
	
	public void action() {//Closes the claw and sets the state to true, meaning it is grabbing an item.
		grab = true;
		Delay.msDelay(STOP_TIME);
		pilot.stop();
		clawServo.setSpeed(SPEED);
		clawServo.backward();
		Delay.msDelay(2000);
		clawServo.stop();
		sharedState.setHolding(true);
		grab = false;
	}
	
	public void suppress() {clawServo.stop();}// stops the claw from closing and suppresses the action.
	
	public boolean takeControl() { // If the item is 4 cm in front of it then return true.
		sensor.fetchSample(distance, 0);
		return (distance[0] < 0.04f && !grab);
	}	

}