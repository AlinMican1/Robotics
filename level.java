import lejos.hardware.lcd.LCD;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.hardware.Battery;
import lejos.hardware.Sound;


public class level implements Behavior{
	private float current;
	private boolean Supp = false;
	private Battery Battery1;
	private static final int WAIT = 5000;
	level(){ // CONSTRUCTOR TAKING IN FLOAT VALUE FROM THE BATTERY
		Battery1 = new Battery();
	}
	
	
	
	public void suppress() {
		this.Supp = true;
		while(!this.Supp) {
        	Thread.yield();
		}
	}
	
	public boolean takeControl() {
		
		return Battery1.getVoltage() <3; // if the VOLTAGE IS LESS TAHT 3V , EACH BATTERY 1.5V *6 
	
	}
		

	public void action() {
		for (int i = 0;i<5;i++) {
		LCD.drawString("Battery is Low",0,0);}   // FLASH THAT BATTERY IS LOW
		Sound.beep();// MAKE A BEEP SOUND 
		Delay.msDelay(WAIT);
		System.exit(0);
	}
		
		
}


