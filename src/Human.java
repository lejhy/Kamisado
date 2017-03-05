
public class Human extends Player{
	private UI ui;
	
	public Move getMove(Board board){
		return ui.getMove();
	}
	
	public Human(String name, boolean value, UI ui){
		super(name, value);
		this.ui = ui;
	}
}
