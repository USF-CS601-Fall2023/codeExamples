package designPatterns.command;

 /* LightOffCommand class.
 From Command Design Pattern example, from the Head First Design Patterns book */
public class LightOffCommand implements Command {
	 private Light light;
 
	public LightOffCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
		light.off();
	}
}
