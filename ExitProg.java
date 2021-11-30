
import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class ExitProg implements Behavior { // Inherits from behvaiour class
    private EV3UltrasonicSensor sensor; 
    private BaseRegulatedMotor clawServo;
    private EV3ColorSensor sensor1;
	
    public ExitProg() {
		
	}
	public boolean takeControl() {  // Returns boolean for button down leading to action()
		return Button.ENTER.isDown();
		
	}

	public void action() { // Causes the system to exit if  button is down
		
		System.exit(0);
		
	}

	public void suppress() {}

}
