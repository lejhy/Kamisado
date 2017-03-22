import java.util.Observer;

public abstract class Controller implements Observer{
	protected Core core;

	public Controller() {
	}
	
	public void setCore(Core core) {
		this.core = core;
	}
}
