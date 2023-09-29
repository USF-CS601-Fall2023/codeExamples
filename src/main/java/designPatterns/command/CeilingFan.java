package designPatterns.command;

/** Ceiling Fan.
 * Command Design Pattern. From the Head First Design Patterns book */

public class CeilingFan {
	private String location = "";
	private int level;
	public static final int HIGH = 2;
	public static final int MEDIUM = 1;
	public static final int LOW = 0;
 
	public CeilingFan(String location) {
		this.location = location;
	}
  
	public void high() {
		// turns the ceiling fan on to high
		level = HIGH;
		System.out.println(location + " ceiling fan is on high");
 
	} 

	public void medium() {
		// turns the ceiling fan on to medium
		level = MEDIUM;
		System.out.println(location + " ceiling fan is on medium");
	}

	public void low() {
		// turns the ceiling fan on to low
		level = LOW;
		System.out.println(location + " ceiling fan is on low");
	}
 
	public void off() {
		// turns the ceiling fan off
		level = 0;
		System.out.println(location + " ceiling fan is off");
	}
 
	public int getSpeed() {
		return level;
	}
}
