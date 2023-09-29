package designPatterns.command;

/** Command Design Pattern. From the Head First Design Patterns book */
public class GarageDoorUpCommand implements Command {
	private GarageDoor garageDoor;

	public GarageDoorUpCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}

	public void execute() {
		garageDoor.up();
	}
}
