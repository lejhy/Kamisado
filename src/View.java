import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View {
	private Stage stage;
	private List<Scene> scenes;
	private List<Controller> controllers;

	public View(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
		this.scenes = new ArrayList<Scene>();
		this.controllers = new ArrayList<Controller>();
	}

	public void displayScene(Controller controller) {
		Scene scene = findScene(controller);
		this.stage.setScene(scene);
	}
	
	public Controller loadFXML(String fxmlFile, String cssFile) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Kamisado.class.getResource(fxmlFile));
			
			Scene scene = new Scene(loader.load());
			Controller controller = loader.getController();
			
			if (cssFile != null)
				scene.getStylesheets().add("Kamisado.css");
			
			if (scenes.isEmpty())
				stage.setScene(scene);
			 
			scenes.add(scene);
			controllers.add(controller);
			
			return controller;
			
		} catch (IOException e) {
			System.out.println("Error loading fxml file");
			System.err.println();
			return null;
		}
	}
	
	public Scene findScene(Controller controllerToMatch) {
		for (int i = 0; i < controllers.size(); i++) {
			if (controllers.get(i) == controllerToMatch) {
				return scenes.get(i);
			}
		}
		return null;
	}
	
	public void setOnCloseRequest(EventHandler<WindowEvent> e) {
		stage.setOnCloseRequest(e);
	}
}
