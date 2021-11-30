
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Navigator_Robot {
    private static int TIME = 1000;
    private static int SPEED = 720;
	public static void main(String[] args) {//Main class
		
		MovePilot pilot = getPilot(MotorPort.A, MotorPort.B, 56, 48);//Set the motors and dimensions.
		State sharedState = new State();
		
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S2);//These are all the sensors we are using.
		SampleProvider sp = sensor.getDistanceMode();
		
		BaseRegulatedMotor clawServo = new EV3LargeRegulatedMotor(MotorPort.C);
		
		PoseProvider poseprovider = new OdometryPoseProvider(pilot);
        Navigator navigator = new Navigator(pilot, poseprovider);
		
       
		
		
		//Creating the behaviours.
		Behavior forward = new Onwards(pilot);
		Behavior objFound = new ObjectFound(clawServo, sp, pilot, sharedState);
		Behavior objLost = new ObjectLost(clawServo, sp, sharedState);
		Behavior Exit = new ExitProg();
		Behavior Edge = new EdgeReached(pilot);
		Behavior Level = new level();
		Behavior dropoff = new Waypoints(pilot,poseprovider,navigator,sharedState,clawServo);
		Arbitrator ab = new Arbitrator(new Behavior[] 
				{forward,Edge,objFound,objLost,dropoff,Exit,Level}); //putting them in a array to call them
				ab.go(); // This never returns! It is a blocking call.
				
		
		sensor.close();//Closes the sensor	
		clawServo.close();//Closes the claw.
		

	}

	
	private static MovePilot getPilot(Port left, Port right, int diam, int offset) {//Created a getPilot method.
		
		BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(left);//sets the motors.
		Wheel wL = WheeledChassis.modelWheel(mL, diam).offset(-1 * offset);
		BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(right);
		Wheel wR = WheeledChassis.modelWheel(mR, diam).offset(offset);
		Wheel[] wheels = new Wheel[] {wR, wL};
		Chassis chassis = new WheeledChassis(wheels, WheeledChassis.TYPE_DIFFERENTIAL);
		return new MovePilot(chassis);
	}
	
}
