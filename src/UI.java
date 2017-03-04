import java.util.Set;
import java.util.HashSet;

public class UI {
   
   public void menu(String[] choice){
	   for (int i = 0; i < choice.length; i++){
		   System.out.println("(" + i + ") - " + choice[i]);
	   }
   }
}
