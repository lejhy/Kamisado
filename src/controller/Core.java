package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ai.AI;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.Game;
import model.Move;
import model.NormalGame;
import model.Piece;
import model.Player;
import model.Position;
import model.SpeedGame;
import model.Value;
import view.View;

public class Core extends Observable implements Observer {
	private FileData fileData;
	private Soundtrack soundtrack;
	private Game game;
	private Position selection;

	private View view;
	private MainMenuViewController mainMenuViewController;
	private NewGameViewController newGameViewController;
	private LoadGameViewController loadGameViewController;
	private ScoreViewController scoreViewController;
	private SettingsViewController settingsViewController;
	private GameViewController gameViewController;

	public Core(FileData fileData, Soundtrack soundtrack, View view) {
		this.fileData = fileData;
		this.soundtrack = soundtrack;
		this.view = view;
		for (ViewController controller : view.getControllers()){
			controller.setCore(this);
		}
		this.game = null;
		this.selection = new Position(-1, -1);
	}

	@Override
	public void update(Observable observable, Object argument) {
		if (game.isGameOver() == false)
			checkForAI();
	}

	public void gameInput(Position position, Value inputType) {
		if (inputType == Value.ACTION) {
			Move move = new Move(selection, position);
			if (game.nextTurn(move, Value.HUMAN)) {
				Piece piece = game.getValidPiece();
				if (!game.isGameOver() && piece.isDeadlocked()) {
					move = new Move(piece.getPosition(), piece.getPosition());
					game.nextTurn(move, Value.HUMAN);
					piece = game.getValidPiece();
				}
				selection = piece.getPosition();
				change();
			} else if (game.isGameOver() && game.hasNextRound()) {
				if (position.x <= 3)
					game.nextRound(Value.LEFT);
				else
					game.nextRound(Value.RIGHT);
			} else if (game.isValidPiecePosition(position)) {
				selection = position;
			} else {
				selection = game.getValidPiecePosition();
			}
			change();
		} else if (inputType == Value.HOVER) {

		}
	}

	public void undoMove() {
		if (game.undoLastMove()) {
			selection = game.getValidPiecePosition();
			change();
		}
	}

	public void saveGame() {
		fileData.addGame(game.clone());
	}

	public void resumeGame() {
		if (game != null) {
			view.displayScene(gameViewController);
		}
	}

	public void newGameMenu() {
		view.displayScene(newGameViewController);
	}

	public void loadGameMenu() {
		view.displayScene(loadGameViewController);
	}

	public void loadGame(Game game) {
		if (this.game != null)
			this.game.purge();
		this.game = game;
		game.addObserver(this);
		selection = game.getValidPiecePosition();
		wireGameView();
		change();
	}

	public void scoreMenu() {
		view.displayScene(scoreViewController);
	}

	public void settingsMenu() {
		view.displayScene(settingsViewController);
	}

	public void exit() {
		fileData.saveDataToFile();
		System.exit(0);
	}

	public void mainMenu() {
		view.displayScene(getMainMenuViewController());
	}

	public void newGame(Player white, Player black, int time, int points, boolean randomBoard, Value gameMode) {
		if (game != null)
			game.purge();
		if (gameMode == Value.SPEED_MODE) {
			this.game = new SpeedGame(white, black, time, points, randomBoard);
		} else {
			this.game = new NormalGame(white, black, points, randomBoard);
		}
		game.addObserver(this);
		game.addWhitePointsListener((observable, oldValue, newValue) -> {
			if (game.hasNextRound() == false)
				fileData.addScore(game);
		});
		game.addBlackPointsListener((observable, oldValue, newValue) -> {
			if (game.hasNextRound() == false)
				fileData.addScore(game);
		});
		selection = game.getValidPiecePosition();
		wireGameView();
		change();
	}

