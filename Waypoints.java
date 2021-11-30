
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.SampleProvider;

import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Waypoints implements Behavior { // 
	private MovePilot pilot;
	private PoseProvider poseprovider;
	private Navigator navigator;
	private State sharedState;
	private NXTLightSensor sensor = new NXTLightSensor(SensorPort.S1);
	private SampleProvider colourSamples =  sensor.getRedMode();
	private float[] colour = new float[1];
	private boolean travelling = false;
	private BaseRegulatedMotor clawServo;
	private static int TIME = 2000;
	private static int ANGLE = 180;
	private static int TIME2 = 1000;
	private static int  SPEED = 1000;
	private static int  BACK_SPEED = 100;
	private static int  ROTATE_SPEED = 10;
	
	public Waypoints(MovePilot pilot,PoseProvider poseprovider,Navigator navigator,State sharedState,BaseRegulatedMotor clawServo) { // Takes in Paramaters relating to movement and positioning
		this.navigator = navigator;
		this.pilot = pilot;
		this.poseprovider = poseprovider;
		this.sharedState = sharedState;
		this.clawServo = clawServo;
		
	}

	@Override
	public boolean takeControl() { // Returns boolean for travelling state and shared state if the robot its holding an object
		return sharedState.isHolding() && !travelling;

	}

	@Override
	public void action() { 
		travelling = true;
		
		colourSamples.fetchSample(colour, 0); // Detection for white colour
		if(colour[0] > 0.37) {
			Path white = new Path();
			white.add(new Waypoint(0f,300f)); // x-axis and y-axis are flipped
			LCD.drawString("ee" + colour[0], 1, 5 );
			Delay.msDelay(1);
			navigator.followPath(white);
			
		}
		if(colour[0] < 0.37) { // Detection for black colour moves the robot to black waypoint
			
			Path black = new Path();
			black.add(new Waypoint(500f,600f));
			LCD.drawString("ee" + colour[0], 1, 3 );
			Delay.msDelay(1);
			navigator.followPath(black);
		}
		//if(colour[0] >0.35 && colour[0] < 0.60) { //Detection for Yellow colour moves the robot to Yellow waypoint
			//Path yellow = new Path();
			//yellow.add(new Waypoint(-410.0,-340.0));
			//LCD.drawString("ee" + colour[0], 1, 4 );
			//Delay.msDelay(3000);
			//navigator.followPath(yellow);
		//}
            
		    navigator.waitForStop();      // Waits for robot to reach location and open its claws
        	clawServo.setSpeed(SPEED);
        	clawServo.forward();
        	Delay.msDelay(TIME2);
        	clawServo.stop();
        	
        	pilot.backward();          // allows the robot to retreat from drop location 
        	pilot.setLinearSpeed(BACK_SPEED);
        	pilot.backward();
        	Delay.msDelay(TIME);
        	
        	pilot.setLinearSpeed(ROTATE_SPEED); // Rotates the robot away from drop site
        	pilot.rotate(ANGLE);
        	sharedState.setHolding(false);
        	travelling = false;
        

	}

	@Override
	public void suppress() { // Stops robot from moving
		//navigator.stop();
		travelling = false;
		pilot.stop();
	}

}
