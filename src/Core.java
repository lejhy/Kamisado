import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Core implements Observer{
	@SuppressWarnings("unused")
	private Data data;
	private Game game;
	private Position selection;
	
	private View view;
	private MainMenuViewController mainMenuViewController;
	private NewGameViewController newGameViewController;
	private LoadGameViewController loadGameViewController;
	private ScoreViewController scoreViewController;
	private GameViewController gameViewController;
	
	
	public Core () {
		try {
			// try opening an existing file
			data = new Data("Kamisado.save");
		} catch (Exception e) {
			// create a new file
			data = new Data();
		}
	}
	
	@Override
	public void update (Observable observable, Object argument) {
		if(observable == this.game){
			updateGame();
		} else {
			
		}
	}
	
	public void gameInput(Position position) {
		if (GameLogic.isValidTower(game.getBoard(), position.x, position.y)) {
			selection = position;
			Tower tower = game.getBoard().getTower(selection.x, selection.y);
			showValidMoves(tower);
		} else {
			game.nextTurn(new Move(selection.x, selection.y, position.x, position.y), Value.HUMAN);
		}
	}
	
	private void updateGame() {
		gameViewController.drawBoard(game.getBoard());
		if (game.isGameOver()) {
			gameViewController.gameOver(game.getWinner().getName());
		} else {
			if (game instanceof SpeedGame) {
				gameViewController.showTimer(((SpeedGame)game).getTimeLimit());
			} else {
				gameViewController.hideTimer();
			}
			checkForAI();
		}
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
	
	public void scoreMenu() {
		view.displayScene(scoreViewController);
    }
	
	public void exit() {
		System.exit(0);
    }
	
    void mainMenu() {
    	view.displayScene(mainMenuViewController);
    }
	
    void newGame(Player player1, Player player2, Value gameMode) {
    	if (gameMode == Value.SPEED_MODE){
    		this.game = new SpeedGame(player1, player2);
    	} else {
    		this.game = new NormalGame(player1, player2);
    	}
    	
    	game.addObserver(this);
    	gameViewController.setPlayerNames(player1.getName(), player2.getName());
    	view.displayScene(gameViewController);
    	this.update(game, Value.READY);
    }
    
    
    public void showValidMoves(Tower tower) {
    	List<Move> moves = GameLogic.getValidMoves(game.getBoard(), tower);
    	List<Position> positions = new ArrayList<Position>();
    	for (Move move:moves) {
    		positions.add(new Position(move.finishX, move.finishY));
    	}
    	gameViewController.drawBoard(game.board);
    	gameViewController.drawHighlights(positions);
    }
	
	public void checkForAI() {
		if (game.getCurrentPlayer().getType() == Value.EASY_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					game.nextTurn(AI.MiniMaxAB(game.getBoard(), 3), Value.EASY_AI);
					gameViewController.hideLoader();
				}
			});
			thread.setDaemon(true);
			thread.start();
		} else if (game.getCurrentPlayer().getType() == Value.HARD_AI) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					gameViewController.showLoader();
					game.nextTurn(AI.MiniMaxAB(game.getBoard(), 9), Value.HARD_AI);
					gameViewController.hideLoader();
				}
			});
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setMainMenuViewController(Controller mainMenuViewController) {
		this.mainMenuViewController = (MainMenuViewController)mainMenuViewController;
		mainMenuViewController.setCore(this);
	}

	public void setNewGameViewController(Controller newGameViewController) {
		this.newGameViewController = (NewGameViewController)newGameViewController;
		newGameViewController.setCore(this);
	}

	public void setLoadGameViewController(Controller loadGameViewController) {
		this.loadGameViewController = (LoadGameViewController)loadGameViewController;
		loadGameViewController.setCore(this);
	}

	public void setScoreViewController(Controller scoreViewController) {
		this.scoreViewController = (ScoreViewController)scoreViewController;
		scoreViewController.setCore(this);
	}

	public void setGameViewController(Controller gameViewController) {
		this.gameViewController = (GameViewController)gameViewController;
		gameViewController.setCore(this);
	}
}