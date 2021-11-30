
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;


public class ObjectLost implements Behavior {
	
	private BaseRegulatedMotor clawServo;
	private SampleProvider sensor;
	private State sharedState;

	private static int TIME = 1000;
	private static int  SPEED = 1000;
	private boolean grab = true;
	public ObjectLost(BaseRegulatedMotor clawServo, SampleProvider sensor, State sharedState) {//Constructor

		this.clawServo = clawServo;
		this.sensor = sensor;
		this.sharedState = sharedState;	
	}

	
	private float[] distance = new float[1]; 

	public void action() {//Checks if the object is lost

		grab = true;
		clawServo.setSpeed(SPEED);
		clawServo.forward();
		Delay.msDelay(TIME);
		clawServo.stop();
		sharedState.setHolding(false);
		grab = false;

	}

	public void suppress() { clawServo.stop();} //Closes the claw and suppreses the action.

	public boolean takeControl() { // if the item is more than 4 cm ahead then it means that the object has been lost so return true to run the action.
		sensor.fetchSample(distance, 0);
		return (distance[0] > 0.04f && sharedState.isHolding() && !grab);
	}

}