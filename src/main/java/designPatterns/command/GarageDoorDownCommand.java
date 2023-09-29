package designPatterns.command;

/** Command Design Pattern. From the Head First Design Patterns book */
public class GarageDoorDownCommand implements Command {
	private GarageDoor garageDoor;

	public GarageDoorDownCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}

	public void execute() {
		garageDoor.down();
	}
}
