import java.io.Serializable;
import java.util.ArrayList;

public class Scoreboard implements Serializable{
   
   private ArrayList<Integer> score = new ArrayList<Integer>();
   private ArrayList<String> playerName = new ArrayList<String>();
   private ArrayList<Integer> time = new ArrayList<Integer>();
   
   
   public Scoreboard(ArrayList<Integer> score , ArrayList<String> playerName, ArrayList<Integer> time) {
	   score = this.score;
	   playerName = this.playerName;
	   time = this.time;
   }
   
   
   
   public void setScore(int value, int i) {
      value = score.get(i);
   }
   
   public int getScore(int i) {
      return score.get(i);
   }
   
   public int size(){
	   return score.size();
   }
   
   public void resetScore(){
	   score = null;
	   playerName = null;
	   time = null;
   }
   
   public void setPlayerName(String value,int i) {
      value = playerName.get(i);
   }
   
   public String getPlayerName(int i) {
      return playerName.get(i);
   }
   
   
   
   public void setTime(int value, int i) {
      value = time.get(i);
   }
   
   public int getTime(int i) {
      return time.get(i);
   }
}
