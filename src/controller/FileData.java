package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameEntry;
import model.ScoreEntry;

public class FileData {

	private ObservableList<GameEntry> gameList;
	private ObservableList<ScoreEntry> scoreList;
	private String fileName;

	public FileData() {
		this.fileName = "Kamisado.config";
		this.gameList = FXCollections.observableArrayList();
		this.scoreList = FXCollections.observableArrayList();
		loadFile();
	}

	public FileData(String fileName) {
		this.fileName = fileName;
		this.gameList = FXCollections.observableArrayList();
		this.scoreList = FXCollections.observableArrayList();
		loadFile();
	}

	public ObservableList<ScoreEntry> getScoreList() {
		return scoreList;
	}

	public void addScore(Game game) {
		scoreList.add(new ScoreEntry(game.getPlayer1().getName().get(), game.getPlayer2().getName().get(),
				game.getScore().getPlayer1Points().get(), game.getScore().getPlayer2Points().get()));
	}

	public ObservableList<GameEntry> getGameList() {
		return gameList;
	}

	public void addGame(Game game) {
		gameList.add(new GameEntry(game, System.currentTimeMillis()));
	}

	public Game getGame(int i) {
		return gameList.get(i).getGame();
	}

	public void saveDataToFile() {
		try {
			FileOutputStream outPut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(outPut);
			for (GameEntry game : gameList) {
				out.writeObject(game);
			}
			for (ScoreEntry entry : scoreList) {
				out.writeObject(entry);
			}
			out.close();
			outPut.close();
			System.out.println("Data is saved in: " + fileName);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void loadFile() {
		scoreList.clear();
		gameList.clear();
		;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				Object inputObject = null;
				while (true) {
					inputObject = in.readObject();
					if (inputObject instanceof GameEntry)
						gameList.add((GameEntry) inputObject);
					else if (inputObject instanceof ScoreEntry)
						scoreList.add((ScoreEntry) inputObject);
				}
			} catch (ClassNotFoundException | EOFException e) {
				in.close();
				fileIn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public String getFileName() {
		return fileName;
	}
}
