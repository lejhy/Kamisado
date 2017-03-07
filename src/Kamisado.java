import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

/**
 * Filip Lejhanec
 * Fraser Steel 
 */

public class Kamisado{

	public static void main(String[] args) {
		String filename = "Kamisado.save";
		UI ui = new UI();
		Controller controller = new Controller(ui, filename);
	}
}
