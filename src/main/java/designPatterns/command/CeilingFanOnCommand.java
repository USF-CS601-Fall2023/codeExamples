package designPatterns.command;

/** Command Design Pattern. From the Head First Design Patterns book */

public class CeilingFanOnCommand implements Command {
	private CeilingFan ceilingFan;

	public CeilingFanOnCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
	public void execute() {
		ceilingFan.high();
	}
}
