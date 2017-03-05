
public class Human extends Player{
	private UI ui;
	
	public Move getMove(Board board){
		int startX = Integer.parseInt(ui.prompt("startX: "));
		int startY = Integer.parseInt(ui.prompt("startY: "));
		int finishX = Integer.parseInt(ui.prompt("finishX: "));
		int finishY = Integer.parseInt(ui.prompt("finishY: "));
		Move move = new Move(startX,startY,finishX,finishY);
		return move;
	}
	
	public Human(String name, boolean value, UI ui){
		super(name, value);
		this.ui = ui;
	}
}
