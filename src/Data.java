import java.util.Set;

import javax.swing.JFileChooser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class Data {
   
   private List<Game> games;
   private Scoreboard scoreboard;
   private String fileName;
   private List<Player> players;
   
   public Data(String fileName){
	   this.fileName = fileName;
	   this.init();
   }
   
   public Data() {
	// TODO Auto-generated constructor stub
}

private void init () {
	   
   }
   
   public Scoreboard getScore() {
      return scoreboard;
   } 
   
   public void addGame(Game game) {
      games.add(game);
   }
   
   public Game getGame(int i) {
      return games.get(i);
   }
   
   public Player getPlayers(int i){
	   return players.get(i);
   }
   
   public List<Game> getGames() {
      return games;
   }
   
   public void saveDataToFile(String fileName) {
	   try {
//		  JFileChooser chooser = new JFileChooser();
//		  chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);;
//		  chooser.showSaveDialog(null);
//		  
//		  String path = chooser.getSelectedFile().getAbsolutePath();
//		  String filename = chooser.getSelectedFile().getName();
		   
		   FileOutputStream fileOut = new FileOutputStream(fileName);
		   ObjectOutputStream out = new ObjectOutputStream(fileOut);
		   out.writeObject(games);
		   out.writeObject(players);
		   out.close();
		   fileOut.close();
		   System.out.println("Data is save in: " + fileName);
	   
	   }catch(IOException i){
		   i.printStackTrace();
	   }  
	
	   // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void removeGame(Game game) {
      this.games = null;
      throw new UnsupportedOperationException("not implemented");
   }

public String getFileName() {
	// TODO Auto-generated method stub
	return null;
}

public void saveDataToFile() {
	// TODO Auto-generated method stub
	
}
   
}
