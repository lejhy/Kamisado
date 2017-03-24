import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileData {
   
   
   private ObservableList<ScoreEntry> scoreList;
   private String fileName;
   private Game game;
   
   public FileData(){
	   this.fileName = "Kamisado.config";
	   this.game = null;
	   this.scoreList = FXCollections.observableArrayList();
	   loadFile();
   }
   
   public FileData(String fileName){
	   this.fileName = fileName;
	   this.game = null;
	   this.scoreList = FXCollections.observableArrayList();
	   loadFile();
   }
   
   public void writeOut() {
	   System.out.println(game.toString());
   }
   
   public ObservableList<ScoreEntry> getScoreList() {
      return scoreList;
   } 
   
   public void addScore(Game game) {
	   scoreList.add(new ScoreEntry(
		   game.getPlayer1().getName().get(), 
		   game.getPlayer2().getName().get(), 
		   game.getScore().getPlayer1Points().get(), 
		   game.getScore().getPlayer2Points().get()
	   ));
   }
   
   public void setGame(Game game) {
      this.game = game;
   }
   
   public Game getGame() {
      return game;
   }
   
   public void saveDataToFile() {
	   try {		   
		   FileOutputStream outPut = new FileOutputStream(fileName);
		   ObjectOutputStream out = new ObjectOutputStream(outPut);
		   out.writeObject(game);
		   for (ScoreEntry entry : scoreList) {
			   out.writeObject(entry);
		   }
		   out.close();
		   outPut.close();
		   System.out.println("Data is saved in: " + fileName);
	   }catch(IOException i){
		   i.printStackTrace();
	   } 
   }	   
	   
	public void loadFile(){     
	      try {
	    	  
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         try {
	        	 Object inputObject = null;
	        	 while(true) {
	        		 inputObject = in.readObject();
	        		 if (inputObject instanceof Game)
	        			 game = ((Game)inputObject);
	        		 else if (inputObject instanceof ScoreEntry)
	        			 scoreList.add((ScoreEntry)inputObject);
	        	 }
	         } catch (ClassNotFoundException | EOFException e) {
	        	 in.close();
		         fileIn.close();
	         }
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }
   }

	public String getFileName() {
		return fileName;
		
	}
}
