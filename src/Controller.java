import java.util.Set;
import java.util.HashSet;

public class Controller {
   /**
    * <pre>
    *           0..*     0..*
    * Controller ------------------------- UI
    *           controller        &lt;       uI
    * </pre>
    */
   private Set<UI> uI;
   
   public Set<UI> getUI() {
      if (this.uI == null) {
         this.uI = new HashSet<UI>();
      }
      return this.uI;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Controller ------------------------> Kamisado
    *           controller        &gt;       kamisado
    * </pre>
    */
   private Set<Kamisado> kamisado;
   
   public Set<Kamisado> getKamisado() {
      if (this.kamisado == null) {
         this.kamisado = new HashSet<Kamisado>();
      }
      return this.kamisado;
   }
   
   public void processInputs(int x, int y) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void processInputs(int i, String str) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void updateView() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
