package designPatterns.command;

/** Command Design Pattern. From the Head First Design Patterns book */
public class CeilingFanOffCommand implements Command {
	private CeilingFan ceilingFan;

	public CeilingFanOffCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
	public void execute() {
		ceilingFan.off();
	}
}
