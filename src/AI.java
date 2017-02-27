import java.util.Set;

public interface AI {
   /**
    * <pre>
    *           0..*     0..*
    * AI ------------------------> Board
    *           aI        &gt;       board
    * </pre>
    */
   public Set<Board> getBoard();
   
   public void makeMove();
   
   }
