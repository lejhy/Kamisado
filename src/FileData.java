import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileData {
   
   
   private Scoreboard scoreboard;
   private String fileName;
   private ArrayList<Game> games;
   private ArrayList<Player> players;
   
   public FileData(){
	   this.fileName = fileName;
	   this.scoreboard = scoreboard;
	   ArrayList<Game> games = new ArrayList<Game>();
	   ArrayList<Player> players = new ArrayList<Player>();
   }
   
   
   public Scoreboard getScore() {
      return scoreboard;
   } 
   
   public void addGame(FileData filedata, Game game) {
      games.add(game);
   }
   
   public Game getGame(FileData filedata, int i) {
      return games.get(i);
   }
   
   public Player getPlayers(FileData filedata, int i){
	   return players.get(i);
   }
   
   public ArrayList<Game> getGames(FileData filedata) {
      return games;
   }
   
   public void saveDataToFile() {
	   try {
//		  JFileChooser chooser = new JFileChooser();
//		  chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);;
//		  chooser.showSaveDialog(null);
//		  
//		  String path = chooser.getSelectedFile().getAbsolutePath();
//		  String filename = chooser.getSelectedFile().getName();
		   
		   FileOutputStream outPut = new FileOutputStream(fileName);
		   ObjectOutputStream out = new ObjectOutputStream(outPut);
		   out.writeObject(games);
		   out.writeObject(scoreboard);
		   out.close();
		   outPut.close();
		   System.out.println("Data is save in: " + fileName);
	   
	   }catch(IOException i){
		   i.printStackTrace();
	   } 
   }
	   
	   
	   
	public void loadFile(){
			      games = null;
			      scoreboard = null;
			      try {
			         FileInputStream fileIn = new FileInputStream(fileName);
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         games = (ArrayList) in.readObject();
			         scoreboard = (Scoreboard) in.readObject();
			         in.close();
			         fileIn.close();
			      }catch(IOException i) {
			         i.printStackTrace();
			         return;
			      }catch(ClassNotFoundException c) {
			         System.out.println("File was not found");
			         c.printStackTrace();
						      }
   }
   
   public void removeGame(FileData filedata, int i) {
     games.remove(i);   
   }

public String getFileName(FileData filedata) {
	return fileName;
	
}

   
}
