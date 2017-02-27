import java.util.Set;
import java.util.HashSet;

public class UI {
   /**
    * <pre>
    *           0..*     0..*
    * UI ------------------------- Controller
    *           uI        &gt;       controller
    * </pre>
    */
   private Set<Controller> controller;
   
   public Set<Controller> getController() {
      if (this.controller == null) {
         this.controller = new HashSet<Controller>();
      }
      return this.controller;
   }
   
   public void render() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void getInput() {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
