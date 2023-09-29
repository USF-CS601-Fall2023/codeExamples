package designPatterns.command;

/* LightOnCommand.
From Command Design Pattern example, from the Head First Design Patterns book */
public class LightOnCommand implements Command {
	private Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.on();
	}
}
