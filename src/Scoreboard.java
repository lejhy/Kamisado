
public class Scoreboard {
   
   private int[] score;
   private String[] playerName;
   private int[] time;
   
   public Scoreboard(int[] score , String[] playerName, int[] time) {
	   score = this.score;
	   playerName = this.playerName;
	   time = this.time;
   }
   
   
   
   public void setScore(int value, int i) {
      this.score[i] = value;
   }
   
   public int getScore(int i) {
      return this.score[i];
   }
   
   
   
   public void setPlayerName(String value,int i) {
      this.playerName[i] = value;
   }
   
   public String getPlayerName(int i) {
      return this.playerName[i];
   }
   
   
   
   public void setTime(int value, int i) {
      this.time[i] = value;
   }
   
   public int getTime(int i) {
      return this.time[i];
   }
   
   }
