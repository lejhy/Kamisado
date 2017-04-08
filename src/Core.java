import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


public class Core extends Observable implements Observer{
	private FileData data;
	private Game game;
	private Position selection;
	
	private View view;
	private MainMenuViewController mainMenuViewController;
	private NewGameViewController newGameViewController;
	private LoadGameViewController loadGameViewController;
	private ScoreViewController scoreViewController;
	private GameViewController gameViewController;
	
	
	public Core () {
		data = new FileData("Kamisado.config");
		this.game = null;
		this.selection = new Position(-1,-1);
	}
	
	@Override
	public void update (Observable observable, Object argument) {
		if (game.isGameOver() == false) 
			checkForAI();
	}
	
	public void gameInput(Position position, Value inputType) {
		if (inputType == Value.ACTION) {
			Move move = new Move(selection, position);
			if (game.nextTurn(move, Value.HUMAN)) {
				selection = game.getValidPiece().getPosition();
				change();
			} else if (game.isGameOver() && game.hasNextRound()){
				game.nextRound();
			} else if (game.isValidPiecePosition(position)){
				selection = position;
			} else {
				selection = game.getValidPiecePosition();
			}
			change();
		} else if (inputType == Value.HOVER){
			
		}
	}
	
	public void undoMove() {
		if (game.undoLastMove()) {
			selection = game.getValidPiecePosition();
			change();
		}
	}
	
	public void saveGame() {
		data.addGame(game.clone());
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
	
	public void exit() {
		data.saveDataToFile();
		System.exit(0);
    }
	
    void mainMenu() {
    	view.displayScene(mainMenuViewController);
    }
	
    void newGame(Player player1, Player player2, int time, int points, Value gameMode) {
    	if (game != null) 
    		game.purge();
    	if (gameMode == Value.SPEED_MODE){
    		this.game = new SpeedGame(player1, player2, time, points);
    	} else {
    		this.game = new NormalGame(player1, player2, points);
    	}
    	game.addObserver(this);
    	game.addPlayer1PointsListener((observable, oldValue, newValue) -> {
    		if (game.hasNextRound() == false)
    			data.addScore(game);
    	});
    	game.addPlayer2PointsListener((observable, oldValue, newValue) -> {
    		if (game.hasNextRound() == false)
    			data.addScore(game);
    	});
    	selection = game.getValidPiecePosition();
    	wireGameView();
    	change();
    }
    
    public void wireGameView() {
    	gameViewController.setGame(game);
    	game.addObserver(gameViewController);
    	game.addBoardObserver(gameViewController);
    	
    	gameViewController.setPlayer1Name(game.getPlayer1Name());
    	gameViewController.setPlayer2Name(game.getPlayer2Name());
    	
    	gameViewController.setPlayer1Score(0);
    	gameViewController.setPlayer2Score(0);
    	game.addPlayer1PointsListener((o, ov, nv) -> {
    		gameViewController.setPlayer1Score(nv.intValue());
    	});
    	game.addPlayer2PointsListener((o, ov, nv) -> {
    		gameViewController.setPlayer2Score(nv.intValue());
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
		if (game != null){
			return true;
		} else {
			return false;	
		}
	}
	
	private void change() {
		setChanged();
		notifyObservers();
	}
	
	public void setView(View view) {
		this.view = view;
		this.view.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				exit();
			}
		});
	}
	
	public void setMainMenuViewController(Controller mainMenuViewController) {
		this.mainMenuViewController = (MainMenuViewController)mainMenuViewController;
		this.addObserver(mainMenuViewController);
		this.mainMenuViewController.setCore(this);
	}

	public void setNewGameViewController(Controller newGameViewController) {
		this.newGameViewController = (NewGameViewController)newGameViewController;
		this.newGameViewController.setCore(this);
	}
	
	public void setLoadGameViewController(Controller loadGameViewController) {
		this.loadGameViewController = (LoadGameViewController)loadGameViewController;
		this.loadGameViewController.setCore(this);
		this.loadGameViewController.setGameList(data.getGameList());
	}

	public void setScoreViewController(Controller scoreViewController) {
		this.scoreViewController = (ScoreViewController)scoreViewController;
		this.scoreViewController.setCore(this);
		this.scoreViewController.setScoreList(data.getScoreList());
	}

	public void setGameViewController(Controller gameViewController) {
		this.gameViewController = (GameViewController)gameViewController; 
		this.addObserver(gameViewController);
		this.gameViewController.setCore(this);
	}
}