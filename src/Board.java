public class Board {
	private Color[][] tiles;
	
	public Board(){
		tiles = new String[8][8];
		String[][] tiles = {
			{"orange", "blue", "purple", "pink", "yellow", "red", "green", "brown"},
			{"red", "orange", "pink", "green", "blue", "yellow", "brown", "purple"},
			{"green", "pink", "orange", "red", "purple", "brown", "yellow", "blue"},
			{"pink", "purple", "blue", "orange", "brown", "green", "red", "yellow"},
			{"yellow", "red", "green", "brown", "orange", "blue", "purple", "pink"},
			{"blue", "yellow", "brown", "purple", "red", "orange", "pink", "green"},
			{"purple", "brown", "yellow", "blue", "green", "pink", "orange", "red"},
			{"brown", "green", "red", "yellow", "pink", "purple", "blue", "orange"}
		};
		this.tiles = tiles;
	}	
	public void  render(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				System.out.print(tiles[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}
	
	public Color[][] getTiles(){
		return tiles;
	}
	
	public Color getTile(int i, int j){
		return tiles[i][j];
	}
}
