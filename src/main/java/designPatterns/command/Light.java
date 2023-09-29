package designPatterns.command;

 /* Class Light.
 From Command Design Pattern example, from the Head First Design Patterns book */
public class Light {
	 private String location = "";

	public Light(String location) {
		this.location = location;
	}

	public void on() {
		System.out.println(location + " light is on");
	}

	public void off() {
		System.out.println(location + " light is off");
	}
}
