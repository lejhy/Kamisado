
public class Score {
   /**
    * <pre>
    *           0..*     0..1
    * Score ------------------------- Data
    *           score        &lt;       data
    * </pre>
    */
   private Data data;
   
   public void setData(Data value) {
      this.data = value;
   }
   
   public Data getData() {
      return this.data;
   }
   
   private int score;
   
   public void setScore(int value) {
      this.score = value;
   }
   
   public int getScore() {
      return this.score;
   }
   
   private String playerName;
   
   public void setPlayerName(String value) {
      this.playerName = value;
   }
   
   public String getPlayerName() {
      return this.playerName;
   }
   
   private int time;
   
   public void setTime(int value) {
      this.time = value;
   }
   
   public int getTime() {
      return this.time;
   }
   
   }
