package designPatterns.command;

public class LivingroomLightOnCommand implements Command {
	private Light light;

	public LivingroomLightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.on();
	}
}
