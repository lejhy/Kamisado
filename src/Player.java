public abstract class Player {

	protected int score;
	protected String name;
	protected boolean value;
	
	public abstract Move getMove();
	
	public void setValue(boolean value) {
		this.value = value;
	}
	
	public boolean getValue () {
		return value;
	}
	
	public void setScore(int value) {
	   this.score = value;
	}
	
	public int getScore() {
	   return this.score;
	}

	public String getName() {
		return name;
	}

	public void setName(String position) {
		this.name = position;
	}
	
	public Player(String name, boolean value){
		this.name = name;
		this.value = value;
	}
}
