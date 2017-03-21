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

public class FileData {
   
   
   private Scoreboard scoreboard;
   private String fileName;
   private Game game;
   
   public FileData(){
	   Scanner user_input = new Scanner(System.in);
	   this.fileName = "Kamisado.config";
	   this.game = null;
   }
   
   public FileData(String fileName){
	   this.fileName = fileName;
	   this.game = null;
	   loadFile();
   }
   
   public void writeOut() {
	   System.out.println(game.toString());
   }
   
   public Scoreboard getScore() {
      return scoreboard;
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
        		 inputObject = in.readObject();
        		 if (inputObject instanceof Game)
        			 game = ((Game)inputObject);
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