	public void wireGameView() {
		gameViewController.setGame(game);
		game.addObserver(gameViewController);
		game.addBoardObserver(gameViewController);

		gameViewController.setWhiteName(game.getWhiteName());
		gameViewController.setBlackName(game.getBlackName());

		gameViewController.setWhiteScore(game.getWhitePoints());
		gameViewController.setBlackScore(game.getBlackPoints());
		game.addWhitePointsListener((o, ov, nv) -> {
			gameViewController.setWhiteScore(nv.intValue());
		});
		game.addBlackPointsListener((o, ov, nv) -> {
			gameViewController.setBlackScore(nv.intValue());
		});

		view.displayScene(gameViewController);
		this.update(game, Value.READY);
		gameViewController.update(game, null);
	}

	public List<Position> getPositionsToHighlight() {
		if (game.isValidPiecePosition(selection)) {
			List<Move> moves = game.getValidMoves(selection);
			List<Position> positions = new ArrayList<Position>();
			for (Move move : moves) {
				positions.add(move.finish);
			}
			return positions;
		} else {
			return new ArrayList<Position>();
		}
	}

	public void checkForAI() {

		if (game.getCurrentPlayerType() == Value.BEGINNER_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					Move move = AI.MiniMaxAB(game.getBoard(), 2);
					gameViewController.hideLoader();
					Platform.runLater(new Runnable() {
						public void run() {
							game.nextTurn(move, Value.BEGINNER_AI);
							selection = game.getValidPiecePosition();
							change();
						}
					});
				}
			});
			thread.setDaemon(true);
			thread.start();
		} else if (game.getCurrentPlayerType() == Value.EASY_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					Move move = AI.MiniMaxAB(game.getBoard(), 5);
					gameViewController.hideLoader();
					Platform.runLater(new Runnable() {
						public void run() {
							game.nextTurn(move, Value.EASY_AI);
							selection = game.getValidPiecePosition();
							change();
						}
					});
				}
			});
			thread.setDaemon(true);
			thread.start();

		} else if (game.getCurrentPlayerType() == Value.HARD_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					Move move = AI.MiniMaxAB(game.getBoard(), 9);
					gameViewController.hideLoader();
					Platform.runLater(new Runnable() {
						public void run() {
							game.nextTurn(move, Value.HARD_AI);
							selection = game.getValidPiecePosition();
							change();
						}
					});
				}
			});
			thread.setDaemon(true);
			thread.start();
		}
	}

	public boolean hasGame() {
		if (game != null) {
			return true;
		} else {
			return false;
		}
	}

	private void change() {
		setChanged();
		notifyObservers();
	}
	
	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}
	
	public FileData getFileData() {
		return fileData;
	}
	
	public void setSoundtrack(Soundtrack soundtrack) {
		this.soundtrack = soundtrack;
	}
	
	public Soundtrack getSoundtrack() {
		return soundtrack;
	}

	public void setView(View view) {
		this.view = view;
		this.view.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				exit();
			}
		});
	}

	public MainMenuViewController getMainMenuViewController() {
		return mainMenuViewController;
	}

	public NewGameViewController getNewGameViewController() {
		return newGameViewController;
	}

	public void setNewGameViewController(NewGameViewController newGameViewController) {
		this.newGameViewController = newGameViewController;
	}

	public LoadGameViewController getLoadGameViewController() {
		return loadGameViewController;
	}

	public void setLoadGameViewController(LoadGameViewController loadGameViewController) {
		this.loadGameViewController = loadGameViewController;
	}

	public ScoreViewController getScoreViewController() {
		return scoreViewController;
	}

	public void setScoreViewController(ScoreViewController scoreViewController) {
		this.scoreViewController = scoreViewController;
	}

	public SettingsViewController getSettingsViewController() {
		return settingsViewController;
	}

	public void setSettingsViewController(SettingsViewController settingsViewController) {
		this.settingsViewController = settingsViewController;
	}

	public GameViewController getGameViewController() {
		return gameViewController;
	}

	public void setGameViewController(GameViewController gameViewController) {
		this.gameViewController = gameViewController;
	}

	public void setMainMenuViewController(MainMenuViewController mainMenuViewController) {
		this.mainMenuViewController = mainMenuViewController;
	}
}