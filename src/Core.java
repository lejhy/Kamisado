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
		checkForAI();
	}
	
	public void gameInput(Position position, Value inputType) {
		if (inputType == Value.ACTION) {
			if (!game.isGameOver()) {
				if (GameLogic.isValidTower(game.getBoard(), position.x, position.y)) {
					selection = position;
				} else {
					game.nextTurn(new Move(selection.x, selection.y, position.x, position.y), Value.HUMAN);
					selection = GameLogic.getValidTower(game.getBoard()).getPosition();
				}
				change();
			} else if (game.hasNextRound()){
				game.nextRound();
			}
		} else if (inputType == Value.HOVER){
			
		}
	}
	
	public void undoMove() {
		if(!game.isGameOver() && game.getCurrentPlayer().getType() == Value.HUMAN && game.getLastPlayer().getType() != Value.HUMAN) {
			game.getBoard().undoLastMove();
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
		this.game = game;
		game.addObserver(this);
		selection = GameLogic.getValidTower(game.getBoard()).getPosition();
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
    	if (gameMode == Value.SPEED_MODE){
    		this.game = new SpeedGame(player1, player2, time, points);
    	} else {
    		this.game = new NormalGame(player1, player2, points);
    	}
    	game.addObserver(this);
    	game.getGameOver().addListener((observable, oldValue, newValue) -> {
    		if (game.hasNextRound() == false)
    			data.addScore(game);
    	});
    	wireGameView();
    	change();
    }
    
    public void wireGameView() {
    	gameViewController.setGame(game);
    	game.addObserver(gameViewController);
    	game.getBoard().addObserver(gameViewController);
    	
    	gameViewController.setPlayer1Name(game.getPlayer1().getName().get());
    	gameViewController.setPlayer2Name(game.getPlayer2().getName().get());
    	
    	gameViewController.setPlayer1Score(0);
    	gameViewController.setPlayer2Score(0);
    	game.getScore().getPlayer1Points().addListener((o, ov, nv) -> {
    		gameViewController.setPlayer1Score(nv.intValue());
    	});
    	game.getScore().getPlayer2Points().addListener((o, ov, nv) -> {
    		gameViewController.setPlayer2Score(nv.intValue());
    	});

    	view.displayScene(gameViewController);
    	this.update(game, Value.READY);
    	gameViewController.update(game, null);
    }
    
    
    public List<Position> getPositionsToHighlight() {
    	Tower tower = game.getBoard().getTower(selection.x, selection.y);
    	if (tower != null && game.getCurrentPlayer().getType() == Value.HUMAN) {
	    	List<Move> moves = GameLogic.getValidMoves(game.getBoard(), tower);
	    	List<Position> positions = new ArrayList<Position>();
	    	for (Move move:moves) {
	    		positions.add(new Position(move.finishX, move.finishY));
	    	}
	    	return positions;
    	} else {
    		return new ArrayList<Position>();
    	}
    }
	
	public void checkForAI() {
		
		if (game.getCurrentPlayer().getType() == Value.BEGINNER_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					Move move = AI.MiniMaxAB(game.getBoard(), 2);
					gameViewController.hideLoader();
					Platform.runLater(new Runnable() {
						public void run() {
							game.nextTurn(move, Value.BEGINNER_AI);
							selection = GameLogic.getValidTower(game.getBoard()).getPosition();
							change();
						}
					});
				}
			});
			thread.setDaemon(true);
			thread.start();
		} else if (game.getCurrentPlayer().getType() == Value.EASY_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					Move move = AI.MiniMaxAB(game.getBoard(), 5);
					gameViewController.hideLoader();
					Platform.runLater(new Runnable() {
						public void run() {
							game.nextTurn(move, Value.EASY_AI);
							selection = GameLogic.getValidTower(game.getBoard()).getPosition();
							change();
						}
					});
				}
			});
			thread.setDaemon(true);
			thread.start();
			
		} else if (game.getCurrentPlayer().getType() == Value.HARD_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					Move move = AI.MiniMaxAB(game.getBoard(), 9);
					gameViewController.hideLoader();
					Platform.runLater(new Runnable() {
						public void run() {
							game.nextTurn(move, Value.HARD_AI);
							selection = GameLogic.getValidTower(game.getBoard()).getPosition();
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