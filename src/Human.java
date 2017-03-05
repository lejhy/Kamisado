
public class Human extends Player{
	private Kamisado controller;
	
	public Move getMove(){
		return controller.getMove();
	}
	
	public Human(String name, boolean value, Kamisado controller){
		super(name, value);
		this.controller = controller;
	}
}
