package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.ViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View {
	private Stage stage;
	private List<Scene> scenes;
	private List<ViewController> controllers;

	public View(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
		this.scenes = new ArrayList<Scene>();
		this.controllers = new ArrayList<ViewController>();
	}

	public void displayScene(ViewController controller) {
		Scene scene = findScene(controller);
		this.stage.setScene(scene);
	}

	public ViewController loadFXML(String fxmlFile, String cssFile) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(fxmlFile));

			Scene scene = new Scene(loader.load());
			ViewController controller = loader.getController();

			if (cssFile != null)
				scene.getStylesheets().add(getClass().getResource(cssFile).toString());

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

	public Scene findScene(ViewController controllerToMatch) {
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
